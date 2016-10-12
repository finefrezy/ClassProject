package com.elife.classproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class ProgressActivity extends AppCompatActivity {


    ProgressBar mPb;
    Button mBtn;
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        mPb = (ProgressBar) findViewById(R.id.pb);
        mSeekBar = (SeekBar) findViewById(R.id.seek);

//        mPb.setProgress(40);
        Log.e("ProgressActiv.ity", mPb.getProgress() + "");
        Log.e("ProgressActivity", mPb.getSecondaryProgress() + "");


        mBtn = (Button) findViewById(R.id.btn_pro);


       // INVISIBLE虽然隐藏，但是屏幕上依然占据自己的位置
       // GONE 隐藏并释放在屏幕上占据的位置
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPb.getVisibility() == View.VISIBLE) {
                    mPb.setVisibility(View.INVISIBLE);
                } else {
                    mPb.setVisibility(View.VISIBLE);
                }

                mSeekBar.setProgress(12);
            }
        });


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // fromUser直接来自于用户拖动为true，否则为false
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("OnSeekBarChange", "onProgressChanged-> i = " + progress + ", b = " + fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("OnSeekBarChange", "onStartTrackingTouch->  ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("OnSeekBarChange", "onStopTrackingTouch->  ");
            }
        });
    }
}
