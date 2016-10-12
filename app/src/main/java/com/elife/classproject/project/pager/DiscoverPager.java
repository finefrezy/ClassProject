package com.elife.classproject.project.pager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.elife.classproject.R;
import com.elife.classproject.project.base.BasePager;
import com.elife.classproject.project.constant.ConstantData;
import com.elife.classproject.project.encry.Encryption;
import com.elife.classproject.project.internet.RetrofitClient;
import com.elife.classproject.project.internet.RetrofitService;
import com.elife.classproject.project.model.DiscoverModel;
import com.elife.classproject.project.model.Sign;
import com.elife.classproject.project.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by tzhang on 2016/9/22.
 */
public class DiscoverPager extends BasePager {


    private List<DiscoverModel> mDiscoverList;
    private ImageView mImageLeft;
    private ImageView mImageMiddle;
    private ImageView mImageRight;
    private ImageView mImageBottom;

    public DiscoverPager(Context ctx) {
        super(ctx);
    }

    @Override
    public View initView() {

        Log.e("DiscoverPager", "initView");

        mPageRootView = View.inflate(mContex, R.layout.pager_discover, null);
        mImageLeft = (ImageView) mPageRootView.findViewById(R.id.left);
        mImageMiddle = (ImageView) mPageRootView.findViewById(R.id.middle);
        mImageRight = (ImageView) mPageRootView.findViewById(R.id.right);
        mImageBottom = (ImageView) mPageRootView.findViewById(R.id.bottom);

        return mPageRootView;
    }

    @Override
    public void initData() {

        Log.e("DiscoverPager", "initData");

        RetrofitService retrofitService = RetrofitClient.getClient();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        UserModel user = new UserModel();
        user.key = ConstantData.AUTH_KEY;
        String json = gson.toJson(user);

        Sign encryData = Encryption.encryption(json);
        if (null == encryData) {
            return;
        }

        String requestStr = gson.toJson(encryData);
        Call<List<DiscoverModel>> call = retrofitService.getDiscover(requestStr);

        // 异步请求
        call.enqueue(new Callback<List<DiscoverModel>>() {
            @Override
            public void onResponse(Call<List<DiscoverModel>> call, Response<List<DiscoverModel>> response) {
                mDiscoverList = response.body();
                viewShow();
            }

            @Override
            public void onFailure(Call<List<DiscoverModel>> call, Throwable t) {

            }
        });
    }

    private void viewShow() {

        WindowManager wm = (WindowManager)mContex.getSystemService(Context.WINDOW_SERVICE);

        final Point point = new Point();
        wm.getDefaultDisplay().getSize(point);

        Transformation transformation = new Transformation(){

            @Override
            public Bitmap transform(Bitmap source) {
                int desireWidth = point.x / 3 - 3;
                double ratio = source.getWidth() / desireWidth;
                int desireHeight = (int) (source.getHeight()/ratio);

                if (desireWidth != 0 && desireHeight != 0) {
                    Bitmap result = Bitmap.createScaledBitmap(source, desireWidth, desireHeight, false);
                    if (result != source) {
                        source.recycle();
                    }
                    return result;
                } else {
                    return source;
                }
            }

            @Override
            public String key() {
                return "transformation";
            }
        };

        Picasso.with(mContex).load(ConstantData.SERVER_URL_IMAGE + mDiscoverList.get(0).content).tag(mContex).transform(transformation).into(mImageLeft);
        Picasso.with(mContex).load(ConstantData.SERVER_URL_IMAGE + mDiscoverList.get(1).content).tag(mContex).transform(transformation).into(mImageRight);
        Picasso.with(mContex).load(ConstantData.SERVER_URL_IMAGE + mDiscoverList.get(2).content).tag(mContex).transform(transformation).into(mImageMiddle);
        Picasso.with(mContex).load(ConstantData.SERVER_URL_IMAGE + mDiscoverList.get(3).content).tag(mContex).into(mImageBottom);

    }

}
