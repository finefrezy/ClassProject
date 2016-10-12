package com.elife.classproject.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tzhang on 2016/9/8.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "android";
    private static final String TABLE_NAME = "student";
    private static final int DATABASE_VERSION = 4;

    private static DatabaseHelper sDatabaseHelper;


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 懒汉式
    public static DatabaseHelper getInstance(Context context) {
        if(null == sDatabaseHelper) {
            sDatabaseHelper = new DatabaseHelper(context);
        }
        return sDatabaseHelper;
    }


    /**
     * 在第一使用数据库的时候执行创建本地数据库与表
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("DatabaseHelper", "onCreate");
        String createTable = "create table " + TABLE_NAME + "(" +
                "stuId integer primary key," +// _ID
                "stuName text)";

        String insert = "insert into " + TABLE_NAME + " values(123,'zhangsan')";
        sqLiteDatabase.execSQL(createTable);
        sqLiteDatabase.execSQL(insert);
    }

    // 多个版本需要循环更新  NEW VERSION---OLD VERSION
    // i old i1new
    // @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.e("DatabaseHelper", "onUpgrade");
//        String sql = "alter table " + TABLE_NAME + " add column age integer";
//        sqLiteDatabase.execSQL(sql);

       for (int i = oldVersion; i <= newVersion; i++) {
           switch (i) {
               case 2:
                   // 升级相关的代码
                   break;
               case 3:
                   // 升级相关的代码
                   break;
               case 4:
                   // 升级相关的代码
                   break;
           }
       }

//        sqLiteDatabase.beginTransaction();
//
//        String alterSql = "alter table " + TABLE_NAME + " rename to temp_" + TABLE_NAME;// temp_student
//        String createTable = "create table " + TABLE_NAME + "(" +
//                "stuId integer primary key," +
//                "stuName text, ages integer, gender text, school text)";
//
//        String insertSql = "insert into " + TABLE_NAME + " select stuId,stuName,age,'','' from temp_" + TABLE_NAME;
//        String dropSql = "drop table temp_" + TABLE_NAME;
//
//        String sql = "insert into " + TABLE_NAME + " values(11,'12',10, '男','厦门大学')";
//
//        sqLiteDatabase.execSQL(alterSql);
//        sqLiteDatabase.execSQL(createTable);
//        sqLiteDatabase.execSQL(insertSql);
//        sqLiteDatabase.execSQL(dropSql);
//        sqLiteDatabase.execSQL(sql);
//
//        sqLiteDatabase.setTransactionSuccessful();
//
//        // 在调用此方法时，如果没有接收到setTransactionSuccessful设置的标志，就会回滚
//        sqLiteDatabase.endTransaction();


    }


}
