package com.example.newgroup4.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.newgroup4.R;

public class ArrayAdaptLectsApp extends ArrayAdapter {

    // contacts data
    private String names[] = {"Kristofer", "Vernita", "Rubie",
            "Bobbye", "Harley", "Christia", "Jacqualine"};

    private String phoneNos[] = {"(959) 283-2626", "(214) 334-2564",
            "(917) 983-1192", "(958) 826-5902", "(548) 554-6564",
            "(941) 859-1470", "(925) 448-3657}"};


    // to reference the Activity where this ListView item is on
    private Activity context;

    public ArrayAdaptLectsApp(Activity context) {
        super(context, R.layout.lects_app_list_container);
        this.context = context;

        // add names array as our base data array. This is to let the adapter knows
        // the length (no. of records) in our data when inflating
        this.addAll(names);
    }

    public View getView(int position, View view, ViewGroup parent) {

        // inflate one list item using single item layout
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.lects_app_list_container,
                null, true);

        // change the values in the layout
        // this code gets references to objects in the item_listview.xml file
        TextView nameTV = (TextView)rowView.findViewById(R.id.nameTextViewID);
        TextView phoneTV = (TextView)rowView.findViewById(R.id.TimeTextViewID);

        // this ode sets the values of the objects to values from the arrays
        nameTV.setText(names[position]);
        phoneTV.setText(phoneNos[position]);

        // return the single item view object for this item at index position
        return rowView;
    }

}
