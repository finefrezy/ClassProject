package com.elife.classproject.baseview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.elife.classproject.R;

import java.util.ArrayList;
import java.util.List;

public class BaseViewTestActivity extends AppCompatActivity {
//    RandomTextView randomTextView;
    TestView testView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view_test);


//        randomTextView = (RandomTextView) findViewById(R.id.define_text);
//        randomTextView.setListData(initData());
        testView = (TestView) findViewById(R.id.define_text);
        testView.setListData(initData());

    }

    private List<String> initData() {
        List<String> list = new ArrayList<String>();
        list.add("寥落古行宫，宫花寂寞红。白头宫女在，闲坐说玄宗。");
        list.add("君自故乡来，应知故乡事。来日绮窗前，寒梅著花未？");
        list.add("归山深浅去，须尽丘壑美。莫学武陵人，暂游桃源里。");
        list.add("银烛秋光冷画屏，轻罗小扇扑流萤。天阶夜色凉如水，坐看牵牛织女星。");
        list.add("纱窗日落渐黄昏，金屋无人见泪痕。寂寞空庭春欲晚，梨花满地不开门。");
        list.add("灞原风雨定，晚见雁行频。落叶他乡树，寒灯独夜人。空园白露滴，孤壁野僧邻。寄卧郊扉久，何年致此身。");
        list.add("遥夜泛清瑟，西风生翠萝。残萤栖玉露，早雁拂金河。高树晓还密，远山晴更多。淮南一叶下，自觉洞庭波。");
        list.add("锦瑟无端五十弦，一弦一柱思华年。\n" +
                "庄生晓梦迷蝴蝶，望帝春心托杜鹃。\n" +
                "沧海月明珠有泪，蓝田日暖玉生烟。\n" +
                "此情可待成追忆，只是当时已惘然。");
        return list;
    }
}
