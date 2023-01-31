package com.example.newgroup4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.adapter.ApptAdapter;
import com.example.newgroup4.model.ApptxLectName;
import com.example.newgroup4.model.SharedPrefManager;
import com.example.newgroup4.model.User;
import com.example.newgroup4.remote.ApiUtils;
import com.example.newgroup4.remote.ApptService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentHome extends AppCompatActivity {

    ApptService apptService;
    Context context;
    RecyclerView apptList;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        context = this; // get current activity context

        // get reference to the searchView
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        public void setFilteredList(List<ApptxLectName> filterList){
            this.apptList = filterList();
            notifyDataSetChanged();
        }

        // get reference to the RecyclerView apptList
        apptList = findViewById(R.id.apptList);

        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // get appt service instance
        apptService = ApiUtils.getAppService();

        // execute the call. send the user token when sending the query
        apptService.getLectNameByStudID(user.getToken(),user.getUsername()).enqueue(new Callback<List<ApptxLectName>>() {
            @Override
            public void onResponse(Call<List<ApptxLectName>> call, Response<List<ApptxLectName>> response) {
                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // Get list of appointment object from response
                List<ApptxLectName> appointments = response.body();

                // initialize adapter
                ApptAdapter adapter = new ApptAdapter(context, appointments);

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
            public void onFailure(Call<List<ApptxLectName>> call, Throwable t) {
                Toast.makeText(context, "Error connecting to the server", Toast.LENGTH_LONG).show();
                Log.e("MyApp:", t.getMessage());
            }
        });

        // assign action to logout button
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    }

    private void filterList(String Text) {
        List<ApptxLectName> filteredList = new ArrayList<>();
        for(ApptxLectName appointment: apptList){
            if(appointment.getLectName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(appointment);
            }

            if(filteredList.isEmpty()){
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }else{
                adapter.setFilteredList(filteredList);
            }

        }
    }

}