package com.example.newgroup4.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;
import com.example.newgroup4.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapt extends RecyclerView.Adapter<CalViewHolder>
{
    private final ArrayList<String> daysOfMonth;
    private final OnitemListener onitemListener;
    private final List<Appointment> appointments;
    private final String monthOfYear;

    public CalendarAdapt(ArrayList<String> daysOfMonth, OnitemListener onitemListener, List<Appointment> appointmentList, String month) {
        this.daysOfMonth = daysOfMonth;
        this.onitemListener = onitemListener;
        this.appointments = appointmentList;
        this.monthOfYear = month;
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
    public int getItemCount() {
        return daysOfMonth.size();
   }

    public interface OnitemListener
    {
        void onItemClick(int position, String dayText);
    }




    //change each date to show colour change and num of appointment
    @Override
    public void onBindViewHolder(@NonNull CalViewHolder holder, int position) {

        holder.dayOfMonth.setText(daysOfMonth.get(position));
        holder.appCount.setText("");


        if (daysOfMonth.get(position).equals("1")) {
            //make the background red
            holder.itemView.setBackgroundColor(Color.RED);
        }

        //for every appointment in the list check if there is appointment in this month and then check if there is appointment for each da


        for (Appointment a : appointments) {
            //parse the date to get the day
            String[] date = a.getDate().split("-");
            String day = date[2];
            String month = date[1];

            if (day.equals(daysOfMonth.get(position))) {
                if (month.equals(monthOfYear))
                    holder.appCount.setText("1");
                holder.itemView.setBackgroundColor(Color.GREEN);
            }
        }


        //holder.appCount.setText(Integer.toString(getItemCount()));
        // else
        // holder.appCount.setText("");


    }
}