package com.elife.classproject.viewinject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elife.classproject.R;

/**
 * 写一个BaseActivity可以完成方法调用的封装
 */
@ContentView(R.layout.activity_inject_test)
public class InjectTestActivity extends AppCompatActivity {

    @ViewInjector(R.id.btn1)
    private Button mBtn1;
    @ViewInjector(R.id.btn2)
    private Button mBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);


    }

    @OnClick({ R.id.btn1, R.id.btn2 })
    public void onClickBtn(View view) {
        switch(view.getId()){
            case R.id.btn1:
                Toast.makeText(InjectTestActivity.this, "btn123", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(InjectTestActivity.this, "btn222", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
