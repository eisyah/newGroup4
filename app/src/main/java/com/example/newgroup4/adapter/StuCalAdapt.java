package com.example.newgroup4.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;

import java.util.ArrayList;

public class StuCalAdapt extends RecyclerView.Adapter<CalViewHolder>{

    private final ArrayList<String> daysOfMonthS;
    private final CalendarAdapt.OnitemListener onitemListener;

    public StuCalAdapt(ArrayList<String> daysOfMonth, CalendarAdapt.OnitemListener onitemListener) {
        this.daysOfMonthS = daysOfMonth;
        this.onitemListener = onitemListener;
    }

    @NonNull
    @Override
    public CalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.date_box, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        layoutParams.height = (int) (parent.getHeight()*0.096666);

        view.setBackgroundColor(Color.LTGRAY);

        return new CalViewHolder(view, onitemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalViewHolder holder, int position) {

        holder.dayOfMonth.setText(daysOfMonthS.get(position));
        holder.appCount.setText("");
        //holder.appCount.setText(Integer.toString(getItemCount()));
        if(position==12) {
            holder.appCount.setText(daysOfMonthS.get(position)+" app.");
            holder.itemView.setBackgroundColor(Color.BLUE);}
        // else
        //    holder.appCount.setText("");

    }

    @Override
    public int getItemCount() {
        return daysOfMonthS.size();
    }

    public interface OnitemListener
    {
        void onItemClick(int position, String dayText);
    }

}
