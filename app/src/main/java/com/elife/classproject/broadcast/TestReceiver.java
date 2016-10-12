package com.elife.classproject.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.elife.classproject.MainActivity;
import com.elife.classproject.constant.ConstantData;

/**
 * Created by tzhang on 2016/9/14.
 * 广播接收者-》接收广播 《--给他注册什么广播就接受什么广播
 */
public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        if (Intent.ACTION_TIME_CHANGED.equals(intent.getAction())) {
            Toast.makeText(context, Intent.ACTION_TIME_CHANGED, Toast.LENGTH_SHORT).show();
        } else if (ConstantData.TEST_BROAD_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        }

        Intent i = new Intent(context, MainActivity.class);
        // catagory
        // flags
        context.startActivity(i);

    }
}
