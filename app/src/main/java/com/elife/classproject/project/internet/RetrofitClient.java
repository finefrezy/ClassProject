package com.elife.classproject.project.internet;


import com.elife.classproject.project.constant.ConstantData;

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
                    .baseUrl(ConstantData.SERVER_URL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sRetrofitService = client.create(RetrofitService.class);
        }
        return sRetrofitService;
    }

}
