package com.elife.classproject.composite;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by tzhang on 2016/9/7.
 */
public class MyPagerAdapter extends FragmentPagerAdapter{

    List<Fragment> mFragmentList;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);

        this.mFragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (null != mFragmentList) {
            return mFragmentList.size();
        }
        return 0;
    }
}
