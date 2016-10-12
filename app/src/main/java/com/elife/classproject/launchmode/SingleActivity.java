package com.elife.classproject.launchmode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.elife.classproject.R;
import com.elife.classproject.application.MyApplication;

public class SingleActivity extends AppCompatActivity {

    Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        mBtn = (Button) findViewById(R.id.btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("=================", "" + MyApplication.MY_APPLICATION.getI());
            }
        });
    }
}
