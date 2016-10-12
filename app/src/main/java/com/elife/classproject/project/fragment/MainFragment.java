package com.elife.classproject.project.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.elife.classproject.R;
import com.elife.classproject.project.ProjectActivity;
import com.elife.classproject.project.base.BaseFragment;
import com.elife.classproject.project.base.BasePager;
import com.elife.classproject.project.pager.DiscoverPager;
import com.elife.classproject.project.pager.HobbyPager;
import com.elife.classproject.project.pager.HomePager;
import com.elife.classproject.project.pager.MyPager;
import com.elife.classproject.project.pager.ShoppngCarPager;
import com.elife.classproject.project.view.BanScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {

    public static final String TAG = MainFragment.class.getSimpleName();

    private BanScrollViewPager mViewPager;
    private RadioGroup mRadioGroup;

    private List<BasePager>   mPagerList;
    



    // 数据的初始化
    @Override
    public void initData() {

        Log.e("MainFragment", "initData");
        mPagerList = new ArrayList<BasePager>();
        HomePager homePager = new HomePager(getActivity());
        mPagerList.add(homePager);
        DiscoverPager discoverPager = new DiscoverPager(getActivity());
        mPagerList.add(discoverPager);
        ShoppngCarPager shoppngCarPager = new ShoppngCarPager(getActivity());
        mPagerList.add(shoppngCarPager);
        MyPager myPager = new MyPager(getActivity());
        mPagerList.add(myPager);
        HobbyPager hobbyPager = new HobbyPager(getActivity());
        mPagerList.add(hobbyPager);

        MainPagerAdapter mpa = new MainPagerAdapter();
        mViewPager.setAdapter(mpa);

        // 第一次打开时，默认初始化第一页
        mPagerList.get(0).initData();
    }

    // View的初始化  onCreateView
    @Override
    public View initView(LayoutInflater inflater) {

        Log.e("MainFragment", "initView");

        // 通过代码方式来设置布局宽高属性
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootView = inflater.inflate(R.layout.fragment_main, null);
        mRootView.setLayoutParams(params);

        mViewPager = (BanScrollViewPager) mRootView.findViewById(R.id.view_pager);
        mRadioGroup = (RadioGroup) mRootView.findViewById(R.id.main_rg);


        

        final ProjectActivity activity = (ProjectActivity) getActivity();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.home:
                        mViewPager.setCurrentItem(0, false);
                        mPagerList.get(0).initData();
                        activity.setmTitleText("首页");

                        break;
                    case R.id.discover:
                        mViewPager.setCurrentItem(1, false);
                        mPagerList.get(1).initData();

                        activity.setmTitleText("发现");

                        hideToolBar();

                        break;
                    case R.id.shoppingcar:
                        mViewPager.setCurrentItem(2, false);
                        mPagerList.get(2).initData();

                        activity.setmTitleText("购物车");
                        break;
                    case R.id.my:
                        mViewPager.setCurrentItem(3, false);
                        mPagerList.get(3).initData();
                        activity.setmTitleText("我的");
                        break;
                    case R.id.hobby:
                        mViewPager.setCurrentItem(4, false);
                        mPagerList.get(4).initData();
                        activity.setmTitleText("食趣");
                        break;

                }
            }
        });


        return mRootView;
    }


    class MainPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view== object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mPagerList.get(position).getmPageRootView();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }



}
