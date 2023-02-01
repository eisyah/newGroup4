package com.example.newgroup4.remote;

import com.example.newgroup4.model.Lecturer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LectService {

    @GET("api/lecturers")
    Call<List<Lecturer>> getAllLecturer(@Header("api-key") String api_key);

    //get lecturer by id
    @GET("api/lecturers/getbylectID")
    Call<Lecturer> getbylectID( @Field("lectID") String lectID);


}
