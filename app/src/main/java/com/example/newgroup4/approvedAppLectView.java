package com.example.newgroup4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.newgroup4.adapter.appListAdapterLect;

public class approvedAppLectView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_app_lect_view);

        // Initialize ContactListAdapter
        appListAdapterLect contactAdapter = new appListAdapterLect(this);

        // Link the data adapter with ListView component inside
        // the main activity layout
        ListView AppList = (ListView)findViewById(R.id.LectsAppList);
        AppList.setAdapter(contactAdapter);

        AppList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), DetailsApprovedLect.class);
                // get the data from the array based on the item position/index
                /*intent.putExtra("NAME", contactAdapter.getNames()[pos]);
                intent.putExtra("PHONE_NO", contactAdapter.getPhoneNos()[pos]);*/
                // start the details activity
                startActivity(intent);

            }
        });

    }
}