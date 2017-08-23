package com.example.jonyz.jonydownload.Presenter;

import com.example.jonyz.jonydownload.Contact.DownloadContract;
import com.example.jonyz.jonydownload.Model.DownloadModel;

/**
 * Created by JonyZ on 2017/8/23.
 */

public class DownloadPresenter implements DownloadContract.IDownPresenter {
    DownloadModel downloadModel=new DownloadModel();
    @Override
    public void startDownload() {
        downloadModel.startDownload();
    }

    @Override
    public void stopDownload() {
        downloadModel.stopDownload();

    }
}
