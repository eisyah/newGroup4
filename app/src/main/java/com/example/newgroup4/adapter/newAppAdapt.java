package com.example.newgroup4.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;
import com.example.newgroup4.model.Appointment;
import com.example.newgroup4.model.newApp;

import java.util.List;

public class newAppAdapt extends RecyclerView.Adapter<newAppAdapt.ViewHolder> {

    // to reference the Activity where this ListView item is on
    private Activity context;

    /*public newAppAdapt(Activity context) {
        super(context, R.layout.lects_app_list_container);
        this.context = context;

        // add names array as our base data array. This is to let the adapter knows
        // the length (no. of records) in our data when inflating
        this.addAll(names);
    }

    public View getView(int position, View view, ViewGroup parent) {

        // inflate one list item using single item layout
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.new_app_container,
                null, true);

        // change the values in the layout
        // this code gets references to objects in the item_listview.xml file
        TextView nameTV = (TextView)rowView.findViewById(R.id.nameTextViewID);
        TextView phoneTV = (TextView)rowView.findViewById(R.id.Time2TextViewID);

        // this ode sets the values of the objects to values from the arrays
        nameTV.setText(names[position]);
        phoneTV.setText(phoneNos[position]);

        // return the single item view object for this item at index position
        return rowView;
    }*/

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvStuName;
        public TextView tvTimeSlot;

        public ViewHolder(View itemView) {
            super(itemView);

            tvStuName = (TextView) itemView.findViewById(R.id.nameTextViewID);
            tvTimeSlot = (TextView) itemView.findViewById(R.id.Time2TextViewID);

        }
    }

    private List<Appointment> mListData;   // list of book objects
    private Context mContext;       // activity context

    public newAppAdapt(Context context, List<Appointment> listData){
        mListData = listData;
        mContext = context;
    }

    private Context getmContext(){return mContext;}


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the single item layout
        View view = inflater.inflate(R.layout.new_app_container, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // bind data to the view holder
        Appointment m = mListData.get(position);
        holder.tvStuName.setText(m.getstuName());
        holder.tvTimeSlot.setText(m.getTime());
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

}
