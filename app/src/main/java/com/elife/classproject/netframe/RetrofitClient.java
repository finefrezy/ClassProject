package com.elife.classproject.netframe;

import com.elife.classproject.constant.ConstantData;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tzhang on 2016/9/13.
 */
public class RetrofitClient {

    private static RetrofitService sRetrofitService;

    public static RetrofitService getClient() {
        if (sRetrofitService == null) {
            OkHttpClient okClient = new OkHttpClient();
            // 拦截器
            // 设置超时时间
            // RXjava 的回调器
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(ConstantData.BASE_URL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sRetrofitService = client.create(RetrofitService.class);
        }
        return sRetrofitService;
    }

}
