package com.elife.classproject.project.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elife.classproject.project.ProjectActivity;


/**
 * Created by tzhang on 2016/9/22.
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
       super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = initView(inflater);
        return mRootView;
    }

    public  abstract  void initData();
    public abstract View initView(LayoutInflater inflater);

    public void hideToolBar() {
        ProjectActivity mainActivity =  (ProjectActivity)getActivity();
        mainActivity.hideTooBar();
    }
}
