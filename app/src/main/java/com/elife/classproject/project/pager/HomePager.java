package com.elife.classproject.project.pager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elife.classproject.R;
import com.elife.classproject.project.base.BasePager;
import com.elife.classproject.project.constant.ConstantData;
import com.elife.classproject.project.encry.Encryption;
import com.elife.classproject.project.indicator.PagerSlidingTabStrip;
import com.elife.classproject.project.internet.RetrofitClient;
import com.elife.classproject.project.internet.RetrofitService;
import com.elife.classproject.project.model.CategoryModel;
import com.elife.classproject.project.model.Sign;
import com.elife.classproject.project.model.UserModel;
import com.elife.classproject.project.pager.subpage.NormalPager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tzhang on 2016/9/22.
 * http://cxg.139club.com/index.php?g=api_test&m=Item&a=getCate
 */
public class HomePager extends BasePager {

    private ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerSlidingStrip;

    private List<CategoryModel> mListTitle;

    private HomePagerAdapter mHomePageAdapter;

    private List<BasePager> mCatePagers;

    public HomePager(Context ctx) {
        super(ctx);
    }

    @Override
    public View initView() {

        Log.e("HomePager", "initView");
        // 解析布局获取view
        mPageRootView = View.inflate(mContex, R.layout.pager_home, null);
        mPagerSlidingStrip = (PagerSlidingTabStrip) mPageRootView.findViewById(R.id.tabs);
        mViewPager = (ViewPager) mPageRootView.findViewById(R.id.view_pager_home);
        mPagerSlidingStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(mContex, "positon = " + position, Toast.LENGTH_SHORT).show();
                mCatePagers.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initTabsValue();

        return mPageRootView;
    }

    @Override
    public void initData() {

        Log.e("HomePager", "initData");

        RetrofitService retrofitService = RetrofitClient.getClient();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        UserModel user = new UserModel();
        user.key = ConstantData.AUTH_KEY;
        String json = gson.toJson(user);

        Sign encryData = Encryption.encryption(json);
        if (null == encryData) {
            return;
        }
        // usermodel->json->AES加密(得到一个字符串)->转成SignModel对象（只有一个字段叫sign
        // 将AES加密后的字符串赋值给SignModel对象的sigh字段
        // 将signmodel对象转换为json字符串发送到服务器
// 对象-》json->singmodel对象-》json-》发送到服务器

        String requestStr = gson.toJson(encryData);
        Call<ResponseBody> call = retrofitService.getCate(requestStr);

        // 异步请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseData = response.body().string();
                    // 同一个界面会有对个不同的网络请求发出，所以设置标志位来确定有哪一个接口发出的
                    String flag = responseData.substring(0, 1);

                    String reallyData = responseData.substring(1, responseData.length());

                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<CategoryModel>>() {
                    }.getType();

                    mListTitle = gson.fromJson(reallyData, type);

                    if (null == mHomePageAdapter) {

                        // ViewPager采用BasePager，根据服务器返回创建Pager并保存到集合中
                        if (null != mListTitle) {
                            mCatePagers = new ArrayList<BasePager>();
                            for (int i = 0; i < mListTitle.size(); i++) {
                                NormalPager normalPager = new NormalPager(mContex, mListTitle.get(i));
                                mCatePagers.add(normalPager);
                                if (i == 0) {
                                    mCatePagers.get(0).initData();
                                }
                            }
                        }

                        mHomePageAdapter = new HomePagerAdapter();
                        mViewPager.setAdapter(mHomePageAdapter);
                        mPagerSlidingStrip.setViewPager(mViewPager);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

    /**
     * mPageSliding默认值配置
     */
    private void initTabsValue() {
        // 底部游标颜色
        mPagerSlidingStrip.setIndicatorColor(Color.TRANSPARENT);
        // tab的分割线颜色
        mPagerSlidingStrip.setDividerColor(Color.TRANSPARENT);
        // tab背景
        mPagerSlidingStrip.setBackgroundColor(Color.TRANSPARENT);
        // tab底线高度
        mPagerSlidingStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, mContex.getResources().getDisplayMetrics()));
        // 游标高度  选中时的那条线
        mPagerSlidingStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5, mContex.getResources().getDisplayMetrics()));
        mPagerSlidingStrip.setSelectedTextSize(18.0f);
        // 选中的文字颜色
        mPagerSlidingStrip.setSelectedTextColor(Color.parseColor("#80ca1a"));
        // 正常文字颜色
        mPagerSlidingStrip.setTextColor(Color.parseColor("#fcc600"));
    }

    class HomePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mListTitle.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ;
            container.addView(mCatePagers.get(position).getmPageRootView());
            return mCatePagers.get(position).getmPageRootView();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mListTitle.get(position).name;
        }
    }


}
