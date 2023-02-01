package com.example.newgroup4.remote;

import com.example.newgroup4.model.Appointment;
import com.example.newgroup4.model.ApptxLectName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface ApptService {

    @GET("api/appointment/getall")
    Call<List<ApptxLectName>> getAllAppointments(@Header("api-key") String api_key);

    @GET("api/appointment/getbystudID")
    Call<List<Appointment>> getAppointmentByStudID(@Header("api-key") String api_key, @Query("studID") String studID);

    //to get lecture name for student appointment view
    @GET("api/appointment/getlectnamebystudID")
    Call<List<ApptxLectName>> getLectNameByStudID(@Header("api-key") String api_key, @Query("studID") String studID);
}
