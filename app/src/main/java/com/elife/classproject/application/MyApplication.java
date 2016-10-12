package com.elife.classproject.application;

import android.app.Application;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.elife.classproject.player.SongModel;
import com.elife.classproject.sqlite.DatabaseHelper;

import java.util.List;

/**
 * Created by tzhang on 2016/9/2.
 */
public class MyApplication extends Application {

    public static final MyApplication MY_APPLICATION = new MyApplication();
    private static DatabaseHelper sDataBaseHelper;
    private static List<SongModel> sSongList;
    private int i = 0;

    public static DatabaseHelper getDbInstance() {
        return sDataBaseHelper;
    }

    public static List<SongModel> getSongList() {
        return sSongList;
    }

    public static void setSongList(List<SongModel> songList) {
        sSongList = songList;
    }

    // 当前应用程序的初始化操作
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("------------", "MyApplication");

        sDataBaseHelper = DatabaseHelper.getInstance(this);// 能放到线程里面就放到线程里面
        SDKInitializer.initialize(getApplicationContext());
    }

    public int getI() {
        i++;
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

}
