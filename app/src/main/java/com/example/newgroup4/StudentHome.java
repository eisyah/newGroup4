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

    //menu stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menu inflater
        MenuInflater inflater = super.getMenuInflater();

        // inflate the menu using our XML menu file id, options_menu
        inflater.inflate(R.menu.options_menu, menu);

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
                StudSideApptAdapter adapter = new StudSideApptAdapter(context, appointments);

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

   /* private void filterList(String Text) {
        List<Lecturer> filteredList = new ArrayList<>();
        for(Lecturer lecturer: apptList){
            if(lecturer.getLectName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(lecturer);
            }

            if(filteredList.isEmpty()){
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }else{
                adapter.setFilteredList(filteredList);
            }

        }
    }*/

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



}