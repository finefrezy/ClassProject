package com.elife.classproject.project.pager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.elife.classproject.AllClassActivity;
import com.elife.classproject.R;
import com.elife.classproject.project.base.BasePager;


/**
 * Created by tzhang on 2016/9/22.
 */
public class HobbyPager extends BasePager {
    LinearLayout mLinearHobby;
    private WebView mWebView;

    public HobbyPager(Context ctx) {
        super(ctx);
    }

    @Override
    public View initView() {
        Log.e("HobbyPager", "initView");
        mPageRootView  = View.inflate(mContex, R.layout.page_hobby, null);
        mLinearHobby = (LinearLayout) mPageRootView.findViewById(R.id.story_linear);


        mWebView = new WebView(mContex);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 默认使用缓存
        settings.setAllowFileAccess(true); // 可以读取文件缓存(manifest生效)
        settings.setDefaultTextEncodingName("utf-8");

        settings.setBuiltInZoomControls(true);// 隐藏缩放按钮
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
        settings.setUseWideViewPort(true);// 可任意比例缩放
        settings.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        settings.setSavePassword(true);
        settings.setSaveFormData(true);// 保存表单数据
        settings.setGeolocationEnabled(true);// 启用地理定位
        settings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");// 设置定位的数据库路径
        settings.setDomStorageEnabled(true);

        mWebView.setBackgroundColor(Color.parseColor("#ffffff"));

        mLinearHobby.addView(mWebView);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("http://fxciosbuy")) {
                    String id ="";
                    if (url.contains("http://fxciosbuy?id=")) {
                        id = url.substring(url.lastIndexOf("=") + 1, url.length() - 1);
                    } else {
                        id = url.substring(url.lastIndexOf("%3D") + 3, url.length() - 1);
                    }
//            	Log.d("weburldir", id);
                    Intent intent = new Intent();
                    intent.setClass(mContex, AllClassActivity.class);
                    try
                    {
                        intent.putExtra("good_detail", Integer.parseInt(id));
                    }
                    catch (Exception e)
                    {

                    }

                    mContex.startActivity(intent);


                } else {
                    view.loadUrl(url);   //在当前的webview中跳转到新的url
                }

                return true;
            }
        });
        mWebView.loadUrl("http://cxg.139club.com/" + "index.php?g=home&m=Cxggs&a=index&username=");


        return mPageRootView;
    }

    @Override
    public void initData() {

    }

}
