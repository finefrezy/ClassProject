package com.elife.classproject.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.elife.classproject.constant.ConstantData;

/**
 * Created by tzhang on 2016/9/8.
 */
public class BcReceiver extends BroadcastReceiver {

    Handler mHandler;

    public BcReceiver() {

    }

    public BcReceiver(Handler handler) {
        mHandler = handler;
    }


    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
//            Toast.makeText(context, "123", Toast.LENGTH_SHORT).show();
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getDataString().substring(8);

//            Toast.makeText(context, packageName, Toast.LENGTH_SHORT).show();
        }
        //接收广播：设备上删除了一个应用程序包。
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
//            Toast.makeText(context, "remove", Toast.LENGTH_SHORT).show();
        }

        if (intent.getAction().equals(ConstantData.BORAD_SELF_ACTION)) {
            Toast.makeText(context, "自定义广播", Toast.LENGTH_SHORT).show();

//            Message msg = mHandler.obtainMessage();

            mHandler.sendEmptyMessage(0);
        }

    }
}
