package com.example.newgroup4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;
import com.example.newgroup4.model.Lecturer;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LectAdapter extends RecyclerView.Adapter<LectAdapter.ViewHolder> implements Filterable {

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

    // add 1
    private List<Lecturer> LlistData;

    private List<Lecturer> mListData; // list of lecturer object
    private Context mContext;         // activity context

    public LectAdapter(Context context, List<Lecturer> listData){
        mListData = listData;
        mContext = context;

        // add 2
        LlistData = new ArrayList<>(listData);
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

    // add 3
    @Override
    public Filter getFilter() {
        return exFilter;
    }

    // add 4
    private Filter exFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
           List<Lecturer> filteredList = new ArrayList<>();

           if(constraint == null || constraint.length() == 0){
               filteredList.addAll(LlistData);
           }else{
               String filterPattern = constraint.toString().toLowerCase().trim();
               for(Lecturer lect : LlistData){
                   if(lect.getLectName().toLowerCase().contains(filterPattern)){
                       filteredList.add(lect);
                   }
               }
           }

           FilterResults results = new FilterResults();
           results.values = filteredList;

           return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mListData.clear();
            mListData.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}



