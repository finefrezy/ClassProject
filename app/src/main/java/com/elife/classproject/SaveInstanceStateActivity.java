package com.elife.classproject;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SaveInstanceStateActivity extends AppCompatActivity {

    private EditText mEdit;
    private CheckBox mCheck;
    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);
        mEdit = (EditText) findViewById(R.id.edit);
        mCheck = (CheckBox) findViewById(R.id.checkbox);
        mBtn = (Button) findViewById(R.id.btn);
        Log.e("SaveInstanceState","------------------------");
        Log.e("SaveInstanceState","onCreate");

        if (null != savedInstanceState) {
            if (savedInstanceState.getLong("currPosition", 0) == 0) {
//                Toast.makeText(SaveInstanceStateActivity.this, "qqq", Toast.LENGTH_LONG).show();
                Log.e("SaveInstanceState","onCreate->savedInstanceState");
            } else {
//                Toast.makeText(getApplicationContext(), savedInstanceState.getLong("currPosition", 0) + "", Toast.LENGTH_LONG).show();
                Log.e("SaveInstanceState","onCreate->savedInstanceState->" + savedInstanceState.getLong("currPosition", 0) );
            }
        }


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 	if (SaveInstanceStateActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){// 横屏
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 竖屏
                    	}
                	else if(SaveInstanceStateActivity.this.getResources().getConfiguration().orientation ==Configuration.ORIENTATION_PORTRAIT) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    	}
            }
        });

    }

    /**
     * onSaveInstanceState()方法的默认实现
     * <p/>
     * 　　如果我们没有覆写onSaveInstanceState()方法, 此方法的默认实现会自动保存activity中的某些状态数据, 比如activity中各种UI控件的状态.。
     * android应用框架中定义的几乎所有UI控件都恰当的实现了onSaveInstanceState()方法,因此当activity被摧毁和重建时, 这些UI控件会自动保存和恢复状态数据.
     *
     * 比如EditText控件会自动保存和恢复输入的数据,而CheckBox控件会自动保存和恢复选中状态.开发者只需要为这些控件指定一个唯一的ID(通过设置android:id属性即可),
     * 剩余的事情就可以自动完成了.如果没有为控件指定ID, 则这个控件就不会进行自动的数据保存和恢复操作。
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("SaveInstanceState","onSaveInstanceState");
        outState.putLong("currPosition", 12345);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("SaveInstanceState","onRestoreInstanceState");
        if (null != savedInstanceState) {
            if (savedInstanceState.getLong("currPosition", 0) == 0) {
                Log.e("SaveInstanceState","onRestoreInstanceState->savedInstanceState");
            } else {
                Log.e("SaveInstanceState","onRestoreInstanceState->savedInstanceState->" + savedInstanceState.getLong("currPosition", 0) );
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SaveInstanceState","onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("SaveInstanceState","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("SaveInstanceState","onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("SaveInstanceState","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SaveInstanceState","onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("SaveInstanceState","onRestart");
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("SaveInstanceState","onConfigurationChanged");
        if (SaveInstanceStateActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){// 横屏
         setContentView(R.layout.activity_first);
        }
        else if(SaveInstanceStateActivity.this.getResources().getConfiguration().orientation ==Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_second);
        }



    }
}
