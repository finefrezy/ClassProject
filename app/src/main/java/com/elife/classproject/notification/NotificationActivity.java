package com.elife.classproject.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.elife.classproject.AllClassActivity;
import com.elife.classproject.R;
// http://blog.sina.com.cn/s/blog_7d22784d0102v94u.html
// http://www.cnblogs.com/wangziqiang/p/4287454.html
// http://www.itnose.net/detail/6169442.html
// 不同版本下通知的设置
//http://blog.csdn.net/loongggdroid/article/details/17616509

public class NotificationActivity extends AppCompatActivity {

    Button mShowNoti;
    Button mCancelNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mShowNoti = (Button) findViewById(R.id.show_noti);
        mCancelNoti = (Button) findViewById(R.id.cancel_noti);

        mShowNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoti();
            }
        });
        mCancelNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNoti();
            }
        });
    }

    private void showNoti() {
        // 获取系统通知服务
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, AllClassActivity.class);
        // 点击通知时，会执行相应的intent跳转
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, 0);

        // 通过位图工厂，解析本地的图片资源成为一个Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.live_head_passerby);

        Notification notify = new Notification.Builder(this)
                .setSmallIcon(R.drawable.recommend_icn_exclusive)
                .setLargeIcon(bitmap)
                .setTicker("问君能有多少愁？恰似一江春水向东流。")
                .setContentTitle("虞美人")
                .setContentText("This is the notification message")

                // Call requires API level 16 (current min is 15):
                .setContentIntent(pendingIntent).build();

        notify.flags |= Notification.FLAG_FOREGROUND_SERVICE;

        // 第一个参数是id，对应当前的通知
        manager.notify(1, notify);
    }
    private void cancelNoti() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 根据当前的通知id，取消通知
        manager.cancel(1);
    }
}
