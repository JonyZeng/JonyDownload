package com.example.jonyz.jonydownload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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
    private UIRecive mRecive;
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
        mRecive=new UIRecive();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.ACTION_UPDATE);
        intentFilter.addAction(Config.ACTION_FINISHED);
        intentFilter.addAction(Config.ACTION_START);
        registerReceiver(mRecive, intentFilter);
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

    @Override
    protected void onDestroy() {
        unregisterReceiver(mRecive);
        super.onDestroy();
    }

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {

    }

    /**
     * 刷新Ui
     */
    private class UIRecive extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            if (Config.ACTION_UPDATE.equals(intent.getAction())) {
                // 更新进度条的时候
                int finished = intent.getIntExtra("finished", 0);
                int id = intent.getIntExtra("id", 0);
                mAdapter.updataProgress(id, finished);

            } else if (Config.ACTION_FINISHED.equals(intent.getAction())){
                // 下载结束的时候
                FileBean fileBean = (FileBean) intent.getSerializableExtra("fileBean");
                mAdapter.updataProgress(fileBean.getId(), 0);
                Toast.makeText(MainActivity.this, list.get(fileBean.getId()).getFileName() + "下载完毕", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
