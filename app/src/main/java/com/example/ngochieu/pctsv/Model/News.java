package com.example.ngochieu.pctsv.Model;

/**
 * Created by NgocHieu on 5/17/2017.
 */

public class News {
    private int idNews;
    private String title;
    private String detail;
    private String postTime;
    private int views;
    private String descriptiveNews;

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDescriptiveNews() {
        return descriptiveNews;
    }

    public void setDescriptiveNews(String descriptiveNews) {
        this.descriptiveNews = descriptiveNews;
    }

    @Override
    public String toString() {
        return "News{" +
                "idNews=" + idNews +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", postTime='" + postTime + '\'' +
                ", views=" + views +
                ", descriptiveNews='" + descriptiveNews + '\'' +
                '}';
    }
}
