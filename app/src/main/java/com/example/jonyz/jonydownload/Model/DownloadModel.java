package com.example.jonyz.jonydownload.Model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.jonyz.jonydownload.Bean.FileBean;
import com.example.jonyz.jonydownload.Contact.DownloadContract;
import com.example.jonyz.jonydownload.Service.DownloadService;
import com.example.jonyz.jonydownload.Utils.Config;

/**
 * Created by JonyZ on 2017/8/23.
 */

public class DownloadModel implements DownloadContract.IDownloadModel,View.OnClickListener{

    private static final String TAG = DownloadModel.class.getSimpleName();
    private Intent intent;

    @Override
    public void startDownload(FileBean fileBean, Context context) {
        //开始下载
        intent = new Intent(context,DownloadService.class);
        intent.setAction(Config.ACTION_START);
        intent.putExtra("fileBean",fileBean);
        Log.d(TAG, "startDownload: 开启下载的服务");
        context.startService(intent);

    }

    @Override
    public void stopDownload(FileBean fileBean, Context context) {
        //暂停下载
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(Config.ACTION_STOP);
        intent.putExtra("fileBean", fileBean);
        Log.d(TAG, "stopDownload: 停止下载服务");
        context.startService(intent);
    }

    @Override
    public void onClick(View view) {

    }
}
