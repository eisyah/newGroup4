package com.example.newgroup4.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.R;

public class CalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfMonth;
    public final TextView appCount;
    private final CalendarAdapt.OnitemListener onitemListener;

    public CalViewHolder(@NonNull View itemView, CalendarAdapt.OnitemListener onitemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        appCount = itemView.findViewById(R.id.eventCdisplay);

        this.onitemListener = onitemListener;
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onitemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
