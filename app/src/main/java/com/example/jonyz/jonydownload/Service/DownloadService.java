package com.example.jonyz.jonydownload.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.jonyz.jonydownload.Bean.FileBean;
import com.example.jonyz.jonydownload.Utils.Config;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 多文件多线程断点下载服务
 * Created by JonyZ on 2017/8/23.
 */

public class DownloadService extends Service {


    private FileBean fileBean;
    private DownloadService.initThread initThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Config.ACTION_START)) {
            //接收到action，开启线程下载
            //创建实体类对象
            fileBean = (FileBean) intent.getSerializableExtra("fileBean");
            //创建一个线程类，在线程中执行耗时操作
            initThread = new initThread();
           //DownloadTask();

        }
        return super.onStartCommand(intent, flags, startId);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        switch (msg.what){
            case Config.MSG_INIT://开始下载

        }
        }
    };

    /**
     * 初始化的线程类
     */
    private class initThread extends Thread {

        private URL url;
        private int responseCode;
        private int length = 0;   //判断长度
        private RandomAccessFile randomAccessFile;

        @Override
        public void run() {
            //创建网络获取对象
            HttpURLConnection connection = null;
            //获取URL对象
            try {
                url = new URL(fileBean.getUrl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(2000);
                connection.setRequestMethod("GET");
                responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    length = connection.getContentLength();
                }
                if (length <= 0) {//说明下载文件不存在
                    return;
                }
                //文件存在，开始下载
                //判断文件路径是否存在
                File dir = new File(Config.DownloadPath);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File file = new File(dir, fileBean.getFileName());
                randomAccessFile = new RandomAccessFile(file, "rwd");//随机访问，随时读写
                randomAccessFile.setLength(length);
                fileBean.setDownSize(length);   //设置文件的大小
                //将fileBean通过handler传递过去
                Message msg = new Message();
                msg.what = Config.MSG_INIT;
                msg.obj = fileBean;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            super.run();
        }
    }
}
