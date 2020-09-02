package com.zp.android.arch.flutter

import android.content.Context
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine


/**
 * https://juejin.im/post/6844904117081473032
 *
 * super.getInitialRoute() = null
 * super.getAppBundlePath() = flutter_assets
 * super.getDartEntrypointFunctionName() = main
 */
class FlutterContainerActivity: FlutterActivity() {


    /**
     * 入口函数
     * 对应 flutter main()函数
     * 默认返回 main
     */
    override fun getDartEntrypointFunctionName(): String {
        return super.getDartEntrypointFunctionName()
    }

    //初始路由路径是'/'，可通过重写Flutter.getInitialRoute()函数来改变FlutterActivity入口，但是不支持使用intent传值的方式来改变入口
    override fun getInitialRoute(): String {
        return super.getInitialRoute()
    }

    //指定Android项目运行时加载的Flutter项目bundle路径
    override fun getAppBundlePath(): String {
        return super.getAppBundlePath()
    }

    //直接指定FlutterEngine缓存对象，这是最为直接的办法。 我推荐使用方法2。
    override fun getCachedEngineId(): String? {
        return super.getCachedEngineId()
    }

    /**
     * 为了解决pre-warm FlutterEngine可能会造成内存浪费的问题，
     * 可以在用户第一次打开Flutter页面时才创建FlutterEngine，将其缓存起来，减少用户再次打开Flutter页面时FlutterEngine的初始化时间。
     * 但是，延迟创建FlutterEngine会出现在第一个Flutter帧渲染出来前出现白屏的情况，为了优化用户体验可以为页面加上Splash Screen。
     */
    //延迟创建FlutterEngine,
    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return super.provideFlutterEngine(context)
    }
}