package com.example.newgroup4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newgroup4.model.SharedPrefManager;
import com.example.newgroup4.model.User;

public class LecturerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_home);

        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

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
}