package com.example.newgroup4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import android.widget.Toast;

import com.example.newgroup4.adapter.LectAdapter;
import com.example.newgroup4.model.Lecturer;
import com.example.newgroup4.model.SharedPrefManager;
import com.example.newgroup4.model.User;
import com.example.newgroup4.remote.ApiUtils;
import com.example.newgroup4.remote.LectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerList extends AppCompatActivity {

    LectService lectService;
    Context context;
    RecyclerView lecturerList;
    public LectAdapter adapter;
    private LectAdapter.RecyclerViewClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_list);
        context = this; // get current activity context


        // get reference to the RecyclerView lectList
        lecturerList = findViewById(R.id.lecturerList);


        // get user infor from sharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // get lecturer service instance
        lectService = ApiUtils.getLectService();

        // execute the call. send the user token when sending the query
        lectService.getAllLecturer(user.getToken()).enqueue(new Callback<List<Lecturer>>() {
            @Override
            public void onResponse(Call<List<Lecturer>> call, Response<List<Lecturer>> response) {
                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // Get list of lecturer object from reponse
                List<Lecturer> lecturers = response.body();

                // add listener
                setOnClickListener();

                // initialize adapter
                adapter = new LectAdapter(context, lecturers, listener);

                // set adapter to the recycler view
                lecturerList.setAdapter(adapter);

                // set layout to recycler view
                lecturerList.setLayoutManager(new LinearLayoutManager(context));

                // add separator between item in the list
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(lecturerList.getContext(),
                        DividerItemDecoration.VERTICAL);
                lecturerList.addItemDecoration(dividerItemDecoration);
            }

            private void setOnClickListener() {
                listener = new LectAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(), StudentAddAppointment.class);
                        //intent.putExtra()
                        startActivity(intent);
                    }
                };
            }

            @Override
            public void onFailure(Call<List<Lecturer>> call, Throwable t) {
                Toast.makeText(context, "Error connecting to the server", Toast.LENGTH_LONG).show();
                Log.e("MyApp:", t.getMessage());
            }
        });


    }


    // add 5
    @Override
    public boolean onCreateOptionsMenu(Menu menu){



        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_test, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        //searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

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
    /**
     * Displaying an alert dialog with a single button
     * @param message - message to be displayed
     */
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