package com.elife.classproject.player;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.elife.classproject.R;
import com.elife.classproject.application.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends AppCompatActivity {


    private ListView mListView;


    private List<SongModel> mSongList;
    private SongAdapter mSongAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        mListView = (ListView) findViewById(R.id.song_list);
        getAllmSongList();


        MyApplication.setSongList(mSongList);

        mSongAdapter = new SongAdapter(mSongList, this);
        mListView.setAdapter(mSongAdapter);




        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent();
                in.setClass(getApplicationContext(), PlayActivity.class);
                in.putExtra("sing", i);
                startActivity(in);
            }
        });
    }


    private void getAllmSongList() {

        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.YEAR,
                        MediaStore.Audio.Media.MIME_TYPE,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.ALBUM_ID},
                MediaStore.Audio.Media.MIME_TYPE + "=? or "
                        + MediaStore.Audio.Media.MIME_TYPE + "=?",
                new String[]{"audio/mpeg", "audio/x-ms-wma"}, null);

        mSongList = new ArrayList<SongModel>();

        // for(cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()){}

        SongModel song = null;
        if (cursor.moveToFirst()) {
            do{
                song = new SongModel();
                // 文件名   下标从0开始
                song.setFileName(cursor.getString(1));
                // 歌曲名
                song.setTitle(cursor.getString(2));
                // 时长
                song.setDuration(cursor.getInt(3));
                // 歌手名
                song.setSinger(cursor.getString(4));
                // 专辑名
                song.setAlbum(cursor.getString(5));
                // 年代
                if (cursor.getString(6) != null) {
                    song.setYear(cursor.getString(6));
                } else {
                    song.setYear("未知");
                }
                // 歌曲格式
                if ("audio/mpeg".equals(cursor.getString(7).trim())) {
                    song.setType("mp3");
                } else if ("audio/x-ms-wma".equals(cursor.getString(7).trim())) {
                    song.setType("wma");
                }
                // 文件大小
                if (cursor.getString(8) != null) {
                    float size = cursor.getLong(8) / 1024f / 1024f;
                    song.setSize((size + "").substring(0, 4) + "M");
                } else {
                    song.setSize("未知");
                }
                // 文件路径
                if (cursor.getString(9) != null) {
                    song.setFileUrl(cursor.getString(9));
                }

                song.setAlbumImagePath(queryPath(cursor.getInt(10)));

                mSongList.add(song);
            } while(cursor.moveToNext());
        }

        cursor.close();


    }

    private String queryPath(int albumId) {
//        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{MediaStore.Audio.Albums.ALBUM_ART};
        Cursor cur = this.getContentResolver().query(
                ContentUris.withAppendedId(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, albumId),
                projection, null, null, MediaStore.Audio.Albums.DEFAULT_SORT_ORDER);
        String albumArt = "";
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            albumArt = cur.getString(0);
        }

        cur.close();

        return albumArt;
    }

    private void querySong() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        // 歌曲id
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
        // 文件名
        String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
        // 歌曲名称
        String tilte = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
        // 专辑
        String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
        // 歌手
        String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
        //路径
        String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
        // 时长
        int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
        // 大小
        long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
        // 年份
        String year = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.YEAR));
    }
}
