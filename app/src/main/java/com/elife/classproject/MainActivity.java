package com.elife.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    EditText mEditUserName;
    EditText mEditUserPwd;
    Button mLoginButton;
    StudentModel mStuModel;

    TextView mTextInfo;


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch ((msg.what)) {
                case 0:

                    mTextInfo.setText(mStuModel.getStuName() + mStuModel.getAvatar());

                    break;
                case 1:
                    mTextInfo.setText("用户名或密码出错");
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);

        mEditUserName = (EditText) findViewById(R.id.username);
        mEditUserPwd = (EditText) findViewById(R.id.userpwd);
        mLoginButton = (Button) findViewById(R.id.login);
        mTextInfo = (TextView) findViewById(R.id.info);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = mEditUserName.getText().toString();
                final String userPwd = mEditUserPwd.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            post(userName, userPwd);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });


        Intent intent = getIntent();
        mEditUserName.setText(intent.getStringExtra("username"));
        mEditUserPwd.setText(intent.getStringExtra("userPwd"));
        Bundle bundle = intent.getExtras();
        mEditUserName.setText(bundle.getString("username"));
        mEditUserPwd.setText( bundle.getString("userPwd"));
    }


    private  void post(String username, String userPwd) throws Exception {
        // 创建URL对象
        URL url = new URL("http://10.50.8.87:8088/ClassProject/login");
        // 获取该URL与服务器的连接对象
        URLConnection conn = url.openConnection();
        // 设置头信息，请求头信息了解
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");

        // 设置可以操作连接的输入输出流
        conn.setDoOutput(true);// 默认为false，允许使用输出流
        conn.setDoInput(true);// 默认为true，允许使用输入流

        // 传参数
        PrintWriter pw = new PrintWriter(conn.getOutputStream());
        pw.print("data=" + username + "," +  userPwd);

        pw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String lineContent = null;
        String content = null;

        while ((lineContent = br.readLine()) != null) {
            content = lineContent;
            Log.e("---------", lineContent);
        }

        if(content.equals("1")) {
            mHandler.sendEmptyMessage(1);
        } else {
            Gson gson = new Gson();
            mStuModel = gson.fromJson(content, StudentModel.class);

            mHandler.sendEmptyMessage(0);
        }




        Log.e("---------", "end");
        pw.close();
        br.close();

    }
}
