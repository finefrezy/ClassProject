package com.elife.classproject.recycler.waterfall;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tzhang on 2016/10/13.
 */
public class WaterHeader extends RecyclerView.ViewHolder {


    public int mPosition;

    public TextView textView;


    public WaterHeader(View itemView) {
        super(itemView);
        ;
        textView = (TextView) itemView;
    }
}