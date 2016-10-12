package com.elife.classproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.elife.classproject.sqlite.StudentDbUtil;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener{

    Button mAdd;
    Button mUpdate;
    Button mQuery;
    Button mDelete;
    ListView mListView;

    StudentDbUtil mStudentDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        mAdd = (Button) findViewById(R.id.add);
        mUpdate = (Button) findViewById(R.id.update);
        mQuery = (Button) findViewById(R.id.seaarch);
        mDelete = (Button) findViewById(R.id.delete);
        mListView = (ListView) findViewById(R.id.lv);
        mAdd.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mQuery.setOnClickListener(this);
        mDelete.setOnClickListener(this);

        mStudentDb = new StudentDbUtil();




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add:
                mStudentDb.add();
                break;
            case R.id.update:
                mStudentDb.update();
                break;
            case R.id.seaarch:
                mStudentDb.query();
                break;
            case R.id.delete:
                mStudentDb.delete();
                break;
        }
    }
}
