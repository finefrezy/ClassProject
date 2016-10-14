package com.elife.classproject.netframe;

import com.elife.classproject.constant.ConstantData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tzhang on 2016/9/13.
 */
public class RetrofitClient {

    private static RetrofitService sRetrofitService;

    public static RetrofitService getClient() {
        if (sRetrofitService == null) {
// BuildConfig.DEBUG
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);.addInterceptor(logging) .retryOnConnectionFailure(true)
            OkHttpClient okClient = new OkHttpClient.Builder()
                    .build();
            // 拦截器
            // 设置超时时间
            // RXjava 的回调器
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(ConstantData.BASE_URL)
                    .client(okClient)
                    .addConverterFactory(gsonConverterFactory)
                    .build();

            sRetrofitService = client.create(RetrofitService.class);
        }
        return sRetrofitService;
    }


    /**
     * Content-Disposition: form-data; name=”file000”; filename=”HTTP协议详解.pdf”
     Content-Type: application/octet-stream

     –${bound}
     Content-Disposition: form-data; name=”Filename

     ${bound}是Content-Type里boundary的值
     */
}
