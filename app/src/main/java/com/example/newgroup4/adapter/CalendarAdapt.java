package com.example.newgroup4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;

import java.util.ArrayList;

public class CalendarAdapt extends RecyclerView.Adapter<CalViewHolder>
{
    private final ArrayList<String> daysOfMonth;
    private final OnitemListener onitemListener;

    public CalendarAdapt(ArrayList<String> daysOfMonth, OnitemListener onitemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onitemListener = onitemListener;
    }


    @NonNull
    @Override
    public CalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.date_box, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight()*0.166666666);
        return new CalViewHolder(view, onitemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnitemListener
    {
        void onItemClick(int position, String dayText);

    }

}
