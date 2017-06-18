package com.example.ngochieu.pctsv.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngochieu.pctsv.Database.MyData;
import com.example.ngochieu.pctsv.Database.MyDataDAO;
import com.example.ngochieu.pctsv.Fragment.FragmentCaNhan;
import com.example.ngochieu.pctsv.Fragment.FragmentClass;
import com.example.ngochieu.pctsv.Fragment.FragmentDangKyBieuMau;
import com.example.ngochieu.pctsv.Fragment.FragmentDanhGiaRenLuyen;
import com.example.ngochieu.pctsv.Fragment.FragmentTrangChu;
import com.example.ngochieu.pctsv.Interface.AccountService;
import com.example.ngochieu.pctsv.Model.MyAccount;
import com.example.ngochieu.pctsv.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView textUsername;
    TextView textEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (!connectInternet()) {
//            Toast.makeText(this, "not access internet", Toast.LENGTH_SHORT).show();
//            return;
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        textUsername = (TextView) headerView.findViewById(R.id.textUsername);
        textEmail = (TextView) headerView.findViewById(R.id.textEmail);
        MyDataDAO myDataDAO = new MyDataDAO(this);
        MyAccount myAccount = myDataDAO.getAccount();
        MyData.idAccount = myAccount.getIdAccount();
        MyData.username = myAccount.getUsername();
        MyData.email = myAccount.getEmail();
        MyData.isLogin = myAccount.getIsLogin();

        if (MyData.idAccount > 0){
            textUsername.setText(MyData.username);
            textEmail.setText(MyData.email);
        } else {
            textUsername.setText("username");
            textEmail.setText("email@email");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = new FragmentTrangChu();
        transaction.replace(R.id.Frame_content, fragment);
        transaction.commit();
    }
    public boolean connectInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment;

        switch (item.getItemId()){
            case R.id.nav_trangChu:
                fragment = new FragmentTrangChu();
                transaction.replace(R.id.Frame_content, fragment);
                break;
            case R.id.nav_dangKyBieuMau:
                fragment = new FragmentDangKyBieuMau();
                transaction.replace(R.id.Frame_content, fragment);
                break;
            case R.id.nav_lop:
                fragment = new FragmentClass();
                transaction.replace(R.id.Frame_content, fragment);
                break;
            case R.id.nav_caNhan:
                fragment = new FragmentCaNhan();
                transaction.replace(R.id.Frame_content, fragment);
                break;
            case R.id.nav_exit:
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://192.168.137.1:8080/DATN/rest/login/logout/"+MyData.idAccount).build();
                AccountService service = restAdapter.create(AccountService.class);
                service.logout(new Callback<String>() {
                    @Override
                    public void success(String result, Response response) {
                        MyDataDAO myDataDAO = new MyDataDAO(MainActivity.this);
                        myDataDAO.deleteData(MyData.idAccount);
                        MyData.idAccount = 0;
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Toast.makeText(MainActivity.this, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }

        transaction.addToBackStack(null);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
