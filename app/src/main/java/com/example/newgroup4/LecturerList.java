package com.example.newgroup4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.newgroup4.adapter.LectAdapter;
import com.example.newgroup4.model.Lecturer;
import com.example.newgroup4.model.SharedPrefManager;
import com.example.newgroup4.model.User;
import com.example.newgroup4.remote.ApiUtils;
import com.example.newgroup4.remote.LectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerList extends AppCompatActivity {

    LectService lectService;
    Context context;
    RecyclerView lecturerList;
    private LectAdapter adapter;


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

                // initialize adapter
                LectAdapter adapter = new LectAdapter(context, lecturers);

                // set adapter to the recycler view
                lecturerList.setAdapter(adapter);

                // set layout to recycler view
                lecturerList.setLayoutManager(new LinearLayoutManager(context));

                // add separator between item in the list
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(lecturerList.getContext(),
                        DividerItemDecoration.VERTICAL);
                lecturerList.addItemDecoration(dividerItemDecoration);
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



}