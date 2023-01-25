package com.example.newgroup4.remote;

import com.example.newgroup4.model.Appointment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


public interface ApptService {
    @GET("api/appointment")
    Call<List<Appointment>> getAllAppointments(@Header("api-key") String api_key);
}
