package com.elife.classproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MService extends Service {
    public MService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    // 初始化工作
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MService", "onCreate");
    }

    // 资源的释放
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MService", "onDestroy");
    }

    // 核心业务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MService", "onStartCommand");

// 自己关掉自己
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}
