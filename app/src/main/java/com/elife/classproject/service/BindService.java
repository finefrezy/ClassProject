package com.elife.classproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindService extends Service {

    IBinder myBind;

    public BindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("BindService", "IBinder-》onBind");
        if (null == myBind) {
            myBind = new MyBind();
        }

        return myBind;
    }



    public class MyBind extends Binder {

        public BindService getService() {
            Log.e("BindService", "BindService-》getService");
            return BindService.this;
        }


        // 核心业务可以通过定义方法实现
        public void test() {
            Log.e("BindService", "BindService-》test");
        }

    }
}
