package com.example.jonyz.jonydownload.Utils;

import android.os.Environment;

/**
 * 常量类
 * Created by JonyZ on 2017/8/23.
 */

public class Config {
    public static final String URLONE = "https://dldir1.qq.com/qqfile/qq/TIM1.2.0/21645/TIM1.2.0.exe";
    public static final String URLTWO = "http://www.imooc.com/download/Activator.exe";
    public static final String URLTHREE = "http://s1.music.126.net/download/android/CloudMusic_3.4.1.133604_official.apk";
    public static final String URLFOUR = "http://study.163.com/pub/study-android-official.apk";
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_STOP = "ACTION_STOP";
    public static final String ACTION_UPDATE = "ACTION_UPDATE";
    public static final String ACTION_FINISHED = "ACTION_FINISHED";
    // 文件的保存路徑
    public static final String DownloadPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/download/";
    public static final int MSG_INIT = 0;
}
