package com.elife.classproject.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.elife.classproject.R;
import com.elife.classproject.application.MyApplication;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mImagePlayPause;
    ImageView mImageScheme;
    ImageView mImagemNext;
    ImageView mImagePres;
    ImageView mImageDetail;


    TextView mTextPlayTime;
    TextView mTextTotalTime;

    SeekBar mVolumeSeekBar;
    SeekBar mSeekProgress;
    private AudioManager mAudioManager;
    private boolean isPlaying = true;


    private int mCurrPlayPosition = 0;

    private int mCurrPositon;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.e("------------", "mHandler----------------------------");
                    mCurrPlayPosition++;
                    mSeekProgress.setProgress(mCurrPlayPosition);
                    // 设置时间变化
                    mTextPlayTime.setText(timeFormat(mCurrPlayPosition * 1000));

                    mHandler.sendEmptyMessageDelayed(0, 1000);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mImagePlayPause = (ImageView) findViewById(R.id.play_paly_pause);
        mImageScheme = (ImageView) findViewById(R.id.play_scheme);
        mImagemNext = (ImageView) findViewById(R.id.play_next);
        mImagePres = (ImageView) findViewById(R.id.play_pre);
        mImageDetail = (ImageView) findViewById(R.id.play_detail);


        mImagePlayPause.setOnClickListener(this);
        mImageScheme.setOnClickListener(this);
        mImagemNext.setOnClickListener(this);
        mImagePres.setOnClickListener(this);
        mImageDetail.setOnClickListener(this);

        mTextPlayTime = (TextView) findViewById(R.id.paly_time);
        mTextTotalTime = (TextView) findViewById(R.id.total_time);
        mVolumeSeekBar = (SeekBar) findViewById(R.id.seek_voice);
        mSeekProgress = (SeekBar) findViewById(R.id.seek_play);

        // 获取前一个界面的intent，并获取SongModel对象
        Intent intent = getIntent();
        mCurrPositon = intent.getIntExtra("sing", 0);
        SongModel songModel = MyApplication.getSongList().get(mCurrPositon);


        mTextPlayTime.setText("00:00");
        mTextTotalTime.setText(timeFormat(songModel.getDuration()));
        // 设置当前最大进度为音乐长度的秒数
        mSeekProgress.setMax(songModel.getDuration() / 1000);
        // 默认在0位置
        mSeekProgress.setProgress(0);


        // 设置handler
        MusicService musicService = new MusicService();


        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); //获取当前音乐音量
        mVolumeSeekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)); //SEEKBAR设置为音量的最大阶数  0-7
        mVolumeSeekBar.setProgress(mVolume); //设置seekbar为当前音量进度

        mVolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0); //拖动seekbar时改变音量
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        intent.setClass(getApplicationContext(), MusicService.class);
        intent.putExtra("curritem", songModel.getFileUrl());
        intent.putExtra("flag", "paly");
        startService(intent);


        ProgressBroadcast broadcast = new ProgressBroadcast(mHandler);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.elife.android.PLAY_ACTIVITY_UI");
        registerReceiver(broadcast, intentFilter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_paly_pause:
                if (!isPlaying) {
//                    mImagePlayPause.setBackgroundResource(R.drawable.desk2_play);

                    mImagePlayPause.setImageResource(R.drawable.desk2_play);
                    isPlaying = true;
// 因为service已经启动，并且mediaplayer已经准备好，可以直接播放，所以直接发送handler消息更新UI就行
                    mHandler.sendEmptyMessageDelayed(0, 1000);

                } else {
//                    mImagePlayPause.setBackgroundResource(R.drawable.desk2_pause);
                    mImagePlayPause.setImageResource(R.drawable.desk2_pause);
                    isPlaying = false;
                    mHandler.removeMessages(0);
                }
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                intent.putExtra("flag", "paly");
                startService(intent);


                break;
            case R.id.play_scheme:
                break;
            case R.id.play_next:
                Intent intentNext = new Intent(getApplicationContext(), MusicService.class);
                if (mCurrPositon ==( MyApplication.getSongList().size()-1)) {
                    mCurrPositon = 0;
                    intentNext.putExtra("curritem", MyApplication.getSongList().get(mCurrPositon).getFileUrl());
                } else {

                    mCurrPositon = mCurrPositon + 1;
                    intentNext.putExtra("curritem", MyApplication.getSongList().get(mCurrPositon).getFileUrl());
                }
                intentNext.putExtra("flag", "next");
                startService(intentNext);


                break;
            case R.id.play_pre:
                break;
            case R.id.play_detail:
                break;
        }
    }

    private String timeFormat(int timeMills) {

        int second = timeMills / 1000;
        int min = second / 60;
        int extraSecond = second - min * 60;

        if (min < 10) {
            if (extraSecond < 10) {
                return "0" + min + ":0" + extraSecond;
            } else {
                return "0" + min + ":" + extraSecond;
            }
        } else {
            if (extraSecond < 10) {
                return min + ":0" + extraSecond;
            } else {
                return min + ":" + extraSecond;
            }
        }
    }
}

class ProgressBroadcast extends BroadcastReceiver {

    Handler handler;

    public ProgressBroadcast(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.elife.android.PLAY_ACTIVITY_UI")) {
            if (intent.getStringExtra("flag").equals("progress")) {
                handler.sendEmptyMessageDelayed(0, 1000);
            }

        }

    }
}
