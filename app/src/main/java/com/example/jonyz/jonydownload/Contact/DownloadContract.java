package com.example.jonyz.jonydownload.Contact;

import android.content.Context;

import com.example.jonyz.jonydownload.Bean.FileBean;

/**
 * Created by JonyZ on 2017/8/23.
 */

public class DownloadContract {
    public interface IDownloadView{
        //根据Url获取文件名
         String getfileName(String url);

    }

    /**
     * 复杂的获取数据
     */
    public interface IDownloadModel{
        //开始下载
        void startDownload(FileBean fileBean, Context context);
        //停止下载
        void stopDownload(FileBean fileBean, Context context);
    }

    /**
     * 复杂的逻辑操作
     */
    public interface IDownPresenter{
        //开始下载
        void startDownload(FileBean fileBean, Context context);
        //停止下载
        void stopDownload(FileBean fileBean, Context context);
    }
}
