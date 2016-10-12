package com.elife.classproject.netframe;

import com.elife.classproject.network.GoodModel;
import com.elife.classproject.network.UserModel;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by tzhang on 2016/9/13.
 */
public interface RetrofitService {

    @GET("/elife/{link}")
    Call<List<GoodModel>> getGoods(@Path("link") String link);


    // 测试登录功能，讲解get传参
    @GET("/elife/{link}")
    Call<UserModel> login(@Path("link") String link, @Query("userphone") String phone, @Query("password") String pwd);


    @GET("/elife/{link}")
    Call<UserModel> loginMap(@Path("link") String link, @QueryMap Map<String,String> map);

    @POST("/elife/{link}?userphone=110&password=123456")
    Call<UserModel> postObject(@Path("link") String link, @Body GoodModel goodModel);


    @FormUrlEncoded
    @POST("/elife/{link}")
    Call<UserModel> formLogin(@Path("link") String link, @Field("userphone") String first, @Field("password") String last);


    @Multipart
    @POST("/elife/{link}?user=123")
    Call<ResponseBody> upPhoto(@Path("link") String link, @Part("photo") RequestBody photo, @Part("music") RequestBody music);

// 返回值泛型为String时怎么处理


    // Header Manipulation

    //    @Headers("Cache-Control: max-age=640000")
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/rongserver/{link}")
    Call<ResponseBody> headerTest(@Path("link") String link);


    // Note that headers do not overwrite each other. All headers with the same name will be included in the request.
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("users/{username}")
    Call<ResponseBody> getUser(@Path("username") String username);

    //A request Header can be updated dynamically using the @Header annotation. A corresponding parameter
    // must be provided to the @Header. If the value is null, the header will be omitted(遗漏，忽略). Otherwise, toString will
    // be called on the value, and the result used.
    @GET("/rongserver/{link}")
    Call<ResponseBody> changeHeader(@Path("link") String link, @Header("User-Agent") String agent);
}
