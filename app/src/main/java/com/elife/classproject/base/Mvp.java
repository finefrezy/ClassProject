package com.elife.classproject.base;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by tzhang on 2016/9/6.
 */
public class Mvp extends ViewPager {
    private boolean isCanScroll = false;
    private ViewPager viewPager;

    public Mvp(Context context) {

         super(context);
    }

    public Mvp(Context context, AttributeSet attrs) {
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
//
//@Override
//    public void setOffscreenPageLimit(int limit) {
//        Class<?> classType = super.getClass();
//        Field field = null;
//        Method m = null;
//        try {
//            field = classType.getDeclaredField("mOffscreenPageLimit");
//            field.setAccessible(true);
//
//            try {
//                field.set(this,0);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
////            mOffscreenPageLimit = limit;
//            m = classType.getDeclaredMethod("populate");
//            m.invoke(this);
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//    }

}
