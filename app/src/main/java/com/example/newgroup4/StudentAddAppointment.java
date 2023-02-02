package com.example.newgroup4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.newgroup4.adapter.LecturerSpinnerAdapter;
import com.example.newgroup4.model.Appointment;
import com.example.newgroup4.model.Lecturer;
import com.example.newgroup4.model.SharedPrefManager;
import com.example.newgroup4.model.StudSideApptxLectName;
import com.example.newgroup4.model.Student;
import com.example.newgroup4.model.User;
import com.example.newgroup4.remote.ApiUtils;
import com.example.newgroup4.remote.ApptService;
import com.example.newgroup4.remote.LectService;
import com.example.newgroup4.remote.StudService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//declare the variables

public class StudentAddAppointment extends AppCompatActivity {
    private TextView txtName ;
    private Spinner txtLectName;
    private static TextView tvDate ;
    private Spinner tvTime;
    private TextView txtReason ;
    private TextView txtStatus ;
    private static Date createdAt;
    private Context context;

    /**
     * Date picker fragment class
     * Reference: https://developer.android.com/guide/topics/ui/controls/pickers
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            // create a date object from selected year, month and day
            createdAt = new GregorianCalendar(year, month, day).getTime();

            // display in the label beside the button with specific date format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tvDate.setText( sdf.format(createdAt) );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add_appointment);


        //get reference to the textview
        txtName = findViewById(R.id.txtName);
        txtLectName = findViewById(R.id.txtLectName);
        tvDate = findViewById(R.id.tvDate);
        txtReason = findViewById(R.id.txtReason);
        txtStatus = findViewById(R.id.txtStatus);
        tvTime = findViewById(R.id.tvTime);



        createdAt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tvDate.setText( sdf.format(createdAt) );

        //get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        //code block to get name for student and set txt view
        {
            StudService studService = ApiUtils.getStudService();

            Call<Student> call = studService.getbystudID(user.getToken(), user.getUsername());

            call.enqueue(new Callback<Student>() {
                @Override
                public void onResponse(Call<Student> call, Response<Student> response) {
                    if (response.isSuccessful()) {
                        Student student = response.body();
                        txtName.setText(student.getStudName());
                    }
                }
                @Override
                public void onFailure(Call<Student> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        }



        //code block for setting lecturer name into spinner
        {



            LectService lectService = ApiUtils.getLectService();

            Call<List<Lecturer>> call = lectService.getAllLecturer(user.getToken());

            //execute the call asynchronously. Get a positive or negative callback.
            call.enqueue(new Callback<List<Lecturer>>() {

                @Override
                public void onResponse(Call<List<Lecturer>> call, Response<List<Lecturer>> response) {
                    //for debug purpose
                    Log.d("MyApp:", "Response: " + response.raw().toString());

                    //invalid session?
                    if (response.code() == 401) {
                        //redirect to login
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }

                    //get the list of books from response
                    List<Lecturer> lecturers = response.body();
                    //set the list of books to the spinner
                    txtLectName.setAdapter(new LecturerSpinnerAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, lecturers));
                }

                @Override
                public void onFailure(Call<List<Lecturer>> call, Throwable t) {
                    Log.e("MyApp:", "Failure: " + t.getMessage());
                }
            });
        }

        //set font and make some fonts invisible for txt
        txtName.setEnabled(false);
        txtStatus.setText("New");
        txtStatus.setEnabled(false);
        txtStatus.setTypeface(null, Typeface.BOLD);




    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }



    public void addNewAppt(View v) {
        String name = txtName.getText().toString();
        String lectName = txtLectName.getSelectedItem().toString();
        String reason = txtReason.getText().toString();
        String status = txtStatus.getText().toString();
        String time = tvTime.getSelectedItem().toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String test_time = dateFormat.format(time);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String created_at = sdf.format(createdAt);

        Appointment a = Appointment(0, name, lectName, created_at, test_time, reason, status);
        //get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        ApptService apptService = ApiUtils.getApptService();
        Call<Appointment> call = apptService.addAppt(user.getToken(), a);

        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {

                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // invalid session?
                if (response.code() == 401)
                    displayAlert("Invalid session. Please re-login");

                // appointment added successfully?
                Appointment added = response.body();
                if(added != null){

                    Toast.makeText(context,
                            added.getApptID() + "added succesfully.",
                            Toast.LENGTH_LONG).show();

                    //end thus activity forward to home
                    Intent intent = new Intent(context, StudentHome.class);
                    startActivity(intent);
                    finish();
                }else{
                    displayAlert("Add appointment failed");
                }
            }


            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {
                displayAlert("Error [" + t.getMessage() + "]");
                // for debug purpose
                Log.d("MyApp:", "Error: " + t.getCause().getMessage());
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