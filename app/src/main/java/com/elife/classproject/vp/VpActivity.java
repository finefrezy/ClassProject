package com.elife.classproject.vp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.elife.classproject.AllClassActivity;
import com.elife.classproject.R;

import java.util.ArrayList;
import java.util.List;

public class VpActivity extends AppCompatActivity {

    private static final String TAG = VpActivity.class.getSimpleName();
    ViewPager mViewPager;
    List<Integer> mVpList;
    int mPreviousSelected = 0;
    ImageView[] imageViews;
    private LayoutInflater mInflater;

    private Button mBtnJump;
//    private ImageView mImageOne;
//    private ImageView mImageTwo;
//    private ImageView mImagettThree;
//    private ImageView mImageFour;
//    private ImageView mImageFive;

    int i = 0;

    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            i = 0;
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        initView();
        initData();
//        try {
//            Class<?> classType = mViewPager.getClass();
//            Field field = classType.getDeclaredField("DEFAULT_OFFSCREEN_PAGES");
//            field.setAccessible(true);
//
//            field.set(mViewPager, 0);
//
//            Log.e(TAG, "-----destroyItem------" + field.get(mViewPager));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        VpAdapter vpAdapter = new VpAdapter();
        mViewPager.setAdapter(vpAdapter);
//        mViewPager.setOffscreenPageLimit(0);
        mInflater = LayoutInflater.from(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getApplicationContext(), position + "=====", Toast.LENGTH_SHORT).show();

                imageViews[mPreviousSelected].setBackgroundResource(R.drawable.point_gray);

                imageViews[position].setBackgroundResource(R.drawable.point_red);

                if (position == 4) {
                    mBtnJump.setVisibility(View.VISIBLE);
                } else {
                    mBtnJump.setVisibility(View.GONE);
                }

                mPreviousSelected = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mViewPager.setCurrentItem(mPreviousSelected);


        mBtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllClassActivity.class);
                startActivity(intent);
            }
        });

    }


    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.pager);

        imageViews = new ImageView[5];
        imageViews[0] = (ImageView) findViewById(R.id.one);
        imageViews[1] = (ImageView) findViewById(R.id.two);
        imageViews[2] = (ImageView) findViewById(R.id.three);
        imageViews[3] = (ImageView) findViewById(R.id.four);
        imageViews[4] = (ImageView) findViewById(R.id.five);

        mBtnJump = (Button) findViewById(R.id.jump);
    }

    public void initData() {
        mVpList = new ArrayList<Integer>();
        mVpList.add(R.drawable.start_i1);
        mVpList.add(R.drawable.start_i2);
        mVpList.add(R.drawable.start_i3);
        mVpList.add(R.drawable.start_i4);
        mVpList.add(R.drawable.start_i5);
    }


    class VpAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (null == mVpList) {
                return 0;
            }
            return mVpList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.e(TAG, "-----destroyItem------" + position);
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
//            ImageView imageView = new ImageView(VpActivity.this);
//            if (position % 2 == 0) {
//                imageView.setImageResource(R.drawable.clock_pic_secretary);
//            } else {
//                imageView.setImageResource(R.drawable.p_login_bg);
//            }
//            container.addView(imageView);
//            return imageView;
            Log.e(TAG, "-----instantiateItem------" + position);
            View view = mInflater.inflate(R.layout.vp_pager, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_iv);
            imageView.setImageResource(mVpList.get(position));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
                }
            });

            container.addView(view);

            return view;
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mHander.sendEmptyMessageDelayed(0, 3000);
        Log.e(TAG, "-----onKeyDown------" + keyCode + "---" + i );
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            i ++;
            if (i == 2) {
                finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}

