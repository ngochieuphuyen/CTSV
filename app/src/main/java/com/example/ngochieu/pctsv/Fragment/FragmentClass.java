package com.example.ngochieu.pctsv.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ngochieu.pctsv.Activity.LoginActivity;
import com.example.ngochieu.pctsv.Adapter.AdapterListClass;
import com.example.ngochieu.pctsv.Database.MyData;
import com.example.ngochieu.pctsv.Interface.StudentService;
import com.example.ngochieu.pctsv.Model.Student;
import com.example.ngochieu.pctsv.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NgocHieu on 5/20/2017.
 */

public class FragmentClass extends Fragment {
    Context context;
    RecyclerView listStudents;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        listStudents = (RecyclerView) view.findViewById(R.id.listStudents);
        listStudents.setLayoutManager(new LinearLayoutManager(context));
        if (MyData.idAccount < 1){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/personal/class/"+MyData.idAccount).build();
        StudentService service = restAdapter.create(StudentService.class);
        service.getClass(new Callback<List<Student>>() {
            @Override
            public void success(List<Student> result, Response response) {
                ArrayList<Student> list = new ArrayList<Student>();
                for (int i = 0; i < result.size(); i++) {
                    Student current = new Student();
                    current.setFullName(result.get(i).getFullName());
                    current.setStudentNumber(result.get(i).getStudentNumber());
                    current.setPhoneNumber(result.get(i).getPhoneNumber());
                    current.setIdStudent(result.get(i).getIdStudent());
                    list.add(current);
                }
                AdapterListClass adapter = new AdapterListClass(context, list);
                listStudents.setAdapter(adapter);
                adapter.setMyOnClick(new AdapterListClass.MyOnClick() {
                    @Override
                    public void onClick(Student student) {
                        detailFriend(student.getIdStudent());
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

    private void detailFriend(int idStudent) {
       FragmentFriend fragment = new FragmentFriend();
        Bundle bundle = new Bundle();
        bundle.putInt("idStudent", idStudent);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.Frame_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
