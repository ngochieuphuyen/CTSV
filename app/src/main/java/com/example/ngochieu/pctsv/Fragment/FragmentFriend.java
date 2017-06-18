package com.example.ngochieu.pctsv.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngochieu.pctsv.Interface.StudentService;
import com.example.ngochieu.pctsv.Model.Student;
import com.example.ngochieu.pctsv.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NgocHieu on 5/20/2017.
 */

public class FragmentFriend extends Fragment {
    Context context;
    TextView txtHoTen;
    TextView txtMSSV;
    TextView txtMale;
    TextView txtBirthday;
    TextView txtPhoneNumber;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        txtHoTen = (TextView) view.findViewById(R.id.txtHoTen);
        txtMSSV = (TextView) view.findViewById(R.id.txtMSSV);
        txtMale = (TextView) view.findViewById(R.id.txtMale);
        txtBirthday = (TextView) view.findViewById(R.id.txtBirthday);
        txtPhoneNumber = (TextView) view.findViewById(R.id.txtPhoneNumber);

        Bundle bundle = this.getArguments();
        int idStudent = bundle.getInt("idStudent");
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/personal/student/"+idStudent).build();
        StudentService service = restAdapter.create(StudentService.class);
        service.getFriend(new Callback<Student>() {
            @Override
            public void success(Student result, Response response) {
                txtHoTen.setText(result.getFullName());
                txtMSSV.setText(result.getStudentNumber());
                txtBirthday.setText(result.getBirthday());
                txtPhoneNumber.setText(result.getPhoneNumber());
                if (result.getMale()){
                    txtMale.setText("Nam");
                } else {
                    txtMale.setText("Nu");
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }
}
