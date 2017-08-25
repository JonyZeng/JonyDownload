package com.example.jonyz.jonydownload.Bean;

/**
 * 下载线程实体类
 * Created by JonyZ on 2017/8/23.
 */

public class UrlBean {

    public String url;
    public int    id;
    public int start;
    public int end;
    public int finished;

    public UrlBean(String url, int id, int start, int end, int finished) {
        this.url = url;
        this.id = id;
        this.start = start;
        this.end = end;
        this.finished = finished;
    }

    public UrlBean() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }
}
