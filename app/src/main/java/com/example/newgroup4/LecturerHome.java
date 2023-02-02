package com.example.newgroup4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newgroup4.adapter.ArrayAdaptLectsApp;
import com.example.newgroup4.adapter.CalendarAdapt;
import com.example.newgroup4.model.Appointment;
import com.example.newgroup4.model.SharedPrefManager;
import com.example.newgroup4.model.User;
import com.example.newgroup4.remote.ApiUtils;
import com.example.newgroup4.remote.ApptService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerHome extends AppCompatActivity implements CalendarAdapt.OnitemListener {

    private TextView monthYearText;
    private RecyclerView calRecyleView;
    private LocalDate selectedDate;
    private List<Appointment> appointmentListMain = new ArrayList<>();

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
        setContentView(R.layout.activity_lecturer_home);

        //get appointment and set them into the calendar
        appointmentListMain = getAppointment();

        //calender stuff
        initWidgets();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = LocalDate.now();

        }
        setMonthView(appointmentListMain);


    }

    private void initWidgets()
    {
        calRecyleView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView(List<Appointment> appointmentList)
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        String month = monthYearText.getText().toString();
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        //CalendarAdapt calendarAdapter = new CalendarAdapt(daysInMonth, this, appointmentList, appointmentListMain, month);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calRecyleView.setLayoutManager(layoutManager);
        //calRecyleView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            yearMonth = YearMonth.from(date);
        }

        int daysInMonth = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            daysInMonth = yearMonth.lengthOfMonth();
        }

        LocalDate firstOfMonth = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            firstOfMonth = selectedDate.withDayOfMonth(1);
        }
        int dayOfWeek = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        }

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("MM yyyy");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.format(formatter);
        }
        else
            return null;
    }

    public void previousMonthAction(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.minusMonths(1);
        }
        setMonthView(appointmentListMain);
    }

    public void nextMonthAction(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.plusMonths(1);
        }
        setMonthView(appointmentListMain);
    }


    //onclick kalau tekan kotak date
    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals("")) {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getBaseContext(), LectDateApp.class);
            // get the data from the array based on the item position/index
            /*intent.putExtra("NAME", ArrayAdaptLectsApp.getNames()[pos]);
            intent.putExtra("TIME", ArrayAdaptLectsApp.getPhoneNos()[pos]);*/
            intent.putExtra("DATE", dayText + " " + monthYearFromDate(selectedDate));
            // start the details activity
            startActivity(intent);

        }
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

    //getAppointment method
    private List<Appointment> getAppointment() {


        User user = SharedPrefManager.getInstance(this).getUser();
        ApptService apptService = ApiUtils.getApptService();

        apptService.getMultAppointmentByLectID(user.getToken(),user.getUsername()).enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                if (response.isSuccessful()) {
                    List<Appointment> appointmentList = response.body();
                    appointmentListMain.addAll(appointmentList);
                    Toast.makeText(getApplicationContext(), "Appointment retrieved", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {

                ApptService apptService = ApiUtils.getApptService();

                apptService.getSingAppointmentByLectID(user.getToken(),user.getUsername()).enqueue(new Callback<Appointment>() {
                    @Override
                    public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                        if (response.isSuccessful()) {
                            Appointment appointment = response.body();
                            appointmentListMain.add(appointment);
                            Toast.makeText(getApplicationContext(), "Appointment retrieved", Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<Appointment> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Appointment not retrieved", Toast.LENGTH_LONG).show();
                        Log.e("MyApp:", t.getMessage());
                    }
                });

                //Toast.makeText(getApplicationContext(), "Appointment not retrieved", Toast.LENGTH_LONG).show();
                //Log.e("MyApp:", t.getMessage());
            }
        });


        return appointmentListMain;
    }




}