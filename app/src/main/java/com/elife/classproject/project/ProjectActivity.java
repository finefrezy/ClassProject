package com.elife.classproject.project;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.elife.classproject.R;
import com.elife.classproject.project.fragment.MainFragment;

/**
 * toolbar标题栏居中
 * Toolbar的父类是ViewGroup，我们只需把Toolbar默认的title设置为空，并在Toolbar里加上一个TextView就可以实现
 *
 */
public class ProjectActivity extends AppCompatActivity {

    private static final boolean DEBUG = true;
    private static final String TAG = ProjectActivity.class.getSimpleName();

    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;


    private TextView mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initDrawLayout();
        addMainFragment();


    }

    private void addMainFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MainFragment mainFragment = new MainFragment();
        // 避免view显示的重复
        transaction.replace(R.id.content, mainFragment, MainFragment.TAG);

        transaction.commit();
    }

    private void initDrawLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // 0=<slideOffset<=1,宽度滑动的百分比
                super.onDrawerSlide(drawerView, slideOffset);
                if (DEBUG) {
                    Log.e(TAG, "onDrawerSlide" + slideOffset);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (DEBUG) {
                    Log.e(TAG, "onDrawerOpened");
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (DEBUG) {
                    Log.e(TAG, "onDrawerClosed");
                }
            }
        };

        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTitleText = (TextView) findViewById(R.id.title_content);

        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        // 给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setmTitleText(String titleText) {
        mTitleText.setText(titleText);
    }

    public void hideTooBar() {
//        mToolBar.setVisibility(View.GONE);

    }
}
