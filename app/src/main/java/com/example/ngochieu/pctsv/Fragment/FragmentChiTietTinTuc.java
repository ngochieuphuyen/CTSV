package com.example.ngochieu.pctsv.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngochieu.pctsv.Interface.NewsService;
import com.example.ngochieu.pctsv.Model.News;
import com.example.ngochieu.pctsv.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NgocHieu on 5/17/2017.
 */

public class FragmentChiTietTinTuc extends Fragment {
    Context context;

    TextView txtNewsTitle;
    TextView txtDescriptive;
    TextView txtContent;
    TextView txtPostTime;
    TextView txtViews;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitiettintuc,container, false);
        Bundle bundle = this.getArguments();
        int idNews = bundle.getInt("idNews");
        txtNewsTitle = (TextView) view.findViewById(R.id.txtNewsTitle);
        txtDescriptive = (TextView) view.findViewById(R.id.txtDescriptive);
        txtContent = (TextView) view.findViewById(R.id.txtContent);
        txtPostTime = (TextView) view.findViewById(R.id.txtPostTime);
        txtViews = (TextView) view.findViewById(R.id.txtViews);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/news/detail/"+idNews).build();
        NewsService service = restAdapter.create(NewsService.class);
        service.getNewsDetail(new Callback<News>() {
            @Override
            public void success(News result, Response response) {
                txtNewsTitle.setText(result.getTitle());
                txtDescriptive.setText(result.getDescriptiveNews());
                txtContent.setText(result.getDetail());
                txtPostTime.setText(result.getPostTime());
                txtViews.setText(String.valueOf(result.getViews()));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
