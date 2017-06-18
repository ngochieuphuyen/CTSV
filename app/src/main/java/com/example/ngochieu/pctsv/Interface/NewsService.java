package com.example.ngochieu.pctsv.Interface;

import com.example.ngochieu.pctsv.Model.News;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by NgocHieu on 5/17/2017.
 */

public interface NewsService {
    @GET("/")
    void getListNewsHot(Callback<List<News>> callback);
    @GET("/")
    void getNewsDetail(Callback<News> callback);
}
