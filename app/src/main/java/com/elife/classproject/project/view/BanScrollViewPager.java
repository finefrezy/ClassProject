package com.elife.classproject.project.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by tzhang on 2016/9/22.
 */
public class BanScrollViewPager extends ViewPager {

    private boolean isCanScroll = false;
    private ViewPager viewPager;

    public BanScrollViewPager(Context context) {

        super(context);
    }

    public BanScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setCanScroll(boolean canScroll) {
        isCanScroll = canScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (isCanScroll) {
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isCanScroll) {
            return super.onInterceptTouchEvent(arg0);
        } else {
            return false;
        }
    }
}
