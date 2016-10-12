package com.elife.classproject.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.elife.classproject.R;
import com.elife.classproject.constant.ConstantData;

public class BroadcastActivity extends AppCompatActivity {


    TestReceiver mTestReceiver;
    Button mBtnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        mBtnSend = (Button) findViewById(R.id.broadcast);



        mTestReceiver = new TestReceiver();
        // 添加action事件，也就是广播事件

        IntentFilter intentFilter = new IntentFilter();
        //  遵循习惯
        intentFilter.addAction(ConstantData.TEST_BROAD_ACTION);

        // 为TestReceiver注册了Intent.ACTION_TIME_CHANGED的一个时间，当这个系统事件发生时，系统会发出这个事件的广播，
        // TestReceiver就会接收到这个广播，因为他注册了这个广播事件，所以可以接收到

        // 一旦注册，必须在合适的时机将其解注册----动态注册
        registerReceiver(mTestReceiver, intentFilter);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(ConstantData.TEST_BROAD_ACTION);
                sendBroadcast(intent);


            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(null != mTestReceiver) {
            unregisterReceiver(mTestReceiver);
        }
    }
}
