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
import com.example.newgroup4.model.StudSideApptxLectName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudSideApptAdapter extends RecyclerView.Adapter<StudSideApptAdapter.ViewHolder> implements Filterable {


    static class ViewHolder extends RecyclerView.ViewHolder{

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

    private List<StudSideApptxLectName> aListData;

    private List<StudSideApptxLectName> mListData;
    private Context mContext;

    public StudSideApptAdapter(Context context, List<StudSideApptxLectName> listData){
        mListData = listData;
        mContext = context;

        aListData = new ArrayList<>(mListData);
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

        StudSideApptxLectName m = mListData.get(position);
        holder.tvLectName.setText((m.getLectName()));
        holder.tvStatus.setText(m.getStatus());
        holder.tvLectID.setText(m.getLectID());
    }

    @Override
    public int getItemCount(){
        return mListData == null ? 0 : mListData.size();
    }

    // add 3
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<StudSideApptxLectName> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(aListData);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(StudSideApptxLectName item: aListData){
                    if(item.getStatus().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
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
            mListData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
