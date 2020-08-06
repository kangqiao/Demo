package com.zp.android.demo;

import android.app.Application;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class DemoApplication extends Application {

    private FlutterEngine flutterEngine;

    private static DemoApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        flutterEngine = new FlutterEngine(this);
        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        FlutterEngineCache.getInstance().put("demo_engine_id", flutterEngine);
    }

    public static DemoApplication getInstance() {
        return instance;
    }

    public FlutterEngine getFlutterEngine() {
        return flutterEngine;
    }

    @Override
    public void onTerminate() {
        flutterEngine.destroy();
        super.onTerminate();
        instance = null;
    }
}
