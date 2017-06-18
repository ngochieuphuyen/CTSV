package com.example.ngochieu.pctsv.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ngochieu.pctsv.Activity.LoginActivity;
import com.example.ngochieu.pctsv.Adapter.AdapterListForm;
import com.example.ngochieu.pctsv.Database.MyData;
import com.example.ngochieu.pctsv.Interface.FormService;
import com.example.ngochieu.pctsv.Model.Form;
import com.example.ngochieu.pctsv.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NgocHieu on 4/4/2017.
 */

public class FragmentDangKyBieuMau extends Fragment {
    Context context;
    RecyclerView listForm;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangkybieumau, container, false);

        if (MyData.idAccount < 1){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }

        listForm = (RecyclerView) view.findViewById(R.id.listForm);
        listForm.setLayoutManager(new LinearLayoutManager(context));
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/form/list").build();
        FormService service = restAdapter.create(FormService.class);
        service.getListForm(new Callback<List<Form>>() {
            @Override
            public void success(List<Form> result, Response response) {
                ArrayList<Form> list = new ArrayList<Form>();
                for (int i = 0; i < result.size(); i++) {
                    Form current = new Form();
                    current.setFormName(result.get(i).getFormName());
                    current.setIdForm(result.get(i).getIdForm());
                    list.add(current);
                }
                AdapterListForm adapter = new AdapterListForm(context, list);
                listForm.setAdapter(adapter);
                adapter.setMyOnclick(new AdapterListForm.MyOnclick() {
                    @Override
                    public void onClick(final Form form) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Register form");
                        builder.setMessage("Do you want to register form "+form.getFormName());
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // register form
                                registerForm(form.getIdForm());
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builder.show();
                    }
                });
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void registerForm(int idForm) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/form/register/"+ MyData.idAccount +"/"+idForm).build();
        FormService service = restAdapter.create(FormService.class);
        service.registerForm(new Callback<String>() {
            @Override
            public void success(String result, Response response) {
                if ("true".equals(result)){
                    Toast.makeText(context, "Registered", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
