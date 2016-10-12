package com.elife.classproject.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.elife.classproject.R;

public class Service2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2);

        Intent intent = new Intent(getApplicationContext(), MService.class);
        stopService(intent);

    }
}
