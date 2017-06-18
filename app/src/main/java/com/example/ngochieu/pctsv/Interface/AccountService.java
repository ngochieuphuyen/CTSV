package com.example.ngochieu.pctsv.Interface;

import android.accounts.Account;

import com.example.ngochieu.pctsv.Model.MyAccount;

import retrofit.Callback;

import retrofit.http.GET;

/**
 * Created by NgocHieu on 5/16/2017.
 */

public interface AccountService {
    @GET("/")
    void getAccount(Callback<MyAccount> callback);
    @GET("/")
    void changePassword(Callback<String> callback);
    @GET("/")
    void updateAccount(Callback<String> callback);
    @GET("/")
    void checkLogin(Callback<String> callback);
    @GET("/")
    void logout(Callback<String> callback);
}
