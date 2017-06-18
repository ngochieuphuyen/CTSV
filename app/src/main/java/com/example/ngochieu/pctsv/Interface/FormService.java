package com.example.ngochieu.pctsv.Interface;

import com.example.ngochieu.pctsv.Model.Form;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by NgocHieu on 5/16/2017.
 */

public interface FormService {
    @GET("/")
    void getFormRequested(Callback<List<Form>> callback);
    @GET("/")
    void getListForm(Callback<List<Form>> callback);
    @GET("/")
    void registerForm(Callback<String> callback);
}
