package com.example.jonyz.jonydownload.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.jonyz.jonydownload.Bean.UrlBean;

import java.util.List;

/**线程操作类，用于对数据库进行操作
 * Created by JonyZ on 2017/8/25.
 */

public class ThreadUtil  {

    private final DBUtil dbUtil;
    private SQLiteDatabase readableDatabase;

    public ThreadUtil(Context context) {
        dbUtil = DBUtil.getInstance(context);
    }

    /**
     * 加锁,插入数据库线程
     */
    public synchronized void insertThread(UrlBean urlBean) {
        readableDatabase = dbUtil.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("thread_id",urlBean.getId());
        values.put("url",urlBean.getUrl());
        values.put("start",urlBean.getStart());
        values.put("end",urlBean.getEnd());
        values.put("finished",urlBean.getFinished());
        readableDatabase.insert("urlBean",null,values);
        readableDatabase.close();
    }

    /**
     * 删除数据库线程
     */
    public synchronized  void delThread(UrlBean urlBean) {
        readableDatabase = dbUtil.getReadableDatabase();
        readableDatabase.delete("urlBean","url=?",new String[]{urlBean.getUrl()});
        readableDatabase.close();
    }

    /**
     * 更新线程
     */
    public synchronized void updateThread(UrlBean urlBean) {
        readableDatabase=dbUtil.getReadableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("finished",urlBean.getFinished());
        readableDatabase.update("urlBean",contentValues,null,new String[]{urlBean.getUrl()});
    }

    /**
     * 查询数据库线程
     * @param Url
     * @return
     */
    public synchronized List<UrlBean>queryThread(String Url){
        readableDatabase=dbUtil.getReadableDatabase();
        //readableDatabase.query("urlBean",)
        return null;
    }
}
