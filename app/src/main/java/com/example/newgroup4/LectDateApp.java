package com.example.newgroup4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newgroup4.adapter.ArrayAdaptLectsApp;

public class LectDateApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lect_date_app);

        // Initialize ContactListAdapter
        ArrayAdaptLectsApp contactAdapter = new ArrayAdaptLectsApp(this);

        // Link the data adapter with ListView component inside
        // the main activity layout
        ListView contactList = (ListView)findViewById(R.id.LectsAppList);
        contactList.setAdapter(contactAdapter);

        // initialize values in the activity based on the values sent by caller
        String date = getIntent().getStringExtra("DATE");

        // set the values inside the view
        // first, find the view item by id declared in the XML
        TextView dateInfo = (TextView)findViewById(R.id.dateInfo);

        // then, set the value
        dateInfo.setText("Appointments on "+date);

    }
}