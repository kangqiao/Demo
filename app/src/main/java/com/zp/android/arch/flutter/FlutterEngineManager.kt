package com.zp.android.arch.flutter

import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.view.FlutterMain
import java.lang.ref.SoftReference

/**
 * https://juejin.im/post/6844904117081473032
 * 知识点
 * 一个Native进程只有一个DartVM环境。
 * 一个Native进程可以有多个FlutterEngine，初始化第一个FlutterEngine会开始运行DartVM。
 * FlutterEngine可以后台运行代码，也可以通过FlutterRender渲染UI。
 * 多个FlutterEngine单独运行在各自的Isolate中，互不干涉。
 *
 * 这里根据业务场景对Flutter混开项目做下分类：
 * 1. 纯Flutter项目，整个项目的主页面或者成为栈顶就只有一个FlutterActivity(FlutterController)。
 *      所有页面跳转都单独一个FlutterEngine中完成，这种情况不需要管理FlutterEngine。
 * 2. Flutter独立模块，打开一个Flutter页面之后，接下来的页面都是Flutter页面，除非退出当前模块。
 *      就和WebView中跳转页面一样，这种情况下管理一个FlutterEngine单例即可。
 * 3. Flutter混合模块，Flutter页面与原生页面交错打开，这种情况要是一个Flutter页面一个FlutterEngine那将是灾难啊，
 *      并且对于运行内存数据也不好共享。**在这种情况下就需要对FlutterEngine进行管理，建议使用flutter_boost，阿里闲鱼出品，牛比！。
 *      [flutter_boost的github链接](https://github.com/alibaba/flutter_boost)
 *      [flutter_boost的flutter pub链接](https://pub.flutter-io.cn/packages/flutter_boost)
 */
object FlutterEngineManager {
    private const val UNIQUE_ENGINE_NAME = "demo_engine_id"

    @Deprecated("用cacheEngines")
    private var flutterEngine: FlutterEngine? = null

    private val cacheEngines: MutableMap<String, SoftReference<FlutterEngine>> = mutableMapOf()

    fun contains(engineId: String) = cacheEngines.containsKey(engineId)
    fun get(engineId: String): FlutterEngine? = cacheEngines[engineId]?.get()

    fun createEngine(context: Application, engineId: String, /*dartEntryPointFunctionName: String = "main",*/ initRoute: String? = null, vararg dartVmArgs: String) {
        if (contains(engineId)) {
            throw IllegalStateException("Already has a unrelease engine!")
        }
        val flutterEngine: FlutterEngine = FlutterEngine(context, dartVmArgs).apply {
            initRoute?.let {
                navigationChannel.setInitialRoute(it)
            }
            /*dartExecutor.executeDartEntrypoint(
                DartExecutor.DartEntrypoint(FlutterMain.findAppBundlePath(), dartEntryPointFunctionName)
            )*/
        }
        FlutterEngineCache.getInstance().put(engineId, flutterEngine)
        cacheEngines[engineId] = SoftReference(flutterEngine)
    }

    fun destroyEngine(engineId: String) {
        if (contains(engineId)) {
            val flutterEngine = get(engineId)
            flutterEngine?.apply {
                FlutterEngineCache.getInstance().remove(engineId)
                destroy()
                cacheEngines.remove(engineId)
            }
        }
    }

    /**
     * 创建一个新的engine
     */
    @JvmStatic
    fun createEngine(context: Application, vararg dartVmArgs: String) {
        if (flutterEngine != null) {
            throw IllegalStateException("Already has a unrelease engine!")
        }
        flutterEngine = FlutterEngine(context, dartVmArgs)
        flutterEngine?.navigationChannel?.setInitialRoute("/page/me")
        flutterEngine?.dartExecutor?.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache
            .getInstance()
            .put(UNIQUE_ENGINE_NAME, flutterEngine)
    }


    /**
     * 释放flutter引擎
     */
    @JvmStatic
    fun releaseFlutterEngine() {
        flutterEngine?.let { engine ->
            FlutterEngineCache.getInstance().remove(UNIQUE_ENGINE_NAME)
            engine.destroy()
        }
        flutterEngine = null
    }
}