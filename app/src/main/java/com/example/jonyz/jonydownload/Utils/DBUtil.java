package com.example.jonyz.jonydownload.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库帮组类
 * Created by JonyZ on 2017/8/25.
 */

public class DBUtil extends SQLiteOpenHelper {
    private static final String TAG = DBUtil.class.getSimpleName();
    public static int VERSION = 1;
    public static String dateName = "download.db";
    private static final String CREATE = "create table download_info(_id integer primary key autoincrement, " +
            "thread_id integer, url varchar, start integer, end integer, finished integer)";
    public static String DROP = "drop table if exists thread_info";
    private static DBUtil dbUtil;
    public DBUtil(Context context) {
        super(context, dateName, null, VERSION);
        Log.i(TAG, "DBUtil: create db");
    }

    //单例模式获取对象
    public static DBUtil getInstance(Context context) {
        if (dbUtil == null) {
            Log.i(TAG, "DBUtil.getInstance: ");
            dbUtil = new DBUtil(context);

        }
        return dbUtil;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP);
        sqLiteDatabase.execSQL(CREATE);
    }
    //创建表

    //删除表
}
