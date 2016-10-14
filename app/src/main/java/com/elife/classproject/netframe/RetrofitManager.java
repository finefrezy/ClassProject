package com.elife.classproject.netframe;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.elife.classproject.network.GoodModel;
import com.elife.classproject.network.UserModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tzhang on 2016/9/13.
 * handler 子线程或其他类中返回主线程（主类）
 * 回调 从其他类中返回主类（他们都在一个线程）
 */
public class RetrofitManager {


    private static Handler sHandler;

    public static void setsHandler(Handler handler) {
        sHandler = handler;
    }


    public static void getGoods() {

        RetrofitService retrofitService = RetrofitClient.getClient();
// 创建有一个回调对象
        Call<List<GoodModel>> call = retrofitService.getGoods("good");
        // 用回调对象发起请求
        call.enqueue(new Callback<List<GoodModel>>() {

            // 回调方法
            @Override
            public void onResponse(Call<List<GoodModel>> call, Response<List<GoodModel>> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    List<GoodModel> result = response.body();
                    Log.e("RetrofitManager", result.size() + "-----------");

                    Message msg = sHandler.obtainMessage();
                    msg.what = 0;
                    msg.obj = result;
                    sHandler.sendMessage(msg);

                } else {

                }
            }

            // 返回http状态码非成功的回调方法
            @Override
            public void onFailure(Call<List<GoodModel>> call, Throwable t) {

            }
        });

    }

    public static void getUser(String userPhone, String userPwd) {
        RetrofitService retrofitService = RetrofitClient.getClient();
        // 创建有一个回调对象
        Call<UserModel> call = retrofitService.login("user", userPhone, userPwd);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                // [200, 300)
                if (response.isSuccessful()) {
                    UserModel result = response.body();

                    Log.e("RetrofitManager", result.getUserPhone() + result.getUserSignature());

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }


    public static void getUserMap(Map<String, String> map) {
        RetrofitService retrofitService = RetrofitClient.getClient();
        // 创建有一个回调对象
        Call<UserModel> call = retrofitService.loginMap("user", map);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                // [200, 300)
                if (response.isSuccessful()) {
                    UserModel result = response.body();

                    Log.e("RetrofitManager--", result.getUserPhone() + result.getUserSignature());

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }


    public static void postObject(GoodModel goodModel) {
        RetrofitService retrofitService = RetrofitClient.getClient();
        // 创建有一个回调对象
        Call<UserModel> call = retrofitService.postObject("user", goodModel);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel result = response.body();

                    Log.e("RetrofitManager--", result.getUserPhone() + result.getUserSignature());

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }


    public static void formLogin(String userPhone, String pwd) {
        RetrofitService retrofitService = RetrofitClient.getClient();
        // 创建有一个回调对象
        Call<UserModel> call = retrofitService.formLogin("user", userPhone, pwd);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel result = response.body();

                    Log.e("RetrofitManager--", result.getUserPhone() + result.getUserSignature());

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }


    public static void upPhoto() {
        RetrofitService retrofitService = RetrofitClient.getClient();

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "wel_img_one.png");
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/octet-stream"), file);

        File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "aa.mp3");

//        String descriptionString = "这是我上传的图片";
//        RequestBody description =
//                RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        RequestBody music =
                RequestBody.create(MediaType.parse("application/octet-stream"), file1);

        Call<ResponseBody> call = retrofitService.upPhoto("ful", requestBody, music);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.e("RetrofitManager", "response = " + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    /**
     * 用于把File对象转化成MultipartBody
     *
     * @param files
     * @return
     */
    private static MultipartBody filesToMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

    /**
     * 把File转化成MultipartBody.Part:
     *
     * @param files
     * @return
     */
    private static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    /**
     * http://blog.csdn.net/jdsjlzx/article/details/51649382
     *
     * @param files
     * @param userId
     * @param info
     */


    public static void uploadFile(List<File> files, String userId, String info) {
        RetrofitService retrofitService = RetrofitClient.getClient();
        Call<BaseResponse<String>> call = retrofitService.uploadFilesWithParts("mfs", filesToMultipartBodyParts(files), userId, info);

        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                if (response.isSuccessful()) {
                    Log.e("RetrofitManager", "succ---uploadFile = " + response.body().msg);
                } else {
                    Log.e("RetrofitManager", "fail---uploadFile = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                Log.e("RetrofitManager", "error---uploadFile = " + t.getMessage());
            }
        });
    }

}
