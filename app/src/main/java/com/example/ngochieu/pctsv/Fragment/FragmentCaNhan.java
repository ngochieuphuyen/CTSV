package com.example.ngochieu.pctsv.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngochieu.pctsv.Activity.LoginActivity;
import com.example.ngochieu.pctsv.Adapter.AdapterFormRequested;
import com.example.ngochieu.pctsv.Database.MyData;
import com.example.ngochieu.pctsv.Interface.AccountService;
import com.example.ngochieu.pctsv.Interface.FormService;
import com.example.ngochieu.pctsv.Interface.StudentService;
import com.example.ngochieu.pctsv.Model.Form;
import com.example.ngochieu.pctsv.Model.MyAccount;
import com.example.ngochieu.pctsv.Model.Student;
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

public class FragmentCaNhan extends Fragment {
    Context context;
    TabHost tab;

    TextView txtUsername;
    EditText editEmail;
    Button btnSave;
    EditText editPasswordOld;
    EditText editPasswordNew;
    EditText editPasswordConfirm;
    Button btnChange;
    MyAccount myAccount;

    TextView txtHoTen;
    TextView txtMSSV;
    TextView txtBirthday;
    TextView txtFaculty;
    TextView txtCourse;
    TextView txtClass;
    TextView txtPhoneNumber;
    TextView txtHomePhone;
    TextView txtMale;

    RecyclerView listFormQuested;

    Student student = new Student();
    public void loadTabs()
    {
        //Lấy Tabhost id ra trước (cái này của built - in android
        //gọi lệnh setup
        tab.setup();
        TabHost.TabSpec spec;
        //Tạo tab1
        spec=tab.newTabSpec("t1");
        spec.setContent(R.id.tabTaiKhoan);
        spec.setIndicator("Thong tin tai khoan");
        tab.addTab(spec);
        //Tạo tab2
        spec=tab.newTabSpec("t2");
        spec.setContent(R.id.tabTTCaNhan);
        spec.setIndicator("Thong tin ca nhan");
        tab.addTab(spec);

        //Tạo tab3
        spec=tab.newTabSpec("t3");
        spec.setContent(R.id.tabGiayTo);
        spec.setIndicator("Yeu cau giay to");
        tab.addTab(spec);
        //Thiết lập tab mặc định được chọn ban đầu là tab 0
        tab.setCurrentTab(0);
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tab.getCurrentTab()){
                    case 1:
                        loadPersonal();
                        break;
                    case 2:    
                        loadFormRequested();
                        break;
                    default:
                        loadAccount();
                        break;
                }
            }
        });
    }

    private void loadAccount() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/personal/account/"+ MyData.idAccount).build();
        AccountService service = restAdapter.create(AccountService.class);
        service.getAccount(new Callback<MyAccount>() {
            @Override
            public void success(final MyAccount result, Response response) {
                myAccount.setUsername(result.getUsername());
                myAccount.setEmail(result.getEmail());
                myAccount.setPassword(result.getPassword());
                myAccount.setIdAccount(result.getIdAccount());
                txtUsername.setText(myAccount.getUsername());
                editEmail.setText(myAccount.getEmail());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void changePassword(String newPassword) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/personal/changepass/"+MyData.idAccount+"/"+newPassword).build();
        AccountService service = restAdapter.create(AccountService.class);
        service.changePassword(new Callback<String>() {
            @Override
            public void success(String result, Response response) {
                if ("true".equals(result)){
                    editPasswordOld.setText("");
                    editPasswordNew.setText("");
                    editPasswordConfirm.setText("");
                    Toast.makeText(context, "changed password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "failure password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateAccount(String email) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/personal/update/"+MyData.idAccount+"/"+email).build();
        AccountService service = restAdapter.create(AccountService.class);
        service.updateAccount(new Callback<String>() {
            @Override
            public void success(String result, Response response) {
                if ("true".equals(result)){
                    Toast.makeText(context, "updated account", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "failure update", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadPersonal() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/personal/infor/"+MyData.idAccount).build();
        StudentService service = restAdapter.create(StudentService.class);
        service.getStudent(new Callback<Student>() {
            @Override
            public void success(Student result, Response response) {
                txtHoTen.setText(result.getFullName());
                txtMSSV.setText(result.getStudentNumber());
                txtBirthday.setText(result.getBirthday());
                if (result.getMale()){
                    txtMale.setText("Nam");
                } else {
                    txtMale.setText("Nu");
                }
                txtPhoneNumber.setText(result.getPhoneNumber());
                txtHomePhone.setText(result.getHomePhone());
                txtClass.setText(result.getClassName());
                txtFaculty.setText(result.getFacultyName());
                txtCourse.setText(result.getCourseName());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void loadFormRequested() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/form/requested/"+MyData.idAccount).build();
        FormService service = restAdapter.create(FormService.class);
        service.getFormRequested(new Callback<List<Form>>() {
            @Override
            public void success(List<Form> result, Response response) {
                ArrayList<Form> listForm = new ArrayList<Form>();
                for (int i = 0; i < result.size(); i++) {
                    Form current = new Form();
                    current.setFormName(result.get(i).getFormName());
                    current.setTimeOfRegistration(result.get(i).getTimeOfRegistration());
                    current.setReceivingTime(result.get(i).getReceivingTime());
                    listForm.add(current);
                }
                AdapterFormRequested adapter = new AdapterFormRequested(context, listForm);
                listFormQuested.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_canhan,container, false);
        tab = (TabHost) view.findViewById(android.R.id.tabhost);

        if (MyData.idAccount < 1){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }

        myAccount = new MyAccount();
        txtUsername = (TextView) view.findViewById(R.id.txtUsername);
        editEmail = (EditText) view.findViewById(R.id.editEmail);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        editPasswordOld = (EditText) view.findViewById(R.id.editPasswordOld);
        editPasswordNew = (EditText) view.findViewById(R.id.editPasswordNew);
        editPasswordConfirm = (EditText) view.findViewById(R.id.editPasswordConfirm);
        btnChange = (Button) view.findViewById(R.id.btnChange);


        txtHoTen = (TextView) view.findViewById(R.id.txtHoTen);
        txtMSSV = (TextView) view.findViewById(R.id.txtMSSV);
        txtBirthday = (TextView) view.findViewById(R.id.txtBirthday);
        txtFaculty = (TextView) view.findViewById(R.id.txtFaculty);
        txtCourse = (TextView) view.findViewById(R.id.txtCourse);
        txtClass = (TextView) view.findViewById(R.id.txtClass);
        txtPhoneNumber = (TextView) view.findViewById(R.id.txtPhoneNumber);
        txtHomePhone = (TextView) view.findViewById(R.id.txtHomePhone);
        txtMale = (TextView) view.findViewById(R.id.txtMale);

        listFormQuested = (RecyclerView) view.findViewById(R.id.listFormQuested);
        listFormQuested.setLayoutManager(new LinearLayoutManager(context));

        loadAccount();
        loadTabs();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update email of account
                updateAccount(editEmail.getText().toString());
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change password
                if(editPasswordOld.getText().toString().equals(myAccount.getPassword())){
                    // same pass
                    if (editPasswordNew.getText().toString().equals(editPasswordConfirm.getText().toString())){
                        changePassword(editPasswordNew.getText().toString());
                    } else {
                        editPasswordNew.setText("");
                        editPasswordConfirm.setText("");
                    }
                } else {
                    // different pass
                    editPasswordOld.setText("");
                }
            }
        });


        return view;
    }
}
