package com.elife.classproject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

// ActionBar 如何设置标题居中
//课后自学 ToolBar， searchview
// http://blog.csdn.net/jdsjlzx/article/details/41441083/
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ActionBar actionBar = getSupportActionBar();
        // 是否显示标题
        actionBar.setDisplayShowTitleEnabled(true);
       // 决定左上角图标的右侧是否有向左的小箭头
//        actionBar.setDisplayHomeAsUpEnabled(true);

        //隐藏actionBar
//        actionBar.hide();


        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.live_head_passerby);
        actionBar.setDisplayUseLogoEnabled(true);
//        // 返回箭头（默认不显示）
        actionBar.setDisplayHomeAsUpEnabled(false);
//        // 左侧图标点击事件使能
        actionBar.setHomeButtonEnabled(true);

    }

// 创建菜单、初始化
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_title, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // 处理点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.share:
                Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.search:
                Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();
                MenuActivity.this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }
}
