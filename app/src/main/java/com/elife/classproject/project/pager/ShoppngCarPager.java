package com.elife.classproject.project.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.elife.classproject.project.base.BasePager;


/**
 * Created by tzhang on 2016/9/22.
 */
public class ShoppngCarPager extends BasePager {
    TextView tv;

    public ShoppngCarPager(Context ctx) {
        super(ctx);
    }

    @Override
    public View initView() {
        Log.e("ShoppngCarPager", "initView");
        mPageRootView  = new TextView(mContex);

        return mPageRootView;
    }

    @Override
    public void initData() {
        Log.e("ShoppngCarPager", "initData");
        ((TextView) mPageRootView).setText("购物车");
    }


}
