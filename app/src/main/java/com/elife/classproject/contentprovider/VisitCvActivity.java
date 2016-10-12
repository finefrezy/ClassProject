package com.elife.classproject.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elife.classproject.R;

public class VisitCvActivity extends AppCompatActivity implements View.OnClickListener {

    Button mAddBtn;
    Button mDeleteBtn;
    Button mQueryBtn;
    Button mUpdateBtn;

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_cv);
        mAddBtn = (Button) findViewById(R.id.content_add_btn);
        mDeleteBtn = (Button) findViewById(R.id.content_delete_btn);
        mQueryBtn = (Button) findViewById(R.id.content_query_btn);
        mUpdateBtn = (Button) findViewById(R.id.content_update_btn);

        mAddBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);
        mQueryBtn.setOnClickListener(this);
        mUpdateBtn.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.tv);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.content_add_btn:
                break;
            case R.id.content_delete_btn:
                break;
            case R.id.content_query_btn:
                query();
                break;
            case R.id.content_update_btn:
                break;
        }
    }

    // 测试用
    private void query() {
        ContentResolver contentResolver = getContentResolver();
        Uri selectUri = Uri.parse("content://com.elife.classproject/student");
        Cursor cursor = contentResolver.query(selectUri, null, null, null, null);
//        contentResolver.insert()
        StringBuffer stringBuffer = new StringBuffer("");


        // do while循环cursor遍历
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex("stuId"));
                String name = cursor.getString(cursor.getColumnIndex("stuName"));
                stringBuffer.append(id);
                stringBuffer.append(name);
                stringBuffer.append("--");
            } while (cursor.moveToNext());

        }


// for循环cursor遍历
//        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()) {
//            String id = cursor.getString(cursor.getColumnIndex("stuId"));
//            String name = cursor.getString(cursor.getColumnIndex("stuName"));
//            stringBuffer.append(id);
//            stringBuffer.append(name);
//            stringBuffer.append("--");
//
//        }


        mTextView.setText(stringBuffer.toString());
    }
}
