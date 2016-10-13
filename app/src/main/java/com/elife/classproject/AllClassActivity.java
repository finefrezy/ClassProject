package com.elife.classproject;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.elife.classproject.aidl.BankActivity;
import com.elife.classproject.anim.AnimActivity;
import com.elife.classproject.baseview.BaseViewTestActivity;
import com.elife.classproject.beautytitle.BeautyActivity;
import com.elife.classproject.broadcast.BcReceiver;
import com.elife.classproject.broadcast.BroadcastActivity;
import com.elife.classproject.canvas.CanvasActivity;
import com.elife.classproject.cardview.CardViewActivity;
import com.elife.classproject.composite.HomeActivity;
import com.elife.classproject.contentprovider.VisitCvActivity;
import com.elife.classproject.dialog.AlertDialgActivity;
import com.elife.classproject.drawer.DrawerActivity;
import com.elife.classproject.eventinter.EventActivity;
import com.elife.classproject.forum.ForumActivity;
import com.elife.classproject.fragment.FragActivity;
import com.elife.classproject.list.RLListViewActivity;
import com.elife.classproject.logistics.LogisticsActivity;
import com.elife.classproject.map.MapActivity;
import com.elife.classproject.netframe.RetrofitActivity;
import com.elife.classproject.network.HandlerActivity;
import com.elife.classproject.network.NetworkActivity;
import com.elife.classproject.notification.NotificationActivity;
import com.elife.classproject.palette.PaletteActivity;
import com.elife.classproject.player.SongListActivity;
import com.elife.classproject.popup.PopupActivity;
import com.elife.classproject.project.ProjectActivity;
import com.elife.classproject.recycler.ForumWaterActivity;
import com.elife.classproject.service.BindServiceActivity;
import com.elife.classproject.shape.ShapeActivity;
import com.elife.classproject.viewinject.InjectTestActivity;
import com.elife.classproject.vp.VpActivity;

public class AllClassActivity extends AppCompatActivity implements View.OnClickListener {


    Button mBtnVp;
    Button mBtnDrawer;
    Button mProgress;
    Button mDialog;
    Button mNaviMenu;
    BcReceiver broadCastReceiver;
    private Button mNotification;
    private Button mPopup;
    private Button mFrag;
    private Button mHome;
    private Button mList;
    private Button mShared;
    private Button mSqlite;
    private Button mBroadcast;
    private Button mJson;
    private Button mNetWork;
    private Button mHandler;
    private Button mNetFrame;
    private Button mContentProvider;
    private Button mService;
    private Button mSong;
    private Button mAnim;
    private Button mShape;
    private Button mCanvas;
    private Button mDefineView;
    private Button mEventDispatch;
    private Button mLogistics;
    private Button mBeautyTitle;
    private Button mForumLayout;
    private Button mPalette;
    private Button mCardView;
    private Button mRecycler;
    private Button mProject;
    private Button mBdMap;
    private Button mAidl;
    private Button mInject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_class);

        mBtnVp = (Button) findViewById(R.id.view_pager);
        mBtnDrawer = (Button) findViewById(R.id.drawer_layout);
        mProgress = (Button) findViewById(R.id.progress);
        mDialog = (Button) findViewById(R.id.dialog);
        mNotification = (Button) findViewById(R.id.noti);
        mNaviMenu = (Button) findViewById(R.id.navi_menu);
        mPopup = (Button) findViewById(R.id.popup);
        mFrag = (Button) findViewById(R.id.fragment);
        mHome = (Button) findViewById(R.id.home);
        mList = (Button) findViewById(R.id.listview);
        mShared = (Button) findViewById(R.id.sharedpre);
        mSqlite = (Button) findViewById(R.id.sqlite);
        mBroadcast = (Button) findViewById(R.id.broadcast);
        mJson = (Button) findViewById(R.id.json);
        mNetWork = (Button) findViewById(R.id.network);
        mHandler = (Button) findViewById(R.id.handler);
        mNetFrame = (Button) findViewById(R.id.net_frame);
        mContentProvider = (Button) findViewById(R.id.provider);
        mService = (Button) findViewById(R.id.service_btn);
        mSong = (Button) findViewById(R.id.song);
        mAnim = (Button) findViewById(R.id.anim);
        mShape = (Button) findViewById(R.id.shape);
        mCanvas = (Button) findViewById(R.id.canvas);
        mLogistics = (Button) findViewById(R.id.logistics);
        mInject = (Button) findViewById(R.id.inject);

        mDefineView = (Button) findViewById(R.id.self_define_view);
        mEventDispatch = (Button) findViewById(R.id.event_dispatch);
        mBeautyTitle = (Button) findViewById(R.id.beauty_title);
        mForumLayout = (Button) findViewById(R.id.forum_layout);
        mPalette = (Button) findViewById(R.id.palette);
        mCardView = (Button) findViewById(R.id.card_view);
        mRecycler = (Button) findViewById(R.id.recycler);
        mProject = (Button) findViewById(R.id.project);
        mBdMap = (Button) findViewById(R.id.bdmap);
        mAidl = (Button) findViewById(R.id.aidl);

        mBtnVp.setOnClickListener(this);
        mList.setOnClickListener(this);
        mBtnDrawer.setOnClickListener(this);
        mProgress.setOnClickListener(this);
        mDialog.setOnClickListener(this);
        mNotification.setOnClickListener(this);
        mNaviMenu.setOnClickListener(this);
        mPopup.setOnClickListener(this);
        mFrag.setOnClickListener(this);
        mHome.setOnClickListener(this);
        mShared.setOnClickListener(this);
        mSqlite.setOnClickListener(this);
        mBroadcast.setOnClickListener(this);
        mJson.setOnClickListener(this);
        mNetWork.setOnClickListener(this);
        mHandler.setOnClickListener(this);
        mHandler.setOnClickListener(this);
        mNetFrame.setOnClickListener(this);
        mContentProvider.setOnClickListener(this);
        mService.setOnClickListener(this);
        mSong.setOnClickListener(this);
        mAnim.setOnClickListener(this);
        mShape.setOnClickListener(this);
        mCanvas.setOnClickListener(this);
        mDefineView.setOnClickListener(this);
        mEventDispatch.setOnClickListener(this);
        mLogistics.setOnClickListener(this);
        mBeautyTitle.setOnClickListener(this);
        mForumLayout.setOnClickListener(this);
        mPalette.setOnClickListener(this);
        mCardView.setOnClickListener(this);
        mRecycler.setOnClickListener(this);
        mProject.setOnClickListener(this);
        mBdMap.setOnClickListener(this);
        mAidl.setOnClickListener(this);
        mInject.setOnClickListener(this);


        //生成广播处理
        broadCastReceiver = new BcReceiver();
        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter("android.intent.action.TIME_TICK");
        //注册广播
        registerReceiver(broadCastReceiver, intentFilter);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.view_pager:
                intent.setClass(getApplicationContext(), VpActivity.class);
                break;
            case R.id.drawer_layout:
                intent.setClass(getApplicationContext(), DrawerActivity.class);
                break;
            case R.id.progress:
                intent.setClass(getApplicationContext(), ProgressActivity.class);
                break;
            case R.id.dialog:
                intent.setClass(getApplicationContext(), AlertDialgActivity.class);
                break;
            case R.id.noti:
                intent.setClass(getApplicationContext(), NotificationActivity.class);
                break;
            case R.id.navi_menu:
                intent.setClass(getApplicationContext(), MenuActivity.class);
                break;
            case R.id.popup:
                intent.setClass(getApplicationContext(), PopupActivity.class);
                break;
            case R.id.fragment:
                intent.setClass(getApplicationContext(), FragActivity.class);
                break;
            case R.id.home:
                intent.setClass(getApplicationContext(), HomeActivity.class);
                break;
            case R.id.listview:
                intent.setClass(getApplicationContext(), RLListViewActivity.class);
                break;
            case R.id.sharedpre:
                intent.setClass(getApplicationContext(), LoginActivity.class);
                break;
            case R.id.sqlite:
                intent.setClass(getApplicationContext(), SqliteActivity.class);
                break;
            case R.id.broadcast:
                intent.setClass(getApplicationContext(), BroadcastActivity.class);
                break;
            case R.id.json:
                intent.setClass(getApplicationContext(), JsonActivity.class);
                break;
            case R.id.network:
                intent.setClass(getApplicationContext(), NetworkActivity.class);
                break;
            case R.id.handler:
                intent.setClass(getApplicationContext(), HandlerActivity.class);
                break;
            case R.id.net_frame:
                intent.setClass(getApplicationContext(), RetrofitActivity.class);
                break;
            case R.id.provider:
                intent.setClass(getApplicationContext(), VisitCvActivity.class);
                break;
            case R.id.service_btn:
//                intent.setClass(getApplicationContext(), ServiceActivity.class);
                intent.setClass(getApplicationContext(), BindServiceActivity.class);
                break;

            case R.id.song:
                intent.setClass(getApplicationContext(), SongListActivity.class);
                break;
            case R.id.anim:
                intent.setClass(getApplicationContext(), AnimActivity.class);
                break;
            case R.id.shape:
                intent.setClass(getApplicationContext(), ShapeActivity.class);
                break;
            case R.id.canvas:
                intent.setClass(getApplicationContext(), CanvasActivity.class);
                break;
            case R.id.self_define_view:
                intent.setClass(getApplicationContext(), BaseViewTestActivity.class);
                break;
            case R.id.event_dispatch:
                intent.setClass(getApplicationContext(), EventActivity.class);
                break;

            case R.id.logistics:
                intent.setClass(getApplicationContext(), LogisticsActivity.class);
                break;
            case R.id.beauty_title:
                intent.setClass(getApplicationContext(), BeautyActivity.class);
                break;
            case R.id.forum_layout:
                intent.setClass(getApplicationContext(), ForumActivity.class);
                break;
            case R.id.palette:
                intent.setClass(getApplicationContext(), PaletteActivity.class);
                break;
            case R.id.card_view:
                intent.setClass(getApplicationContext(), CardViewActivity.class);
                break;
            case R.id.recycler:
//                intent.setClass(getApplicationContext(), WaterFallActivity.class);
//                intent.setClass(getApplicationContext(), ForumMsgActivity.class);
                intent.setClass(getApplicationContext(), ForumWaterActivity.class);
                break;
            case R.id.project:
                intent.setClass(getApplicationContext(), ProjectActivity.class);
                break;
            case R.id.bdmap:
                intent.setClass(getApplicationContext(), MapActivity.class);
                break;
            case R.id.aidl:
                intent.setClass(getApplicationContext(), BankActivity.class);
                break;
            case R.id.inject:
                intent.setClass(getApplicationContext(), InjectTestActivity.class);
                break;
        }

        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadCastReceiver);
        super.onDestroy();
    }
}
