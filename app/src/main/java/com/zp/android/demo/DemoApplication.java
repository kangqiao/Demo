package com.zp.android.demo;

import android.app.Application;

import com.zp.android.arch.flutter.FlutterEngineManager;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class DemoApplication extends Application {

    @Deprecated
    private FlutterEngine flutterEngine;
    private static final String UNIQUE_ENGINE_NAME = "demo_engine_id";

    private static DemoApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //FlutterEngineManager.createEngine(this);
    }

    public static DemoApplication getInstance() {
        return instance;
    }

    public FlutterEngine getFlutterEngine() {
        return flutterEngine;
    }

    @Override
    public void onTerminate() {
        //FlutterEngineManager.releaseFlutterEngine();
        super.onTerminate();
        instance = null;
    }

//    public void createEngine(Application context, String... args) {
//        if (null != flutterEngine) {
//            throw new IllegalStateException("Already has a unrelease engine!");
//        }
//        flutterEngine = new FlutterEngine(context, args);
//        flutterEngine.getNavigationChannel().setInitialRoute("/page/me");
//        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
//        FlutterEngineCache.getInstance().put(UNIQUE_ENGINE_NAME, flutterEngine);
//    }
//
//    public void releaseFlutterEngine() {
//        if (null != flutterEngine) {
//            FlutterEngineCache.getInstance().remove(UNIQUE_ENGINE_NAME);
//            flutterEngine.destroy();
//        }
//        flutterEngine = null;
//    }
}
