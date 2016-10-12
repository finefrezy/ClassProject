package com.elife.classproject.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.elife.classproject.R;
import com.elife.classproject.application.MyApplication;

public class Stanardctivity extends AppCompatActivity {

    private static final String TAG = Stanardctivity.class.getSimpleName();

    TextView mTv;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stanardctivity);
        mTv = (TextView) findViewById(R.id.tv);
        mTv.setText("123");


        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Stanardctivity.this, SingleActivity.class);
                Log.d("=================", "---" + MyApplication.MY_APPLICATION.getI());
                startActivity(intent);
            }
        });

        Log.e(TAG, mTv.hashCode() +  "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, mTv.hashCode() + "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }
}
