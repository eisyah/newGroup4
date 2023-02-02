package com.example.newgroup4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.adapter.StudSideApptAdapter;
import com.example.newgroup4.model.Appointment;
import com.example.newgroup4.model.DeleteResponse;
import com.example.newgroup4.model.SharedPrefManager;
import com.example.newgroup4.model.StudSideApptxLectName;
import com.example.newgroup4.model.User;
import com.example.newgroup4.remote.ApiUtils;
import com.example.newgroup4.remote.ApptService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentHome extends AppCompatActivity {

    ApptService apptService;
    Context context;
    RecyclerView apptList;
    SearchView searchView;
    ImageButton imageButton;
    public StudSideApptAdapter adapter;

    //menu stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menu inflater
        MenuInflater inflater = super.getMenuInflater();
        MenuInflater inflater2 = getMenuInflater();

        // inflate the menu using our XML menu file id, options_menu
        inflater.inflate(R.menu.options_menu, menu);
        inflater2.inflate(R.menu.menu_appt, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return true;
    }
    @Override //menu stuff
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
        setContentView(R.layout.activity_student_home);
        context = this; // get current activity context

        imageButton = (ImageButton) findViewById(R.id.search);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)   {
                // forward user to LecturerList
            Intent intent = new Intent(context, LecturerList.class);
            startActivity(intent);
            }
        });

        // get reference to the RecyclerView apptList
        apptList = findViewById(R.id.apptList);

        updateListView();


    }

    private void updateListView() {
        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // get appt service instance
        apptService = ApiUtils.getApptService();

        // execute the call. send the user token when sending the query
        apptService.getLectNameByStudID(user.getToken(),user.getUsername()).enqueue(new Callback<List<StudSideApptxLectName>>() {
            @Override
            public void onResponse(Call<List<StudSideApptxLectName>> call, Response<List<StudSideApptxLectName>> response) {
                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // Get list of appointment object from response
                List<StudSideApptxLectName> appointments = response.body();

                // initialize adapter
                adapter = new StudSideApptAdapter(context, appointments);

                // set adapter to the RecylerView
                apptList.setAdapter(adapter);

                // set layout to recycler view
                apptList.setLayoutManager(new LinearLayoutManager(context));

                // add separator between item in the list
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(apptList.getContext(),
                        DividerItemDecoration.VERTICAL);
                apptList.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onFailure(Call<List<StudSideApptxLectName>> call, Throwable t) {
                Toast.makeText(context, "Error connecting to the server", Toast.LENGTH_LONG).show();
                Log.e("MyApp:", t.getMessage());
            }
        });
    }


    //logout on menu
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

    //implement button add appointment
    public void doAddAppointment(View view) {
        Intent intent = new Intent(this, StudentAddAppointment.class);
        startActivity(intent);
    }

    public boolean onContextItemSelected(MenuItem item){
        StudSideApptxLectName selected = adapter.getSelectedItem();
        Log.d("My App", "selected " +selected.toString());
        switch(item.getItemId()){
            case R.id.menu_delete:
                doDeleteAppt(selected);
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void doDeleteAppt(StudSideApptxLectName selected){
        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // get appt service instance
        apptService = ApiUtils.getApptService();
        Call<DeleteResponse> call = apptService.deleteAppt(user.getToken(), Integer.parseInt(selected.getStudID()));

        call.enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if (response.code() == 200) {
                    // 200 means OK
                    displayAlert("Appointment successfully deleted");
                    // update data in list view
                    updateListView();
                } else {
                    displayAlert("Appointment failed to delete");
                    Log.e("MyApp:", response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                displayAlert("Error [" + t.getMessage() + "]");
                Log.e("MyApp:", t.getMessage());
            }
        });
    }
    public void displayAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


}