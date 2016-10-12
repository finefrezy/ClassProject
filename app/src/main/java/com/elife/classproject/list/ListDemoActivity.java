package com.elife.classproject.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.elife.classproject.FirstActivity;
import com.elife.classproject.R;
import com.elife.classproject.adapter.InfoModel;
import com.elife.classproject.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListDemoActivity extends AppCompatActivity implements View.OnClickListener {// AdapterView.OnItemClickListener

    MyAdapter mMyAdapter;
    private ListView mListView;
    private List<InfoModel> mInfoList;
    private Button mBtnRefresh;
    private Button mBtnLoadMore;

//    String[] mStrs = {"Spring", "Summer", "Autum", "Winter","Spring", "Summer", "Autum", "Winter","Spring", "Summer", "Autum", "Winter","Spring", "Summer", "Autum", "Winter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo);

        mListView = (ListView) findViewById(R.id.list);

// 课后了解
//        mListView.setEmptyView();

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs);
//        mListView.setAdapter(adapter);

        mBtnRefresh = (Button) findViewById(R.id.refresh);
        mBtnLoadMore = (Button) findViewById(R.id.more);

        mBtnRefresh.setOnClickListener(this);
        mBtnLoadMore.setOnClickListener(this);

        initData();

        mMyAdapter = new MyAdapter(mInfoList, this);

        mListView.setAdapter(mMyAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(ListDemoActivity.this, "" + i, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ListDemoActivity.this, FirstActivity.class);
                intent.putExtra("infomodel", mInfoList.get(i));
                startActivity(intent);

                // 在跳转界面中获取对象
//                InfoModel infoModel = (InfoModel) intent.getSerializableExtra("infomodel");

            }
        });
    }

    public void initData() {
        mInfoList = new ArrayList<InfoModel>();

        for (int i = 0; i < 10; i++) {
            InfoModel infoModel = new InfoModel("DESCRIBE", R.drawable.btmlay_icn_praise, "NAME----" + i, R.drawable.recommend_icn_birthday_prs, "10:30");
            mInfoList.add(infoModel);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refresh:
                refresh();
                break;
            case R.id.more:
                loadMore();
                break;
        }
    }


    private void refresh() {
        mInfoList.clear();

        List<InfoModel> infoModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            InfoModel infoModel = new InfoModel("describe", R.drawable.btmlay_icn_praise, "name----" + i, R.drawable.index_btn_fav_prs, "11:30");
            infoModels.add(infoModel);
        }
        mInfoList.addAll(infoModels);
        // 重新执行getCount与getView这些方法，实现界面数据更新
        mMyAdapter.notifyDataSetChanged();
    }

    private void loadMore() {
//        List<InfoModel> infoModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            InfoModel infoModel = new InfoModel("describe", R.drawable.btmlay_icn_praise, "name----" + i, R.drawable.index_btn_fav_prs, "11:30");
            mInfoList.add(infoModel);
        }
//        mInfoList.addAll(infoModels);
        // 重新执行getCount与getView这些方法，实现界面数据更新

        MyAdapter adapter = new MyAdapter(mInfoList, ListDemoActivity.this);
//        mMyAdapter.notifyDataSetChanged();
//        adapter.notifyDataSetChanged();


        mMyAdapter.notifyDataSetChanged();

    }


}
