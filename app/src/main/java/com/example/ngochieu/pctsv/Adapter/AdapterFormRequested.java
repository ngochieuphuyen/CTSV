package com.example.ngochieu.pctsv.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ngochieu.pctsv.Model.Form;
import com.example.ngochieu.pctsv.R;

import java.util.List;

/**
 * Created by NgocHieu on 5/16/2017.
 */

public class AdapterFormRequested extends RecyclerView.Adapter<AdapterFormRequested.HolderFormRequested> {
    Context context;
    List<Form> data;

    public AdapterFormRequested(Context context, List<Form> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public HolderFormRequested onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yeucaugiayto, parent, false);
        return new HolderFormRequested(view);
    }

    @Override
    public void onBindViewHolder(HolderFormRequested holder, int position) {
        Form form = data.get(position);
        holder.itemForm.setText(form.getFormName());
        holder.itemTimeOfRegistration.setText(form.getTimeOfRegistration());
        holder.itemReceivingTime.setText(form.getReceivingTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class HolderFormRequested extends RecyclerView.ViewHolder{
        TextView itemForm;
        TextView itemTimeOfRegistration;
        TextView itemReceivingTime;
        public HolderFormRequested(View itemView) {
            super(itemView);
            itemForm = (TextView) itemView.findViewById(R.id.itemForm);
            itemTimeOfRegistration = (TextView) itemView.findViewById(R.id.itemTimeOfRegistration);
            itemReceivingTime = (TextView) itemView.findViewById(R.id.itemReceivingTime);
        }
    }
}
