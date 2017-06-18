package com.example.ngochieu.pctsv.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.ngochieu.pctsv.R;

/**
 * Created by NgocHieu on 4/4/2017.
 */

public class FragmentDanhGiaRenLuyen extends Fragment{
    Context context;
    TabHost tab;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void loadTabs()
    {
        //Lấy Tabhost id ra trước (cái này của built - in android
        //gọi lệnh setup
        tab.setup();
        TabHost.TabSpec spec;
        //Tạo tab1
        spec=tab.newTabSpec("t1");
        spec.setContent(R.id.tabThongBao);
        spec.setIndicator("Thong bao");
        tab.addTab(spec);
        //Tạo tab2
        spec=tab.newTabSpec("t2");
        spec.setContent(R.id.tabDanhGia);
        spec.setIndicator("Danh Gia Diem Ren Luyen Ca Nhan");
        tab.addTab(spec);

        //Tạo tab3
        spec=tab.newTabSpec("t3");
        spec.setContent(R.id.tabBangtongKet);
        spec.setIndicator("Bang Tong Ket");
        tab.addTab(spec);
        //Thiết lập tab mặc định được chọn ban đầu là tab 0
        tab.setCurrentTab(0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhgiarenluyen, container, false);
        tab = (TabHost) view.findViewById(android.R.id.tabhost);
        loadTabs();
        return view;
    }
}
