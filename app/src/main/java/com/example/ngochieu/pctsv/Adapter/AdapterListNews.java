package com.example.ngochieu.pctsv.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ngochieu.pctsv.Model.News;
import com.example.ngochieu.pctsv.R;

import java.util.List;

/**
 * Created by NgocHieu on 5/17/2017.
 */

public class AdapterListNews extends RecyclerView.Adapter<AdapterListNews.HolderNews> {
    Context context;
    List<News> data;
    MyOnClick myOnClick;

    public AdapterListNews(Context context, List<News> data) {
        this.context = context;
        this.data = data;
    }

    public void setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
    }

    @Override
    public HolderNews onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trangchu, parent, false);
        return new HolderNews(view);
    }

    @Override
    public void onBindViewHolder(HolderNews holder, int position) {
        News news = data.get(position);
        holder.itemNewsName.setText(news.getTitle());
        holder.itemPostTime.setText(news.getPostTime());
        holder.itemViews.setText(String.valueOf(news.getViews()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class HolderNews extends RecyclerView.ViewHolder{
        TextView itemNewsName;
        TextView itemPostTime;
        TextView itemViews;
        public HolderNews(View itemView) {
            super(itemView);
            itemNewsName = (TextView) itemView.findViewById(R.id.itemNewsName);
            itemPostTime = (TextView) itemView.findViewById(R.id.itemPostTime);
            itemViews = (TextView) itemView.findViewById(R.id.itemViews);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnClick.onClick(data.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface MyOnClick{
        void onClick(News news);
    }
}
