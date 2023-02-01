package com.example.newgroup4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;
import com.example.newgroup4.model.ApptxLectName;

import java.util.List;

public class ApptAdapter extends RecyclerView.Adapter<ApptAdapter.ViewHolder>{


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvLectName;
        public TextView tvStatus;
        public TextView tvLectID;

        public ViewHolder(View itemView) {
            super(itemView);

            tvLectName = (TextView) itemView.findViewById(R.id.tvLectName);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
            tvLectID = (TextView) itemView.findViewById(R.id.tvLectID);

        }

    }

    class ViewHolderLect extends RecyclerView.ViewHolder{

        public TextView tvStuName;
        public TextView tvStuStatus;
        public TextView tvStuID;

        public ViewHolderLect(View itemView) {
            super(itemView);

            tvStuName = (TextView) itemView.findViewById(R.id.tvStuName); //belum tukar
            tvStuStatus = (TextView) itemView.findViewById(R.id.tvStuStatus);
            tvStuID = (TextView) itemView.findViewById(R.id.tvStuID);

        }

    }


    private List<ApptxLectName> mListData;
    private Context mContext;

    public ApptAdapter(Context context, List<ApptxLectName> listData){
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

        ApptxLectName m = mListData.get(position);
        holder.tvLectName.setText((m.getLectName()));
        holder.tvStatus.setText(m.getStatus());
        holder.tvLectID.setText(m.getLectID());
    }

    @Override
    public int getItemCount(){
        return mListData == null ? 0 : mListData.size();
    }
}
