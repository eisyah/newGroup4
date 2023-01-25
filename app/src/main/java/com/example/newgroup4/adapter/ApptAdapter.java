package com.example.newgroup4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;
import com.example.newgroup4.model.Appointment;


import java.util.List;

public class ApptAdapter extends RecyclerView.Adapter<ApptAdapter.ViewHolder>{

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvLectName;
        public TextView tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            tvLectName = (TextView) itemView.findViewById(R.id.tvLectName);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);

        }

    }

    private List<Appointment> mListData;
    private Context mContext;

    public ApptAdapter(Context context, List<Appointment> listData){
        mListData = listData;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.appt_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Appointment m = mListData.get(position);
        holder.tvLectName.setText(m.getLectID());
        holder.tvStatus.setText(m.getStatus());
    }

    @Override
    public int getItemCount(){
        return mListData.size();
    }
}
