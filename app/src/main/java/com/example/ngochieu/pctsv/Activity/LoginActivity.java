package com.example.ngochieu.pctsv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ngochieu.pctsv.Database.MyData;
import com.example.ngochieu.pctsv.Database.MyDataDAO;
import com.example.ngochieu.pctsv.Interface.AccountService;
import com.example.ngochieu.pctsv.Model.MyAccount;
import com.example.ngochieu.pctsv.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NgocHieu on 3/27/2017.
 */

public class LoginActivity extends AppCompatActivity {
    EditText editTenDangNhap;
    EditText editmatKhau;
    Button btnDangNhap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTenDangNhap = (EditText) findViewById(R.id.editTenDangNhap);
        editmatKhau = (EditText) findViewById(R.id.editmatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(editTenDangNhap.getText().toString())){
                    Toast.makeText(LoginActivity.this, "input username", Toast.LENGTH_LONG).show();
                } else if ("".equals(editmatKhau.getText().toString())){
                    Toast.makeText(LoginActivity.this, "input password", Toast.LENGTH_LONG).show();
                } else {
                    checkAccount(editTenDangNhap.getText().toString().trim(), editmatKhau.getText().toString().trim());
                }
            }
        });
    }

    private void login(String username, String password) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://192.168.137.1:8080/DATN/rest/login/get/"+username+"/"+password).build();
            AccountService service = restAdapter.create(AccountService.class);
            service.getAccount(new Callback<MyAccount>() {
                @Override
                public void success(MyAccount result, Response response) {
                    MyDataDAO myDataDAO = new MyDataDAO(LoginActivity.this);
                    myDataDAO.addData(result);
                    switchBackToHome();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(LoginActivity.this, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }

    private void switchBackToHome() {
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(myIntent);
    }

    private void checkAccount(final String username, final String password) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/login/check/"+username+"/"+password).build();
        AccountService service = restAdapter.create(AccountService.class);
        service.checkLogin(new Callback<String>() {
            @Override
            public void success(String result, Response response) {
                if ("true".equals(result.toString())) {
                    login(username, password);
                } else {
                    editTenDangNhap.setText("");
                    editmatKhau.setText("");
                    Toast.makeText(LoginActivity.this, "check account", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(LoginActivity.this, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
