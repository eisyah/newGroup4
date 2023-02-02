package com.example.newgroup4.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.newgroup4.model.Lecturer;

import java.util.List;

public class LecturerSpinnerAdapter extends ArrayAdapter<Lecturer> {

    private Context context;
    // Your custom values for the spinner (User)
    private List<Lecturer> Lecturers;

    public LecturerSpinnerAdapter(Context context, int textViewResourceId,
                                  List<Lecturer> Lecturers) {
        super(context, textViewResourceId, Lecturers);
        this.context = context;
        this.Lecturers = Lecturers;
    }

    @Override
    public int getCount(){
        return Lecturers.size();
    }

    @Override
    public Lecturer getItem(int position){
        return Lecturers.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(Lecturers.get(position).getLectName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }


}
