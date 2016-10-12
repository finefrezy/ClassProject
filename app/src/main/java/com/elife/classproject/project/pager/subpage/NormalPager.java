package com.elife.classproject.project.pager.subpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.elife.classproject.R;
import com.elife.classproject.project.adapter.NormalAdapter;
import com.elife.classproject.project.base.BasePager;
import com.elife.classproject.project.constant.ConstantData;
import com.elife.classproject.project.encry.Encryption;
import com.elife.classproject.project.internet.RetrofitClient;
import com.elife.classproject.project.internet.RetrofitService;
import com.elife.classproject.project.model.CategoryGoodsModel;
import com.elife.classproject.project.model.CategoryModel;
import com.elife.classproject.project.model.Sign;
import com.elife.classproject.recycler.waterfall.SpacesItemDecoration;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tzhang on 2016/9/24.
 */
public class NormalPager extends BasePager {

    private RecyclerView mRecyclerNormal;
    // 请求第几页
    private int mRequestPage = 1;
    // 服务器分类数据
    private CategoryModel mCategoryModel;
    // recyclerview加载的adapter
    private NormalAdapter mNormalAdapter;


    private List<CategoryGoodsModel.GoodDetailModel> mList;

    private Map<String, String> requestMap;

    private int totalPage = 0;

    public NormalPager(Context context, CategoryModel categoryModel) {
        super(context);
        this.mCategoryModel = categoryModel;
    }


    @Override
    public View initView() {

        Log.e("NormalPager", "initView");

        mPageRootView = View.inflate(mContex, R.layout.pager_normal, null);
        mRecyclerNormal = (RecyclerView) mPageRootView.findViewById(R.id.recycler_view);

        mRecyclerNormal.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        SpacesItemDecoration decoration = new SpacesItemDecoration(6);
        mRecyclerNormal.addItemDecoration(decoration);

        mList = new ArrayList<CategoryGoodsModel.GoodDetailModel>();
        mNormalAdapter = new NormalAdapter(mList, mContex);
        mRecyclerNormal.setAdapter(mNormalAdapter);

        mRecyclerNormal.addOnScrollListener(new RecyclerView.OnScrollListener() {

            //用来标记是否正在向最后一个滑动，既是否向下滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                Log.e("-------------", "onScrolled--dy=" + dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
                }
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.e("-------------", "onScrollStateChanged");
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                    //获取最后一个完全显示的ItemPosition  span->跨度
                    Log.e("-------------", "manager.getSpanCount()==" + manager.getSpanCount());
//                    int[] lastVisiblePositions = manager.findLastCompletelyVisibleItemPositions(new int[manager.getSpanCount()]);
                    int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
                    // 可见item的最大索引
                    int lastVisiblePos = getMaxElem(lastVisiblePositions);
                    int totalItemCount = manager.getItemCount();
                    // 判断是否滚动到底部
                    if (lastVisiblePos == (totalItemCount - 1) && isSlidingToLast) {
                        Toast.makeText(mContex, "123", Toast.LENGTH_SHORT).show();
                        mRequestPage = mRequestPage + 1;
                        if (mRequestPage > totalPage) {
                            return;
                        } else {
                            loadMore();
                        }

                    }
                }
            }
        });

        return mPageRootView;
    }

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            Log.e("-------------", "getMaxElem()==" + arr[i]);
            if (arr[i] > maxVal) {

                maxVal = arr[i];
            }

        }
        return maxVal;
    }

    @Override
    public void initData() {

        Log.e("NormalPager", "initData");

        String requestStr = requestEncryStr();
        if (!TextUtils.isEmpty(requestStr)) {
            RetrofitService retrofitService = RetrofitClient.getClient();
            Call<CategoryGoodsModel> call = retrofitService.getCateGoodList(requestStr);

            // 异步请求
            call.enqueue(new Callback<CategoryGoodsModel>() {
                @Override
                public void onResponse(Call<CategoryGoodsModel> call, Response<CategoryGoodsModel> response) {
                    CategoryGoodsModel responseData = response.body();
                    mList.clear();
                    if (null != responseData && null != responseData.item_list) {

                        totalPage = responseData.totalPage;
                        mList.addAll(responseData.item_list);
                    }
                    mNormalAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<CategoryGoodsModel> call, Throwable t) {

                }
            });
        }

    }


    public void loadMore() {
        String requestStr = requestEncryStr();
        if (!TextUtils.isEmpty(requestStr)) {
            RetrofitService retrofitService = RetrofitClient.getClient();
            Call<CategoryGoodsModel> call = retrofitService.getCateGoodList(requestStr);

            // 异步请求
            call.enqueue(new Callback<CategoryGoodsModel>() {
                @Override
                public void onResponse(Call<CategoryGoodsModel> call, Response<CategoryGoodsModel> response) {
                    CategoryGoodsModel responseData = response.body();
                    if (null != responseData && null != responseData.item_list) {
                        totalPage = responseData.totalPage;

                        mList.addAll(responseData.item_list);
                        mNormalAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<CategoryGoodsModel> call, Throwable t) {

                }
            });
        }
    }

    public String requestEncryStr() {
        Gson gson = new Gson();
        if (null == requestMap) {
            requestMap = new HashMap<String, String>();
            requestMap.put("page", mRequestPage + "");
            requestMap.put("cid", mCategoryModel.id + "");
            requestMap.put("size", "6");
            requestMap.put("key", ConstantData.AUTH_KEY);
        } else {
            requestMap.put("page", mRequestPage + "");
        }


        Sign encryData = Encryption.encryption(gson.toJson(requestMap));
        if (null == encryData) {
            return "";
        }
        return gson.toJson(encryData);
    }

}
