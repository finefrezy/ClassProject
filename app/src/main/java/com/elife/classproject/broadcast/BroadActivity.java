package com.elife.classproject.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.elife.classproject.R;
import com.elife.classproject.constant.ConstantData;

public class BroadActivity extends AppCompatActivity {

    Button mBroadBtn;
    BcReceiver broadCastReceiver;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mBroadBtn.setText("广播已处理");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);

        mBroadBtn = (Button) findViewById(R.id.btn_broad);
        mBroadBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(ConstantData.BORAD_SELF_ACTION);
                sendBroadcast(intent);
            }
        });

        //生成广播处理
        broadCastReceiver = new BcReceiver();
        broadCastReceiver.setHandler(mHandler);
        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter(ConstantData.BORAD_SELF_ACTION);
        //注册广播
        registerReceiver(broadCastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {

        unregisterReceiver(broadCastReceiver);
        super.onDestroy();
    }
}
