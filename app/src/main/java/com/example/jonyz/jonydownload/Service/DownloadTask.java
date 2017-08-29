package com.example.jonyz.jonydownload.Service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jonyz.jonydownload.Bean.FileBean;
import com.example.jonyz.jonydownload.Bean.UrlBean;
import com.example.jonyz.jonydownload.Utils.Config;
import com.example.jonyz.jonydownload.Utils.DBUtil;
import com.example.jonyz.jonydownload.Utils.ThreadUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 具体的执行下载代码
 * Created by JonyZ on 2017/8/24.
 */

public class DownloadTask {
    private static final String TAG = DownloadTask.class.getSimpleName();
    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    public List<UrlBean> urlBeanList;
    private FileBean fileBean;
    public DBUtil dbUtil;
    private ThreadUtil threadUtil;
    public int threadCount=1;
    public Context context;
    private List<DownloadList> downloadLists=null;
    private UrlBean urlBean;
    public boolean isonPause=false;//判断是否暂停

    public DownloadTask(Context context,FileBean fileBean,int threadCount) {
        this.threadCount=threadCount;
        this.fileBean=fileBean;
        this.context=context;
        threadUtil = new ThreadUtil(context);
    }

    //下载文件
    public void download(){
        //if ()
        Log.i(TAG, "download:"+fileBean.getUrl());
        urlBeanList =threadUtil.queryThread(fileBean.getUrl());
        //获取到数据库里面查询的list
        if (urlBeanList.size()==0){ //数据库中不存在，第一次下载
            //获取文件大小
            int length=fileBean.getDownSize();
            //获取需要分的模块
            int block=length/threadCount;

            for (int i = 0; i < threadCount; i++) {
                //确定开始下载的位置
                int star=block*i;
                //确定结束下载的位置
                int end=(i+1)*block-1;
                if (i==threadCount-1){  //
                    end=length-1;   //最后一个线程下载结束的位置。
                }
                //开启线程
                urlBean = new UrlBean(fileBean.getUrl(),i,star,end,0);
                urlBeanList.add(urlBean);
            }
    }
        Log.d(TAG, "download:");
    //下载文件线程的内部类
    downloadLists = new ArrayList<>();
        for (UrlBean urlBean:urlBeanList) {
        DownloadList dowmload=new DownloadList(urlBean);
        DownloadTask.cachedThreadPool.execute(dowmload);
        downloadLists.add(dowmload);
        threadUtil.insertThread(urlBean);
    }
    }

    /**
     * 线程下载文件执行的类，继承thread
     */
    private class DownloadList extends Thread {


        private HttpURLConnection urlConnection=null;
        private RandomAccessFile accessFile=null;
        private InputStream inputStream=null;

        private File file;
        private Integer finished=0;
        private Intent intent;

        private UrlBean urlBean;
        private boolean isFinished=false;

        public DownloadList(UrlBean urlBean) {
            this.urlBean = urlBean;
        }

        @Override
        public void run() {//执行下载耗时操作
            //获取http对象
            try {
                URL url= new URL(fileBean.getUrl());
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(2000);
                    urlConnection.setRequestMethod("GET");
                    //设置下载的头部
                    int start=urlBean.getStart()+urlBean.getFinished();
                    //设置下载结束的位置
                    urlConnection.setRequestProperty("Range", "bytes=" + start + "-" + urlBean.getEnd());
                    //新建文件对象
                    file = new File(Config.DownloadPath, fileBean.getFileName());
                    //随机访问读写对象
                    accessFile = new RandomAccessFile(file, "rwd");
                    accessFile.seek(start);
                    //刷新当前以及下载的大小
                    finished +=urlBean.getFinished();
                    intent = new Intent();
                    intent.setAction(Config.ACTION_UPDATE);
                    int respCode=urlConnection.getResponseCode();
                    if (respCode==HttpURLConnection.HTTP_PARTIAL){  //请求成功
                        //获取输入流对象
                        inputStream = urlConnection.getInputStream();
                        //设置一个byte数组，中转数据
                        byte[] bytes = new byte[1024];
                        int length=-1;
                        //定义UI刷新时间
                        long time=System.currentTimeMillis();
                        while ((length=inputStream.read(bytes))!=-1){
                            accessFile.write(bytes,0,length);
                            //实时获取下载进度，刷新UI
                            finished+=length;
                            urlBean.setFinished(urlBean.getFinished()+length);
                            if (System.currentTimeMillis()-time>500){
                                time=System.currentTimeMillis();
                                intent.putExtra("finished",finished);
                                Log.d(TAG, "finished:"+finished);
                                intent.putExtra("id",fileBean.getId());
                                Log.d(TAG, "finished"+fileBean.getId());
                                context.sendBroadcast(intent);
                            }
                            if (isonPause){
                                threadUtil.updateThread(urlBean);
                                return;
                            }

                        }


                    }
                    //当前线程是否下载完成
                    isFinished = true;
                    //判断所有线程是否下载完成
                    checkAllFinished();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }finally {
                if (urlConnection!=null){
                    urlConnection.disconnect();
                }
                if (inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (accessFile!=null){
                    try {
                        accessFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            super.run();
        }

        private synchronized void checkAllFinished() {
            boolean allFinished=true;
            for (DownloadList down:downloadLists) {
                if (!down.isFinished)
                allFinished=false;
                break;
            }
            if (allFinished==true){
                Log.d(TAG, "checkAllFinished: 下载完成");
                threadUtil.delThread(fileBean.getUrl());
                intent=new Intent(Config.ACTION_FINISHED);
                intent.putExtra("urlBean",urlBean);
                context.sendBroadcast(intent);
            }
        }


    }
}
