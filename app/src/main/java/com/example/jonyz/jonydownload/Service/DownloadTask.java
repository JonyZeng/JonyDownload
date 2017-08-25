package com.example.jonyz.jonydownload.Service;

import android.content.Context;

import com.example.jonyz.jonydownload.Bean.FileBean;
import com.example.jonyz.jonydownload.Bean.UrlBean;
import com.example.jonyz.jonydownload.Utils.DBUtil;
import com.example.jonyz.jonydownload.Utils.ThreadUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 具体的执行下载代码
 * Created by JonyZ on 2017/8/24.
 */

public class DownloadTask {
    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    public List<UrlBean> urlBeanList;
    private FileBean fileBean;
    public DBUtil dbUtil;
    private ThreadUtil threadUtil;
    public int threadCount=1;
    public Context context;

    public DownloadTask(Context context,FileBean fileBean,int threadCount) {
        this.threadCount=threadCount;
        this.fileBean=fileBean;
        this.context=context;
        threadUtil = new ThreadUtil(context);
    }

    //下载文件
    public void download(Context context){
        //需要一个操作数据库的帮助类
        dbUtil.getInstance(context);
        fileBean = new FileBean();
        //通过查询list获取大小，分配线程
        urlBeanList =threadUtil.queryThread(fileBean.getUrl());
        //获取到数据库里面查询的list
        if (urlBeanList.size()==0){
            //获取文件大小
            int length=urlBeanList.size();
            //获取需要分的模块
            int block=length/threadCount;

            for (int i = 0; i < threadCount; i++) {
                //确定开始下载的位置
                int star=block*i;
                //确定结束下载的位置
                int end=(i+1)*block-1;
                if (i==threadCount-1){  //最后一个线程下载的大小
                    end=length-1;
                }
                //开启线程
                UrlBean urlBean=new UrlBean(fileBean.getUrl(),i,star,end,0);
                urlBeanList.add(urlBean);
            }
        }
    }



}
