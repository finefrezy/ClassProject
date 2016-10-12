package com.elife.classproject.eventinter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.elife.classproject.R;
//http://blog.csdn.net/jdsjlzx/article/details/41145505

/**
 * onIntercept 事件拦截
 * onTouch     事件处理
 */
public class EventActivity extends AppCompatActivity {

    private MyTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        textView = (MyTextView) findViewById(R.id.text);

//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "123", Toast.LENGTH_SHORT).show();
//            }
//        });

//        textView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Toast.makeText(getApplicationContext(), "123", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }
}
