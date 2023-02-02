package com.example.newgroup4.remote;

import com.example.newgroup4.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface StudService {
    //get student by id
    @GET("api/students/getbystudID")
    Call<Student> getbystudID(@Header("api-key") String api_key, @Query("studID_fk") String studID);

    //custom API for getting student
    @GET("api/students/getall")
    Call<List<Student>> getAllStudent(@Header("api-key") String api_key);

}
