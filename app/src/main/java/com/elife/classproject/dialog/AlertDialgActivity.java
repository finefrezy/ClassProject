package com.elife.classproject.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elife.classproject.R;
// http://www.jianshu.com/p/6caffdbcd5dbia

// Material design
// activity以dialog方式显示出来
public class AlertDialgActivity extends AppCompatActivity {

    Button mBtnDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialg);
        mBtnDialog = (Button) findViewById(R.id.btn_dialg);

        mBtnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormal();
            }
        });


    }

    private  void showNormal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialgActivity.this);

        builder.setIcon(R.drawable.live_head_passerby);

        builder.setTitle("虞美人");
//        builder.setMessage("雕栏玉砌应犹在，只是朱颜改。问君能有多少愁？恰似一江春水向东流。");

        builder.setMultiChoiceItems(new String[]{"1", "2"}, new boolean[]{true, false}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                Toast.makeText(getApplicationContext(), i + "--setMultiChoiceItems--" + i + ", " + b, Toast.LENGTH_SHORT).show();
            }
        });

//        builder.setView()

        builder.setNegativeButton("取消", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), i + "--setNegativeButton", Toast.LENGTH_SHORT).show();
//                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("确定", new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), i + "--setPositiveButton", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        builder.setNeutralButton("中性", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), i + "--setNeutralButton", Toast.LENGTH_SHORT).show();
            }
        });
// 显示dialog
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("AlertDialgActivity","onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("AlertDialgActivity","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("AlertDialgActivity","onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("AlertDialgActivity","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("AlertDialgActivity","onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("AlertDialgActivity","onRestart");
    }


}
