package com.elife.classproject.circlemenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.elife.classproject.R;

import java.util.ArrayList;
import java.util.List;

public class CircleMenuActivity extends AppCompatActivity {

    private CircleMenuLayout circleMenuLayout;

    private BetterCircleMenuLayout betterCircleMenuLayout;

    private String[] mItemTexts = new String[]{
            "信用卡", "我的账户", "转账汇款", "投资理财", "特色服务", "安全中心"
    };

    private int[] mItemImgs = {R.drawable.live_head_passerby, R.drawable.live_head_passerby, R.drawable.live_head_passerby, R.drawable.live_head_passerby,
           R.drawable.live_head_passerby, R.drawable.live_head_passerby};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_menu);
        circleMenuLayout = (CircleMenuLayout) findViewById(R.id.circle_menu);
        circleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        betterCircleMenuLayout = (BetterCircleMenuLayout) findViewById(R.id.better_circle_menu);

        circleMenuLayout.setOnItemClickListener(new CircleMenuLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(CircleMenuActivity.this, mItemTexts[position], Toast.LENGTH_SHORT).show();
            }
        });

        List<MenuItem>  list = new ArrayList<MenuItem>();
        MenuItem menuItem1 = new MenuItem(mItemImgs[0], mItemTexts[0]);
        MenuItem menuItem2 = new MenuItem(mItemImgs[1], mItemTexts[1]);
        MenuItem menuItem3 = new MenuItem(mItemImgs[2], mItemTexts[2]);
        MenuItem menuItem4 = new MenuItem(mItemImgs[3], mItemTexts[3]);
        MenuItem menuItem5 = new MenuItem(mItemImgs[4], mItemTexts[4]);
        MenuItem menuItem6 = new MenuItem(mItemImgs[5], mItemTexts[5]);
        list.add(menuItem1);
        list.add(menuItem2);
        list.add(menuItem3);
        list.add(menuItem4);
        list.add(menuItem5);
        list.add(menuItem6);
        CircleMenuAdapter circleMenuAdapter = new CircleMenuAdapter(list);
        betterCircleMenuLayout.setAdapter(circleMenuAdapter);
    }
}
