package com.elife.classproject.logistics;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.elife.classproject.R;

/**
 * Created by tzhang on 2015/12/2.
 */
public class PointLinear extends LinearLayout {


    public PointLinear(Context context) {
        super(context);
        addSubView(context);
    }

    public PointLinear(Context context, AttributeSet attrs) {
        super(context, attrs);
        addSubView(context);
    }

    public void addSubView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_logistics, null);
        addView(view);
    }
}
