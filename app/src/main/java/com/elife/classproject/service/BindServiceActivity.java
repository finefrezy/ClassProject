package com.elife.classproject.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.elife.classproject.R;

public class BindServiceActivity extends AppCompatActivity {


    private Button button;


    private BindService bindService;
    private BindService.MyBind myBind;

    private boolean isBind = false;

    // 先连接在绑定 绑定服务需要一点时间（时间不确定）
    private ServiceConnection conn = new ServiceConnection() {
        /** 获取服务对象时的操作 */
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBind = (BindService.MyBind)service;
            bindService = myBind.getService();
            isBind = true;
        }

        /** 无法获取到服务对象时的操作 */
        public void onServiceDisconnected(ComponentName name) {
            bindService = null;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        button = (Button) findViewById(R.id.btn);

        Intent intent = new Intent(BindServiceActivity.this, BindService.class);
        bindService(intent,conn, Context.BIND_AUTO_CREATE );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != myBind) {
                    myBind.test();
                }

                // 任何时候想解绑service时，调用该方法
                if (isBind) {
                    unbindService(conn);
                    isBind = false;
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
