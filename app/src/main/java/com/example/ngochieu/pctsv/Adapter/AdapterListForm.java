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
 * Created by NgocHieu on 5/17/2017.
 */

public class AdapterListForm extends RecyclerView.Adapter<AdapterListForm.HolderForm> {
    Context context;
    List<Form> data;
    MyOnclick myOnclick;

    public void setMyOnclick(MyOnclick myOnclick) {
        this.myOnclick = myOnclick;
    }

    public AdapterListForm(Context context, List<Form> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public HolderForm onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachbieumau, parent, false);
        return new HolderForm(view);
    }

    @Override
    public void onBindViewHolder(HolderForm holder, int position) {
        Form form = data.get(position);
        holder.itemForm.setText(form.getFormName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class HolderForm extends RecyclerView.ViewHolder{
        TextView itemForm;
        public HolderForm(View itemView) {
            super(itemView);
            itemForm = (TextView) itemView.findViewById(R.id.itemForm);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnclick.onClick(data.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface MyOnclick{
        void onClick(Form form);
    }
}
