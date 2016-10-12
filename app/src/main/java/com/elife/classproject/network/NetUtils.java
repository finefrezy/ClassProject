package com.elife.classproject.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tzhang on 2016/9/10.
 */
public class NetUtils {

    private OnCallBack onCallBack;

    /**
     * get 请求
     *
     * @return
     * @throws IOException
     */
    public static String getGoodInfo() throws IOException {
        URL url = new URL("http://10.50.8.79:8088/elife/good");
        // 打开连接
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        httpURLConnection.setConnectTimeout(8000);// 连接超时
        httpURLConnection.setReadTimeout(8000);// 读取超时

        StringBuffer stringBuffer = null;
        // 200 -> OK
        if (httpURLConnection.getResponseCode() == 200) {
            InputStream is = httpURLConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;

            stringBuffer = new StringBuffer("");
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
            }
            httpURLConnection.disconnect();
            return stringBuffer.toString();
        }
        httpURLConnection.disconnect();
        return null;
    }

    /**
     * post请求
     *
     * @return
     * @throws IOException
     */
    public static String postUserInfo() throws IOException {
        URL url = new URL("http://10.50.8.79:8088/elife/user");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");

        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(8000);

        httpURLConnection.setDoOutput(true);// 允许使用输出流OutputStream，向服务端发送数据

        PrintWriter pw = new PrintWriter(httpURLConnection.getOutputStream());
        pw.print("userphone=110&password=123456");
        pw.flush();
        String content = null;
        if (httpURLConnection.getResponseCode() == 200) {
            InputStream is = httpURLConnection.getInputStream();// inputstream
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                content = line;
            }
        }
        httpURLConnection.disconnect();

        return content;
    }

    /**
     * 获取图片转换成bitmap
     *
     * @return
     * @throws IOException
     */
    public static Bitmap image() throws IOException {
        URL url = new URL("http://pic.4j4j.cn/upload/pic/20121024/5d1c3435ae.jpg");
//        URL url = new URL("http://10.50.8.79:8088/elife/img/cooker.jpg");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//        httpURLConnection.setRequestMethod("POST");

        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(8000);

//        httpURLConnection.setDoOutput(true);
        if (httpURLConnection.getResponseCode() == 200) {
            InputStream is = httpURLConnection.getInputStream();

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        }
        return null;
    }

    /**
     * 获取图片保存到sd卡或，内部存储目录
     *
     * @throws IOException
     */
    public static void file() throws IOException {
        URL url = new URL("http://pic.4j4j.cn/upload/pic/20121024/1b50bd51e2.jpg");
//        URL url = new URL("http://10.50.8.79:8088/elife/img/cooker.jpg");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//        httpURLConnection.setRequestMethod("POST");

        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(8000);
//        httpURLConnection.setDoOutput(true);

        Log.e("--------------", "" + httpURLConnection.getResponseCode());

        if (httpURLConnection.getResponseCode() == 200) {
            InputStream is = httpURLConnection.getInputStream();

            File file = new File("/storage/sdcard/0f63618b1.jpg");

            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            BufferedInputStream br = new BufferedInputStream(is);

            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(file, true));
            byte[] b = new byte[200];
            int end = -1;
            while ((end = br.read(b)) != -1) {
                bw.write(b, 0, end);
            }

            bw.close();
        }
        httpURLConnection.disconnect();
    }

    /**
     * 获取图片转换成bitmap
     *
     * @return
     * @throws IOException
     */
    public static void imageResult(final ImageView view, final String urlStr, final OnCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(urlStr);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);

                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream is = httpURLConnection.getInputStream();
                        Log.e("-------", "--------------------");
                        final Bitmap bitmap = BitmapFactory.decodeStream(is);
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.setBitmap(bitmap, view);
                            }
                        });

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


    public interface OnCallBack {
        void setBitmap(Bitmap bitmap, ImageView view);
    }
}
