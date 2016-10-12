package com.elife.classproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elife.classproject.constant.ConstantData;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditUserName;
    private EditText mEditUserPwd;

    private Button mLoginBtn;

    private CheckBox mChbox;

    private TextView mTvClick;

    private boolean isRemember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.linear_test);

        mEditUserName = (EditText) findViewById(R.id.username);
        mEditUserPwd = (EditText) findViewById(R.id.password);
        mChbox = (CheckBox) findViewById(R.id.remember_user);

        mLoginBtn = (Button) findViewById(R.id.login);
        mLoginBtn.setOnClickListener(this);

        mLoginBtn.setText("Login");

        mTvClick = (TextView) findViewById(R.id.tv_click);
        mTvClick.setOnClickListener(this);


        SharedPreferences settings = getSharedPreferences(ConstantData.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        mEditUserName.setText(settings.getString("username", ""));
        mEditUserPwd.setText(settings.getString("pwd", ""));
        mChbox.setChecked(settings.getBoolean("checked", false));

        mChbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(getApplicationContext(), b + "", Toast.LENGTH_SHORT).show();
                isRemember = b;
            }
        });

//        mLoginBtn.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

//    public void click(View view) {
//        Toast.makeText(LoginActivity.this, "click", Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String userName = mEditUserName.getText().toString();
                String userPwd = mEditUserPwd.getText().toString();

                SharedPreferences settings = getSharedPreferences(ConstantData.SHARED_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                if (isRemember) {
                    // 保存用户名
                    editor.putString("username", userName);
                    editor.putString("pwd", userPwd);
                    // Commit the edits!

                } else {
                    // 把数据删除
                    editor.putString("username", "");
                    editor.putString("pwd", "");
                }
                editor.putBoolean("checked", isRemember);
                editor.commit();
                // Intent 意图 显示意图-隐式意图

                //用intent启动拨打电话
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+userName));
//               try {
//                   startActivity(intent);
//               } catch (Exception e) {
//
//               }

                // 隐式意图跳转
                Intent intent = new Intent();
                intent.setAction("com.elife.classproject.Main");
                // 传值
                intent.putExtra("username", userName);
                intent.putExtra("userPwd", userPwd);
                //  Bundle传值
                Bundle bundle = new Bundle();
                bundle.putString("username", userName + "123");
                bundle.putString("userPwd", userPwd + "123");


                intent.putExtras(bundle);
                // 传递对象

                startActivity(intent);

                LoginActivity.this.finish();

                break;
            case R.id.tv_click:
                mTvClick.setText("锄禾日当午");
                Toast.makeText(LoginActivity.this, "text", Toast.LENGTH_LONG).show();

                Intent in = new Intent();
                in.setClassName("com.elife.chairmanfarm", "com.elife.chairmanfarm.activity.StickyActivity");
                startActivity(in);
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("------------", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
