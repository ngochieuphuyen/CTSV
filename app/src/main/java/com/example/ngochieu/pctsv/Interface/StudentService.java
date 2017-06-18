package com.example.ngochieu.pctsv.Interface;

import com.example.ngochieu.pctsv.Model.Student;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by NgocHieu on 5/16/2017.
 */

public interface StudentService {
    @GET("/")
    void getStudent(Callback<Student> callback);
    @GET("/")
    void getClass(Callback<List<Student>> callback);
    @GET("/")
    void getFriend(Callback<Student> callback);
}
