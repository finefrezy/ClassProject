package com.elife.classproject.composite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.elife.classproject.R;
import com.elife.classproject.base.Mvp;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity  {// implements DiscoverFragment.OnCallBackListener

    RadioGroup mRadioGroup;
    RadioButton mHomeButton;
    RadioButton mDiscoverButton;
    RadioButton mContactButton;
    RadioButton mMyButton;

    private Mvp mViewPager;

    private List<Fragment> mFragmentList;
    private HomeFragment mHomeFragement;
    private DiscoverFragment mDiscoverFragment;
    private ContactFragment mContactFragmentt;
    private MyFragment mMyFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        mHomeButton = (RadioButton) findViewById(R.id.home);
        mDiscoverButton = (RadioButton) findViewById(R.id.discover);
        mContactButton = (RadioButton) findViewById(R.id.contact);
        mMyButton = (RadioButton) findViewById(R.id.my);
        mHomeButton.setChecked(true);


        mRadioGroup = (RadioGroup) findViewById(R.id.bottom_rg);

        mFragmentList = new ArrayList<Fragment>();
        mHomeFragement = new HomeFragment();
        mDiscoverFragment = new DiscoverFragment();
        mContactFragmentt = new ContactFragment();
        mMyFragment = new MyFragment();

        mFragmentList.add(mHomeFragement);
        mFragmentList.add(mDiscoverFragment);
        mFragmentList.add(mContactFragmentt);
        mFragmentList.add(mMyFragment);


        mDiscoverFragment.setOnCallBackListener(new DiscoverFragment.OnCallBackListener() {
            @Override
            public void onCallBack(String str) {
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });



        mViewPager = (Mvp) findViewById(R.id.top_vp);
        // 除了当前现实的界面之外，viewpager还保留几个已经创建的界面
        mViewPager.setOffscreenPageLimit(3);
        MyPagerAdapter mpa = new MyPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mpa);



        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.discover:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.contact:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.my:
                        mViewPager.setCurrentItem(3);
                        break;
                }

            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mHomeButton.setChecked(true);
                        break;
                    case 1:
                        mDiscoverButton.setChecked(true);
                        break;
                    case 2:
                        mContactButton.setChecked(true);
                        break;
                    case 3:
                        mMyButton.setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

//    @Override
//    public void onCallBack(String str) {
//        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
//    }
}
