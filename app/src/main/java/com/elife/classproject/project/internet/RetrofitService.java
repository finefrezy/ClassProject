package com.elife.classproject.project.internet;

import com.elife.classproject.project.model.CategoryGoodsModel;
import com.elife.classproject.project.model.DiscoverModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tzhang on 2016/9/23.
 */
public interface RetrofitService {

    // 获取首页分类标题
    @FormUrlEncoded
    @POST("/index.php?g=api_test&m=Item&a=getCate")
    Call<ResponseBody> getCate(@Field("JsonArray") String json);


    // 获取发现数据
    @FormUrlEncoded
    @POST("/index.php?g=api_test&m=Item&a=getDiscover")
    Call<List<DiscoverModel>> getDiscover(@Field("JsonArray") String json);

    // 获取分类数据
    @FormUrlEncoded
    @POST("/index.php?g=api_test&m=Item&a=getItemsByCate")
    Call<CategoryGoodsModel> getCateGoodList(@Field("JsonArray") String json);



}
