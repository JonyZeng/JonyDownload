package com.example.jonyz.jonydownload.Bean;

import java.io.Serializable;

/**
 * 下载文件的实体类
 * Created by JonyZ on 2017/8/23.
 */

public class FileBean implements Serializable {
    public String fileName;
    public Integer fileSize;
    public Integer filePause;//下载暂停位置
    public Integer DownSize; //finished
    public Integer id;
    public String Url;


    public FileBean() {
    }

    /**
     *
     * @param fileName 文件名
     * @param fileSize 文件大小
     * @param downSize 文件下载
     * @param id       文件ID
     * @param url       文件下载地址
     */
    public FileBean(Integer id,String fileName, Integer fileSize, Integer downSize,  String url) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        DownSize = downSize;
        this.id = id;
        Url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        fileSize = fileSize;
    }

    public Integer getFilePause() {
        return filePause;
    }

    public void setFilePause(String filePause) {
        filePause = filePause;
    }

    public Integer getDownSize() {
        return DownSize;
    }

    public void setDownSize(Integer downSize) {
        DownSize = downSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
