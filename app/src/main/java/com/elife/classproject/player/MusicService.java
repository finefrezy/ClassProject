package com.elife.classproject.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;

public class MusicService extends Service {

    private MediaPlayer mMediaPlayer;
    private String mCurrentPlaying;
    private boolean isPauseExecute = false;

    private Handler mHandler;

    public MusicService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();


        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });

        mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mediaPlayer) {
            }
        });

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mCurrentPlaying = intent.getStringExtra("curritem");

        if (intent.getStringExtra("flag").equals("paly")) {
            if (mMediaPlayer.isPlaying()) {
                pause();
            } else {
                play();
            }
        } else if(intent.getStringExtra("flag").equals("next")){
            // 下一首
            playSong();
        } else if(intent.getStringExtra("flag").equals("previous")) {
            // 前一首
        }





        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 播放音乐
     *
     */
    private void play() {
        try {
            if (!isPauseExecute) {
                playSong();
            } else {
                mMediaPlayer.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playSong() {
        try {
            mMediaPlayer.reset();//把各项参数恢复到初始状态
            mMediaPlayer.setDataSource(mCurrentPlaying);
            mMediaPlayer.prepare();  //进行缓冲
            mMediaPlayer.setOnPreparedListener(new PreparedListener());//注册一个监听器
        } catch (Exception e) {
        }


    }

    /**
     * 暂停音乐
     */
    private void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            isPauseExecute = true;
        }
    }

    /**
     * 停止音乐
     */
    private void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            try {
                mMediaPlayer.prepare(); // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }

    /**
     * 实现一个OnPrepareLister接口,当音乐准备好的时候开始播放
     */
    private final class PreparedListener implements MediaPlayer.OnPreparedListener {

        public PreparedListener() {
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            // 第一次启动时，需要启动service与MediaPlayer准备时间，所以在这个地方发送一个广播出去到activit中，在activity在发送handler更新UI
            mMediaPlayer.start();    //开始播放
            Intent intent = new Intent();
            intent.putExtra("flag", "progress");
            intent.setAction("com.elife.android.PLAY_ACTIVITY_UI");
            sendBroadcast(intent);

        }
    }



}
