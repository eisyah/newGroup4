package com.example.newgroup4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;
import com.example.newgroup4.model.Lecturer;

import java.util.List;

public class LectAdapter extends RecyclerView.Adapter<LectAdapter.ViewHolder> {

    /**
     * Create ViewHolder class to bind list item view
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvLectNameList;

        public ViewHolder(View itemView) {
            super(itemView);

            tvLectNameList = (TextView) itemView.findViewById(R.id.tvLectNameList);
        }
    }

    private List<Lecturer> mListData; // list of lecturer object
    private Context mContext;         // activity context

    public LectAdapter(Context context, List<Lecturer> listData){
        mListData = listData;
        mContext = context;
    }


    private Context getmContext(){return mContext;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate the single item layout
        View view = inflater.inflate(R.layout.lecturer_list_item, parent, false);

        // return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // bind data to the view Holder
        Lecturer m = mListData.get(position);
        holder.tvLectNameList.setText(m.getLectName());
    }

    @Override
    public int getItemCount(){
        return mListData.size();
    }
}



