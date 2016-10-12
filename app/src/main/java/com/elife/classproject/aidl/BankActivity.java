package com.elife.classproject.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elife.classproject.R;

public class BankActivity extends AppCompatActivity {
    // 服务端binder对象
    private BankBinder mBankBinder;
    // 显示处理结果
    private TextView mTxView;

    private Button mBtnOpen;
    private Button mBtnClose;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBankBinder = (BankBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        bindService(new Intent("com.elife.classproject.aidl.BankService"), connection, BIND_AUTO_CREATE);
        mTxView = (TextView) findViewById(R.id.tv_content);
        mBtnOpen = (Button) findViewById(R.id.open);
        mBtnClose = (Button) findViewById(R.id.close);

        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBankBinder.openAccount("zhangtao","123456");
            }
        });

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBankBinder.closeAccount("zhangtao","123456");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
