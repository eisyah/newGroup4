package com.example.newgroup4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.newgroup4.adapter.LecturerSpinnerAdapter;
import com.example.newgroup4.model.Lecturer;
import com.example.newgroup4.model.SharedPrefManager;
import com.example.newgroup4.model.Student;
import com.example.newgroup4.model.User;
import com.example.newgroup4.remote.ApiUtils;
import com.example.newgroup4.remote.LectService;
import com.example.newgroup4.remote.StudService;

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
    private TextView tvTime ;
    private TextView txtReason ;
    private TextView txtStatus ;
    private static Date createdAt;

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
        tvTime = findViewById(R.id.tvTime);
        txtReason = findViewById(R.id.txtReason);
        txtStatus = findViewById(R.id.txtStatus);


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

    public void addNewAppt()
    {
    }
}