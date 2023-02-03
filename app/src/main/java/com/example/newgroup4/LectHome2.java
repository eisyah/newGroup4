package com.example.newgroup4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newgroup4.adapter.StudSideApptAdapter;
import com.example.newgroup4.adapter.appListAdapterLect;
import com.example.newgroup4.adapter.newAppAdapt;
import com.example.newgroup4.model.Appointment;
import com.example.newgroup4.model.SharedPrefManager;
import com.example.newgroup4.model.StudSideApptxLectName;
import com.example.newgroup4.model.User;
import com.example.newgroup4.model.newApp;
import com.example.newgroup4.remote.ApiUtils;
import com.example.newgroup4.remote.ApptService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LectHome2 extends AppCompatActivity {

    ApptService apptService;
    Context context;
    RecyclerView apptList;
    List<newApp> appointmentListMain = new ArrayList<>();
    public newAppAdapt adapter;

    //menu stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menu inflater
        MenuInflater inflater = super.getMenuInflater();

        // inflate the menu using our XML menu file id, options_menu
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                // user clicked report bugs menu item
                // call method to display dialog box
                LogoutNav(); // tukar sini - ecah's sn
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lect_home2);

        Button btnApproved = findViewById(R.id.btnApproved);
        Button btnProfile = findViewById(R.id.btnProfile);

        btnApproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a new explicit intent to open RegisterSuccess activity
                Intent intent = new Intent(getBaseContext(),  approvedAppLectView.class);
                // start the RegisterSuccess activity
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a new explicit intent to open RegisterSuccess activity
                //Intent intentSuccess = new Intent(this, approvedAppLectView.class);
                // start the RegisterSuccess activity
                //startActivity(intentSuccess);
            }
        });


        //start retrieve

        // get reference to the RecyclerView bookList
        apptList = findViewById(R.id.LectsAppList);

        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // get book service instance
        apptService = ApiUtils.getApptService();

        apptService.getstudnamebylectID(user.getToken(),user.getUsername()).enqueue(new Callback<List<newApp>>() {
            @Override
            public void onResponse(Call<List<newApp>> call, Response<List<newApp>> response) {
                List<newApp> app = response.body();

                adapter = new newAppAdapt(context, app);

                apptList.setAdapter(adapter);

                apptList.setLayoutManager(new LinearLayoutManager(context));

                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(apptList.getContext(),
                        DividerItemDecoration.VERTICAL);
                apptList.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onFailure(Call<List<newApp>> call, Throwable t) {
                Toast.makeText(context, "Error connecting to the server", Toast.LENGTH_LONG).show();
                Log.e("MyApp:", t.getMessage());
            }
        });

        //end retrieve

        /* // Initialize ContactListAdapter
        newAppAdapt contactAdapter = new newAppAdapt(this);

        // Link the data adapter with ListView component inside
        // the main activity layout
        ListView contactList = (ListView)findViewById(R.id.LectsAppList);
        contactList.setAdapter(contactAdapter);

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), appOdecPage.class);
                // get the data from the array based on the item position/index
                /*intent.putExtra("NAME", contactAdapter.getNames()[pos]);
                intent.putExtra("PHONE_NO", contactAdapter.getPhoneNos()[pos]);*/
                // start the details activity
                //startActivity(intent);

         //   }
       // });*/

    }

    private void AppAptBtnClicked(View v) {
        // create a new explicit intent to open RegisterSuccess activity
        Intent intentSuccess = new Intent(this, approvedAppLectView.class);
        // start the RegisterSuccess activity
        startActivity(intentSuccess);

    }

    //getAppointment method
    private List<newApp> getAppointment() {

        User user = SharedPrefManager.getInstance(this).getUser();
        ApptService apptService = ApiUtils.getApptService();
        return  null;
    }


    private void LogoutNav() {
        // prepare a dialog box with yes and no
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout?");
        builder.setMessage("Are you sure do you want to logout?");

        // prepare action listener for each button
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // clear the shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).logout();

                        // display message
                        Toast.makeText(getApplicationContext(),
                                "You have successfully logged out.",
                                Toast.LENGTH_LONG).show();

                        // forward to LoginActivity
                        finish();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                    }
                });

        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        // create the alert dialog and show to the user
        AlertDialog alert = builder.create();
        alert.show();
    }

}