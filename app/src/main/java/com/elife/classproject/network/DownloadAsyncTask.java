package com.elife.classproject.network;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by tzhang on 2016/9/11.
 *
 *
 * onPreExecute-》doInBackground--调用了publishProgress，里面的的参数传递onProgressUpdate-》onProgressUpdate
 *                doInBackground--执行结束后，其返回值传递给onPostExecute--》onPostExecute
 *
 *
 *
 */
public class DownloadAsyncTask extends AsyncTask<String, Integer, String> {
    TextView tv;
//    ProgressBar pb;
   public DownloadAsyncTask(TextView tv) {// , ProgressBar pb
        this.tv = tv;
//       this.pb = pb;
    }

    // 所有方法中最后执行，表示异步任务执行结束
    @Override
    protected void onPostExecute(String s) {
        Log.e("---------", "onPostExecute" + s);
        tv.setText(s);
    }
// 最先执行的方法，做一些开始的初始化操作
    @Override
    protected void onPreExecute() {
        Log.e("---------", "onPreExecute");
        tv.setText("0%");
//        pb.setProgress(0);
    }

    // 1,2,3,4...%
    @Override
    protected void onProgressUpdate(Integer... values) {
       Log.e("---------", "onProgressUpdate" + values[0] + "");
        tv.setText(values[0] + "%");
//        pb.setProgress(values[0]);
    }
// 后台执行的任务，也是异步任务的主要工作，耗时操作在里面写
    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setConnectTimeout(8000);
            httpURLConnection.setReadTimeout(8000);

            httpURLConnection.setDoOutput(true);
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream is = httpURLConnection.getInputStream();

                File file = new File("/storage/sdcard/tt.apk");
                if (file.exists()) {
                    file.delete();
                }

                BufferedInputStream br = new BufferedInputStream(is);
                BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(file, true));
                byte[] b = new byte[800];
                int end = -1;
                // 当前读取的长度/大小
                int size = 0;
// 文件的长度（大小）
                long length = httpURLConnection.getContentLength();
                while ((end = br.read(b)) != -1) {
                    size = size + end;
                    // 得到百分比数，一旦调用这个方法，就会主动调用onProgressUpdate方法，publishProgress的参数作为onProgressUpdate方法的参数
                   publishProgress((int)(size * 100 /length));
                    bw.write(b, 0, end);
                }

                bw.close();
                br.close();
            }
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 后台任务结束，返回值到onPostExecute方法
        return "success";
    }

}
