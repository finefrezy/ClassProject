package com.elife.classproject.netframe;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.elife.classproject.R;
import com.elife.classproject.network.GoodModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGetBtn;
    private ListView mListView;
    private List<GoodModel> mGoodModelList;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            switch (msg.what) {
                case 0:
                    mGoodModelList = (List<GoodModel>) msg.obj;
                    setData();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        mGetBtn = (Button) findViewById(R.id.get_btn);
        mListView = (ListView) findViewById(R.id.list);
        mGetBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_btn:
//                RetrofitManager.setsHandler(mHandler);
//                RetrofitManager.getGoods();
//                RetrofitManager.getUser("110", "123456");

//                Map<String,String> map = new HashMap<String, String>();
//                map.put("userphone","110");
//                map.put("password","123456");
//                RetrofitManager.getUserMap(map);

//                GoodModel goodModel = new GoodModel(1,"12","1213",12.2,"ds","dfsa",12.5);
//                RetrofitManager.postObject(goodModel);

//                RetrofitManager.formLogin("110","123456");
//                RetrofitManager.upPhoto();
                List<File> list = new ArrayList<File>();
                File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "wel_img_one.png");
                list.add(file);
                File file1 = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "clock_pic_secretary.jpg");
                list.add(file1);
                File file2 = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "msg_icn_daren.png");
                list.add(file2);
                RetrofitManager.uploadFile(list, "110", "图片上传");

                break;
        }
    }

    /**
     * 以这种写法为主
     */
    public void getGoods() {
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
                    mGoodModelList = response.body();
                    setData();
                } else {

                }
            }

            // 返回http状态码非成功的回调方法
            @Override
            public void onFailure(Call<List<GoodModel>> call, Throwable t) {

            }
        });
    }


    public void setData() {
        GoodAdapter goodAdapter = new GoodAdapter(mGoodModelList, this);
        mListView.setAdapter(goodAdapter);
    }
}
