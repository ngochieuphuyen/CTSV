package com.example.ngochieu.pctsv.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ngochieu.pctsv.Adapter.AdapterListNews;
import com.example.ngochieu.pctsv.Interface.NewsService;
import com.example.ngochieu.pctsv.Model.News;
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

public class FragmentTrangChu extends Fragment {
    Context context;
    RecyclerView listNewsHot;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        listNewsHot = (RecyclerView) view.findViewById(R.id.listNewsHot);
        listNewsHot.setLayoutManager(new LinearLayoutManager(context));

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.137.1:8080/DATN/rest/news/list").build();
        NewsService service = restAdapter.create(NewsService.class);
        service.getListNewsHot(new Callback<List<News>>() {
            @Override
            public void success(List<News> result, Response response) {
                ArrayList<News> list = new ArrayList<News>();
                for (int i = 0; i < result.size(); i++) {
                    News current = new News();
                    current.setTitle(result.get(i).getTitle());
                    current.setPostTime(result.get(i).getPostTime());
                    current.setViews(result.get(i).getViews());
                    current.setIdNews(result.get(i).getIdNews());
                    list.add(current);
                }
                AdapterListNews adapter = new AdapterListNews(context, list);
                adapter.setMyOnClick(new AdapterListNews.MyOnClick() {
                    @Override
                    public void onClick(News news) {
                        callNewsDetail(news.getIdNews());
                    }
                });
                listNewsHot.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void callNewsDetail(int idNews) {
        FragmentChiTietTinTuc fragment = new FragmentChiTietTinTuc();
        Bundle bundle = new Bundle();
        bundle.putInt("idNews", idNews);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.Frame_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
