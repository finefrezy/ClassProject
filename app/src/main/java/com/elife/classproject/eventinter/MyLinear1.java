package com.elife.classproject.eventinter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by tzhang on 2016/9/20.
 */
public class MyLinear1 extends LinearLayout{
    public MyLinear1(Context context) {
        super(context);
    }

    public MyLinear1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyLinear1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("MyLinear1", "onInterceptTouchEvent");

        return true;
    }


    /**
     *
     * 当发生一次点击事件时，有两个时间，一个KEY_DOWN， 一个KEY_UP
     * KEY_DOWN事件的处理，按照先拦截在处理的顺序，到处理对应的view的onTouchEvent
     *
     * 对于KEY_UP事件的处理，也是按照先拦截，再处理的顺序，但是已经确定KEY_DWON事件的处理者，那么这个处理者的拦截方法不会再执行
     *
     *  =======回调结合起来一起使用=============
     *
     * @param event
     * @return
     */
    float y = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("MyLinear1", "onTouchEvent" + event.getAction());


        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = event.getY();
                Log.e("DWON", event.getX() + "");
                event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                float deltaY = event.getY() - y;
                Log.e("MOVE", deltaY + "");
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                Log.e("UP", event.getX() + "");
                break;
        }


        return true;
    }
}
