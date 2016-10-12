package com.elife.classproject.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elife.classproject.R;

/**
 * banner的使用
 *
 */
public class HandlerActivity extends AppCompatActivity {

    TextView mTextView;
    Button mButton;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mTextView.setText(msg.what + "");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);


        mTextView = (TextView) findViewById(R.id.tv);
        mButton = (Button) findViewById(R.id.btn);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.sendEmptyMessage(0);
                Message msg = mHandler.obtainMessage();
                msg.what = 1;
                msg.obj = "123";
                mHandler.sendMessage(msg);

                // 延迟1s发送handler消息
                mHandler.sendEmptyMessageDelayed(2,2000);


                mHandler.sendEmptyMessageDelayed(3,5000);

//                mHandler.sendEmptyMessageAtTime(3, System.currentTimeMillis() + 5000);

                mHandler.removeMessages(3);
                mHandler.removeMessages(3,"123");
            }
        });
    }
}
