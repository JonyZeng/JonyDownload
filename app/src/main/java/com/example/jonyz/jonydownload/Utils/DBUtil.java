package com.example.jonyz.jonydownload.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮组类
 * Created by JonyZ on 2017/8/25.
 */

public class DBUtil extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String dateName = "download.db";
    public static String CREATE = "create table urlBean(_id integer primary key autoincrement, " +
            "thread_id integer, url text, start integer, end integer, finished integer)";
    public static String DROP = "drop table if exists thread_info";
    private static DBUtil dbUtil;

    public DBUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBUtil(Context context) {
        super(context, dateName, null, VERSION);
    }

    //单例模式获取对象
    public static DBUtil getInstance(Context context) {
        if (dbUtil == null) {
            dbUtil = new DBUtil(context);
        }
        return dbUtil;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP);
        sqLiteDatabase.execSQL(CREATE);
    }
    //创建表

    //删除表
}
