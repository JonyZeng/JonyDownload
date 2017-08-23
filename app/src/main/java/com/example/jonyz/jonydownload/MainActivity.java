package com.example.jonyz.jonydownload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.jonyz.jonydownload.Adapter.FileAdapter;
import com.example.jonyz.jonydownload.Bean.FileBean;
import com.example.jonyz.jonydownload.Bean.UrlBean;
import com.example.jonyz.jonydownload.Contact.DownloadContract;
import com.example.jonyz.jonydownload.Utils.Config;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DownloadContract.IDownloadView,View.OnClickListener{

    ListView listView;
    private FileAdapter mAdapter;
    private UrlBean urlBean;
    private List<FileBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        initData();
        initView();
        mAdapter = new FileAdapter(this, list);
        listView.setAdapter(mAdapter);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        listView = (ListView) findViewById(R.id.LV_down);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        //文件类集合
        list = new ArrayList<>();
        list.add(new FileBean(0,getfileName(Config.URLONE),0,0,Config.URLONE)); //(文件ID，文件名，文件大小，已经下载大小,URL)
        list.add(new FileBean(1,getfileName(Config.URLTWO),0,0,Config.URLTWO)); //(文件ID，文件名，文件大小，已经下载大小,URL)
        list.add(new FileBean(2,getfileName(Config.URLTHREE),0,0,Config.URLTHREE)); //(文件ID，文件名，文件大小，已经下载大小,URL)
        list.add(new FileBean(3,getfileName(Config.URLFOUR),0,0,Config.URLFOUR)); //(文件ID，文件名，文件大小，已经下载大小,URL)
    }

    @Override
    public String getfileName(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {

    }
}
