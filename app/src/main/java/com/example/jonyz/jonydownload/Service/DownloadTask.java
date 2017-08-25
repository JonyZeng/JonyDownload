package com.example.jonyz.jonydownload.Service;

import android.content.Context;

import com.example.jonyz.jonydownload.Bean.FileBean;
import com.example.jonyz.jonydownload.Bean.UrlBean;
import com.example.jonyz.jonydownload.Utils.DBUtil;

import java.util.List;

/**
 * 具体的执行下载代码
 * Created by JonyZ on 2017/8/24.
 */

public class DownloadTask {

    public List<UrlBean> urlBeanList;
    private FileBean fileBean;
    public DBUtil dbUtil;
    //下载文件
    public void download(Context context){
        //需要一个操作数据库的帮助类
        dbUtil.getInstance(context);

        fileBean = new FileBean();
       // UrlBean urlBeanThread=new UrlBean(fileBean.getUrl(),)
        //urlBeanList =

    }



}
