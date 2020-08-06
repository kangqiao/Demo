package com.zp.android.net.base;

import android.app.Application;

public interface INetworkRequiredInfo {
    String getAppVersionName();

    String getAppVersionCode();

    boolean isDebug();

    Application getApplicationContext();
}
