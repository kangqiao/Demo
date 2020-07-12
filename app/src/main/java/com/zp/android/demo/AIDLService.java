package com.zp.android.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.zp.android.demo.utils.LogUtil;

public class AIDLService extends Service {
    private String name;

    private Binder binder = new IPerson.Stub() {
        @Override
        public void setName(String s) throws RemoteException {
            name = s;
        }

        @Override
        public String getName() throws RemoteException {
            return name;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.d("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("onDestroy");
    }
}

