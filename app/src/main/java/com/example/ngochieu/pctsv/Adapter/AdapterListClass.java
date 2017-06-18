package com.example.ngochieu.pctsv.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ngochieu.pctsv.Model.Student;
import com.example.ngochieu.pctsv.R;

import java.util.List;

/**
 * Created by NgocHieu on 5/20/2017.
 */

public class AdapterListClass extends RecyclerView.Adapter<AdapterListClass.HolderStudent> {
    Context context;
    List<Student> data;
    MyOnClick myOnClick;

    public void setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
    }

    public AdapterListClass(Context context, List<Student> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public HolderStudent onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachlop, parent, false);
        return new HolderStudent(view);
    }

    @Override
    public void onBindViewHolder(HolderStudent holder, int position) {
        Student student = data.get(position);
        holder.itemName.setText(student.getFullName());
        holder.itemMSSV.setText(student.getStudentNumber());
        holder.itemPhone.setText(student.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class HolderStudent extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView itemMSSV;
        TextView itemPhone;
        public HolderStudent(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemMSSV = (TextView) itemView.findViewById(R.id.itemMSSV);
            itemPhone = (TextView) itemView.findViewById(R.id.itemPhone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnClick.onClick(data.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface MyOnClick{
        void onClick(Student student);
    }
}
