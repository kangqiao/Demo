package com.zp.android.demo;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import io.flutter.embedding.android.FlutterActivity;

public class FlutterContainerActivity extends Activity {



    public void toFlutterPage(View view) {
        //跳转默认路由
        /*Intent intent = FlutterActivity.createDefaultIntent(this);
        startActivity(intent);*/

        //flutter性能优化-缓存引擎优化
        //跳转指定路由
        /*
        Intent intent = FlutterActivity.withNewEngine().initialRoute("third").build(this);
        startActivity(intent);
        startActivity(FlutterActivity.withCachedEngine("demo_engine_id").build(this));
        */

        /*Intent intent = FlutterActivity.withNewEngine().initialRoute("second?{'id:'1'}").build(this);
        intent.setAction(Intent.ACTION_RUN);
        intent.putExtra("route", "second?id=2");
        startActivity(intent);*/

    }
}
