package com.elife.classproject.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.elife.classproject.application.MyApplication;

/**
 * Created by tzhang on 2016/9/8.
 */
public class StudentDbUtil {


    public void add() {
//        ContentValues cv = new ContentValues();
//        cv.put("stuId", 10);
//        cv.put("stuName", "123");
//        // 查一下第二个参数,
//        DatabaseHelper.getInstance(ctx).getWritableDatabase().insert("student", null, cv);

        MyApplication.getDbInstance().getWritableDatabase().execSQL("insert into student values(13,'abc')");

    }

    public void update() {
        ContentValues cv = new ContentValues();
        cv.put("stuId", 10);
        cv.put("stuName", "456");
        MyApplication.getDbInstance().getWritableDatabase().update("student", cv, "stuId=?", new String[]{"10"});
    }

    public void delete() {
        MyApplication.getDbInstance().getWritableDatabase().delete("student","stuId=?", new String[]{"10"});
    }

    public void query() {
        Cursor cursor = null;
        try {
            cursor =   MyApplication.getDbInstance().getReadableDatabase().query("student", new String[]{"stuId","stuName"}, null, null, null, null, null);

            while(cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex("stuId"));
                String name = cursor.getString(cursor.getColumnIndex("stuName"));
                Log.e("info--", name + id);
            }
        } catch (Exception e) {

        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }



    }
}
