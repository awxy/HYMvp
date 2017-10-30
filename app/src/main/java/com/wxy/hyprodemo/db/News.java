package com.wxy.hyprodemo.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/10/30.
 */

public class News extends DataSupport{
    private String title;

    private String body;

    public News( String title,String body) {
        this.title = title;

        this.body = body;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
