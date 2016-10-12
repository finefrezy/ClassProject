package com.elife.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEt;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mEt = (EditText) findViewById(R.id.edit);

        mBtn = (Button) findViewById(R.id.confirm);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                Log.e("-----------", "SecondActivity->onClick");

                Intent intent = new Intent();

                intent.putExtra("edValue", mEt.getText().toString());
                // ResultCode,
                setResult(RESULT_OK, intent);

                SecondActivity.this.finish();
                 break;
        }
    }
}
