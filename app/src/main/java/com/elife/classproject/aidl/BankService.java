package com.elife.classproject.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BankService extends Service {
    public BankService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return new BankBinder();
    }
}
