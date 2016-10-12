package com.elife.classproject.util;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tzhang on 2016/9/20.
 */
public class FormatUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 日期格式转换
    public static String timeFormat(long timeMillSeconds) {
        Date dt = new Date(timeMillSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }

    public static String timeFormatCoupon(long timeMillSeconds) {
        Date dt = new Date(timeMillSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dt);
    }
}
