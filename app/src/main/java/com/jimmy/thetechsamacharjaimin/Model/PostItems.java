package com.jimmy.thetechsamacharjaimin.Model;

import org.json.JSONObject;

public class PostItems {

    private String title;
    private String date;
    private String auther;
    private String content;
    private String url;


    public PostItems(String title, String auther, String date, String url, String content) {
        this.title = title;
        this.auther = auther;
        this.content = content;
        this.date = date;
        this.url = url;

    }

    public String getTitle() {
        return title;
    }

    public String getAuther() {
        return auther;
    }


    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }



    }





