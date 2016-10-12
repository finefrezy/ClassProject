package com.elife.classproject.eventinter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by tzhang on 2016/9/20.
 */
public class MyRelative extends RelativeLayout{
    public MyRelative(Context context) {
        super(context);
    }

    public MyRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("MyRelative", "onInterceptTouchEvent");
// 如果返回false，表示不拦截，如果返回true表示要拦截
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 如果返回false，表示不拦截，如果返回true表示要拦截
        Log.e("MyRelative", "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
