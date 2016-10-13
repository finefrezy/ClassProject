package com.elife.classproject.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elife.classproject.R;

/**
 * 客户端
 *
 * 代理模式 Proxy Pattern
 * 为其他对象提供一种代理以控制对这个对象的访问
 * 当无法或不能想直接访问某个对象时或访问某个对象存在困难时可以通过一个代理对象来间接访问，为了保证客户端使用的透明性，委托对象与代理对象需要实现相同的接口
 * 如，诉讼人与律师,委托关系
 *   接口-》提交申请  举证  开始辩护  诉讼完成
 *
 * AIDL代理模式（跨进程通信--方法调用，Bind机制应用）
 * AIDL接口文件，创建这个的文件后，执行BUILD-MAKE PROECT会根据这个文件生成相应的接口定义与接口实现的java文件（build/generated/source/aidl/debugg/...）
 *
 * 写一个具体的实现类，也就是跨进程通信，要做的事情，实现对应生成文件  类名.Stub，实现里面的方法，也就是接口文件中定义的方法，跨进程通信
 * 真正要做的事情
 *
 *
 */
public class BankActivity extends AppCompatActivity {
    // 服务端binder对象
    private IBankAidl mBankBinder;
    // 显示处理结果
    private TextView mTxView;


    private Button mBtnOpen;
    private Button mBtnClose;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {


            mBankBinder = IBankAidl.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        bindService(new Intent(this, BankService.class), connection, BIND_AUTO_CREATE);
        mTxView = (TextView) findViewById(R.id.tv_content);
        mBtnOpen = (Button) findViewById(R.id.open);
        mBtnClose = (Button) findViewById(R.id.close);

        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mBankBinder) {
                    try {
                        mTxView.setText(mBankBinder.openAccount("zhangtao","123456"));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(BankActivity.this, "服务尚未绑定", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (null != mBankBinder) {
                    try {
                        mTxView.setText(mBankBinder.closeAccount("zhangtao","123456"));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(BankActivity.this, "服务尚未绑定", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
