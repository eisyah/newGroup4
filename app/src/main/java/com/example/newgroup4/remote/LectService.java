package com.example.newgroup4.remote;

import com.example.newgroup4.model.Lecturer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface LectService {

    //get lecturer by id
    @GET("api/lecturers/getbylectID")
    Call<Lecturer> getbylectID( @Field("lectID") String lectID);
}
