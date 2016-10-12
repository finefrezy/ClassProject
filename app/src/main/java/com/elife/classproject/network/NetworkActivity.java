package com.elife.classproject.network;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elife.classproject.R;

import java.io.IOException;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {

    Button mGetBtn;
    Button mPostBtn;
    Button mImgBtn;
    Button mFileBtn;
    Button mAsyncBtn;
    Button mListBtn;

    TextView mTextResult;

    ImageView mImageView;

    ProgressBar mProgressBar;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mImageView.setImageBitmap((Bitmap) msg.obj);
                    break;
                case 1:
                    Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + "/0f63618b1.jpg");
                    // 主动更新图册图片
                    MediaScannerConnection.scanFile(getApplicationContext(), new String[]{Environment.getExternalStorageDirectory().getPath() + "/0f63618b1.jpg"}, null, null);
                    mImageView.setImageBitmap(bitmap);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        mGetBtn = (Button) findViewById(R.id.get_btn);
        mPostBtn = (Button) findViewById(R.id.post_btn);
        mImgBtn = (Button) findViewById(R.id.img_btn);
        mFileBtn = (Button) findViewById(R.id.file_btn);
        mAsyncBtn = (Button) findViewById(R.id.async_task);
        mListBtn = (Button) findViewById(R.id.data_show);

        mTextResult = (TextView) findViewById(R.id.result);
        mImageView = (ImageView) findViewById(R.id.image);

        mProgressBar = (ProgressBar) findViewById(R.id.progress);

        mGetBtn.setOnClickListener(this);
        mPostBtn.setOnClickListener(this);
        mImgBtn.setOnClickListener(this);
        mFileBtn.setOnClickListener(this);
        mListBtn.setOnClickListener(this);
        mAsyncBtn.setOnClickListener(this);

        Log.e("---sdcad--", Environment.getExternalStorageDirectory().getPath());// SD卡路径/storage/sdcard，存储图一般新建目录存
        Log.e("---data--", getApplicationContext().getFilesDir().getPath());// /data/data/com.elife.classproject/files图片对当前程序时私有的，卸载应用图片也会删除

        mTextResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String result = NetUtils.getGoodInfo();
                            NetworkActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (null != result) {
                                        mTextResult.setText(result);
                                    }

                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


                break;
            case R.id.post_btn:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String result = NetUtils.postUserInfo();
                            mTextResult.post(new Runnable() {
                                @Override
                                public void run() {
                                    mTextResult.setText(result);
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            case R.id.img_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Bitmap bitmap = NetUtils.image();

                            Message msg = mHandler.obtainMessage();
                            msg.what = 0;
                            // obj->Object类型的对象
                            msg.obj = bitmap;
                            mHandler.sendMessage(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.file_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            NetUtils.file();
                            Message msg = mHandler.obtainMessage();
                            msg.what = 1;
                            mHandler.sendMessage(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.async_task:
                /**
                 * 异步任务的优点：
                 *  •线程的开销较大，如果每个任务都要创建一个线程，那么应用程 序的效率要低很多；
                    •线程无法管理，匿名线程创建并启动后就不受程序的控制了，如果有很多个请求发送，那么就会启动非常多的线程，系统将不堪重负。
                    •在新线程中更新UI还必须要引入handler，这让代码看上去非常臃肿
                 */

                // asyncTask执行的任务应该是一些短时间内可以完成的任务
                // 默认情况下， 同一时间只能有一个asyncTask在运行， 也就是说所有的task都是串行运行的
                // 所以如果某个task执行时间很长， 会导致后面的task长时间等待

                // 异步任务
                DownloadAsyncTask downTask = new DownloadAsyncTask(mTextResult);
//                downTask.execute("http://10.50.8.79:8088/elife/test.apk"); // 串行运行
                downTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://10.50.8.79:8088/elife/test.apk");// 最多允许5个并行运行

                ProgressAsyncTask progress = new ProgressAsyncTask( mProgressBar);
//                progress.execute("http://10.50.8.79:8088/elife/test.apk");
                progress.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://10.50.8.79:8088/elife/test.apk");

                break;
            case R.id.data_show:
                Intent intent = new Intent(getApplicationContext(), GoodActivity.class);
                startActivity(intent);
                break;
        }
    }
}
