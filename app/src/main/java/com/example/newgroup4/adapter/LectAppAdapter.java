package com.example.newgroup4.adapter;

/*
public class LectAppAdapter extends RecyclerView.Adapter<LectAppAdapter.ViewHolderLect
{
    //test

    class ViewHolderLect extends RecyclerView.ViewHolder{

        public TextView tvStuName;
        public TextView tvStuStatus;
        public TextView tvStuID;

        public ViewHolderLect(View itemView) {
            super(itemView);

            tvStuName = (TextView) itemView.findViewById(R.id.tv); //belum tukar
            tvStuStatus = (TextView) itemView.findViewById(R.id.StuStatus);


        }

    }

    @Override
    public ApptAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.appt_list_item, parent, false);

        ApptAdapter.ViewHolder viewHolder = new ApptAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public <ApptxStuName> void onBindViewHolder(ApptAdapter.ViewHolder holder, int position){

        /*CircularArray<Object> sListData;
        ApptxStuName s = sListData.get(position);
        holder.tvLectName.setText((s.getStuName()));
        holder.tvStatus.setText(s.getStuStatus());
        holder.tvLectID.setText(s.getStuLectID());
    }

}*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;

public class LectAppAdapter extends RecyclerView.Adapter<LectAppAdapter.ViewHolderLect> {

    class ViewHolderLect extends RecyclerView.ViewHolder {

        public TextView tvStuName;
        public TextView tvStuStatus;
        public TextView tvStuID;

        public ViewHolderLect(View itemView) {
            super(itemView);

            tvStuName = (TextView) itemView.findViewById(R.id.tvStuName); //belum tukar
            tvStuStatus = (TextView) itemView.findViewById(R.id.tvApptStat);
        }


    }

    @NonNull
    @Override
    public ViewHolderLect onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.appt_list_item, parent, false);

        ApptAdapter.ViewHolder viewHolder = new ApptAdapter.ViewHolder(view);
       // return viewHolder;
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLect holder, int position) {

        /*CircularArray<Object> sListData;
        ApptxStuName s = sListData.get(position);
        holder.tvLectName.setText((s.getStuName()));
        holder.tvStatus.setText(s.getStuStatus());
        holder.tvLectID.setText(s.getStuLectID());*/

    }

    @Override
    public int getItemCount() {
        return 0;
    }




}
