package com.elife.classproject.eventinter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by tzhang on 2016/9/20.
 */
public class MyLinear2 extends LinearLayout{
    public MyLinear2(Context context) {
        super(context);
    }

    public MyLinear2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyLinear2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("MyLinear2", "onInterceptTouchEvent");

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("MyLinear2", "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
