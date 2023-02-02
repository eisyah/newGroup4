package com.example.newgroup4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    }
}