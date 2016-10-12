package com.elife.classproject.anim;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by tzhang on 2016/9/19.
 */
public class ViewWrapper {

    private View target; //目标对象

    public ViewWrapper(View target) {
        this.target = target;
    }

    public int getWidth() {
        return target.getLayoutParams().width;
    }

    public void setWidth(float widthValue) {
        //widthValue的值从100到20变化
        target.setRotationX(widthValue);


        target.postInvalidate();
    }
    public void setMarginTop(int margin) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) target.getLayoutParams();
        layoutParams.setMargins(0, margin, 0, 0);
        target.setLayoutParams(layoutParams);
    }
}
