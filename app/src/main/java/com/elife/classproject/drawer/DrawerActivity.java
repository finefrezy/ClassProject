package com.elife.classproject.drawer;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.elife.classproject.R;
import com.elife.classproject.adapter.InfoModel;
import com.elife.classproject.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;
// slidingmenu
// https://github.com/jfeinstein10/SlidingMenu
// navigation drawer
public class DrawerActivity extends AppCompatActivity {

    MyAdapter mMyAdapter;
    private ListView mListView;
    private List<InfoModel> mInfoList;

    DrawerLayout mDrawerLayout;


    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initData();

        initView();

    }

    private void initView() {

        mMyAdapter = new MyAdapter(mInfoList, this);

        mListView.setAdapter(mMyAdapter);

        View view = mInflater.inflate(R.layout.header_drawer, null);

        mListView.addHeaderView(view);

        ImageView image = new ImageView(this);
//        image.setImageResource(R.drawable.lay_shang_bg);
//        mListView.addFooterView(image);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0) {
                    Toast.makeText(getApplicationContext(), "" + mInfoList.get(i - 1).getName(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(getApplicationContext(), "onDrawerOpened" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(getApplicationContext(), "onDrawerClosed" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }

    public void initData() {

        mInflater = LayoutInflater.from(this);

        mInfoList = new ArrayList<InfoModel>();

        for (int i = 0; i < 4; i++) {
            InfoModel infoModel = new InfoModel("DESCRIBE", R.drawable.btmlay_icn_praise, "NAME----" + i, R.drawable.recommend_icn_birthday_prs, "10:30");
            mInfoList.add(infoModel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
