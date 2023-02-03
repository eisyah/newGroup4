package com.example.newgroup4.remote;

import com.example.newgroup4.model.Appointment;
import com.example.newgroup4.model.DeleteResponse;
import com.example.newgroup4.model.StudSideApptxLectName;
import com.example.newgroup4.model.newApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApptService {

    @GET("api/appointment/getall")
    Call<List<Appointment>> getAllAppointments(@Header("api-key") String api_key);


    //=== Student's api's ===
    //get all all appointment filtered by student id
    @GET("api/appointment/getbystudID")
    Call<List<Appointment>> getAppointmentByStudID(@Header("api-key") String api_key, @Query("studID_fk") String studID);

    //to get lecture name for student appointment view
    @GET("api/appointment/getlectnamebystudID")
    Call<List<StudSideApptxLectName>> getLectNameByStudID(@Header("api-key") String api_key, @Query("studID_fk") String studID);


    //==== lecturer's api's ====
    //api to get appointment with multiple appointments filtered by lecturer id
    @GET("api/appointment/getbylectID")
    Call<List<Appointment>> getMultAppointmentByLectID(@Header("api-key") String api_key, @Query("lectID_fk") String lectID);

    //api to get single appointment filtered by lecturer ID
    @GET("api/appointment/getbylectID")
    Call<Appointment> getSingAppointmentByLectID(@Header("api-key") String api_key, @Query("lectID_fk") String lectID);

    @POST("api/appointment")
    Call<Appointment> addAppt(@Header("api-key")String apiKey, @Body Appointment appointment);

    @POST("api/appointment/delete/{id}")
    Call<DeleteResponse> deleteAppt(@Header("api-key") String apiKey, @Path("id") int id);

    @GET("api/appointment/getstudnamebylectID")
    Call <List<newApp>> getstudnamebylectID (@Header("api-key") String api_key, @Query("lectID_fk") String lectID);

    @GET("api/appointment/getstudnamebylectID")
    Call<newApp> getSingstudnamebylectID(@Header("api-key") String api_key, @Query("lectID_fk") String lectID);

}
