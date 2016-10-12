package com.elife.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    private Button mBtn;
    private Button mBtn1;
    private TextView mTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mBtn = (Button) findViewById(R.id.btn);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mTv = (TextView) findViewById(R.id.tv);

        ImageView imageView = new ImageView(this);


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("-----------", "FirstActivity->mBtn->onClick");
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);
                // requestCode
                startActivityForResult(intent, 11);
            }
        });
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("-----------", "FirstActivity->mBtn1->onClick");
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);
                // requestCode
                startActivityForResult(intent, 12);


            }
        });
    }

    // 回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("-----------", "FirstActivity->onActivityResult->requestCode " + requestCode);
        Log.e("-----------", "FirstActivity->onActivityResult->resultCode " + resultCode);



        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 11:
                    Toast.makeText(getApplicationContext(),"11", Toast.LENGTH_LONG).show();
                    break;
                case 12:
                    Toast.makeText(getApplicationContext(),"12", Toast.LENGTH_LONG).show();
                    break;
            }
        }

//        String callBackValue = data.getStringExtra("edValue");
//        mTv.setText(callBackValue);
    }
}
