package com.example.jonyz.jonydownload.Presenter;

import android.content.Context;

import com.example.jonyz.jonydownload.Bean.FileBean;
import com.example.jonyz.jonydownload.Contact.DownloadContract;
import com.example.jonyz.jonydownload.Model.DownloadModel;

/**
 * Created by JonyZ on 2017/8/23.
 */

public class DownloadPresenter implements DownloadContract.IDownPresenter {
    DownloadModel downloadModel=new DownloadModel();
    @Override
    public void startDownload(FileBean fileBean, Context context) {
        downloadModel.startDownload(fileBean,context);
    }

    @Override
    public void stopDownload(FileBean fileBean, Context context) {
        downloadModel.stopDownload(fileBean,context);

    }
}
