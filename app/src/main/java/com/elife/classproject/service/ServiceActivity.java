package com.elife.classproject.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.elife.classproject.R;

public class ServiceActivity extends AppCompatActivity {

    Button mBtnService;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        mBtnService = (Button) findViewById(R.id.service);
        mBtnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MService.class);
//                if (i < 5) {
                // start方式启动service
                    startService(intent);
//                    i++;
//                } else {
//                    stopService(intent);
//                }

                Intent i = new Intent(getApplicationContext(), Service2Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
