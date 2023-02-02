package com.example.newgroup4.remote;

import com.example.newgroup4.model.Appointment;
import com.example.newgroup4.model.StudSideApptxLectName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface ApptService {

    @GET("api/appointment/getall")
    Call<List<Appointment>> getAllAppointments(@Header("api-key") String api_key);


    //=== Student's api's ===
    //get all all appointment filtered by student id
    @GET("api/appointment/getbystudID")
    Call<List<Appointment>> getAppointmentByStudID(@Header("api-key") String api_key, @Query("studID") String studID);

    //to get lecture name for student appointment view
    @GET("api/appointment/getlectnamebystudID")
    Call<List<StudSideApptxLectName>> getLectNameByStudID(@Header("api-key") String api_key, @Query("studID") String studID);


    //==== lecturer's api's ====
    //api to get appointment with multiple appointments filtered by lecturer id
    @GET("api/appointment/getbylectID")
    Call<List<Appointment>> getMultAppointmentByLectID(@Header("api-key") String api_key, @Query("lectID") String lectID);

    //api to get single appointment filtered by lecturer ID
    @GET("api/appointment/getbylectID")
    Call<Appointment> getSingAppointmentByLectID(@Header("api-key") String api_key, @Query("lectID") String lectID);

}
