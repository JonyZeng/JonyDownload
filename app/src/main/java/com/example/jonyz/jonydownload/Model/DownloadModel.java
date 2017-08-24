package com.example.jonyz.jonydownload.Model;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.jonyz.jonydownload.Bean.FileBean;
import com.example.jonyz.jonydownload.Contact.DownloadContract;
import com.example.jonyz.jonydownload.Service.DownloadService;
import com.example.jonyz.jonydownload.Utils.Config;

import java.io.Serializable;

/**
 * Created by JonyZ on 2017/8/23.
 */

public class DownloadModel implements DownloadContract.IDownloadModel,View.OnClickListener{

    private Intent intent;

    @Override
    public void startDownload(FileBean fileBean, Context context) {
        //开始下载
        intent = new Intent(context,DownloadService.class);
        intent.setAction(Config.ACTION_START);
        intent.putExtra("fileBean", (Serializable) fileBean);
        context.startService(intent);

    }

    @Override
    public void stopDownload(FileBean fileBean, Context context) {
        //暂停下载
    }

    @Override
    public void onClick(View view) {

    }
}