package com.example.jonyz.jonydownload.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jonyz.jonydownload.Bean.UrlBean;

import java.util.ArrayList;
import java.util.List;

/**线程操作类，用于对数据库进行操作
 * Created by JonyZ on 2017/8/25.
 */

public class ThreadUtil  {

    private static final String TAG = "ThreadUtil";
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
        readableDatabase.insert("download_info",null,values);
        readableDatabase.close();
    }

    /**
     * 删除数据库线程
     */
    public synchronized  void delThread(String  url) {
        readableDatabase = dbUtil.getReadableDatabase();
        readableDatabase.delete("download_info","url=?",new String[]{url});
        readableDatabase.close();
    }

    /**
     * 更新线程
     */
    public synchronized void updateThread(UrlBean urlBean) {
        readableDatabase=dbUtil.getReadableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("finished",urlBean.getFinished());
        readableDatabase.update("download_info",contentValues,null,new String[]{urlBean.getUrl()});
    }

    /**
     * 查询数据库线程
     * @param url
     * @return
     */
    public synchronized List<UrlBean>queryThread(String url){
        Log.i(TAG, "queryThread: url: " + url);
        readableDatabase=dbUtil.getReadableDatabase();
        List<UrlBean> list=new ArrayList<>();
        Cursor cursor=readableDatabase.query("download_info", null, "url = ?", new String[] { url }, null, null, null);
        while (cursor.moveToNext()){
            UrlBean urlBean= new UrlBean();
            urlBean.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            urlBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            urlBean.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            urlBean.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            urlBean.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));
            list.add(urlBean);
        }

        cursor.close();
        readableDatabase.close();

        //readableDatabase.query("urlBean",)
        return list;
    }
}
