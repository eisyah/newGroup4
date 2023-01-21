package com.example.newgroup4.remote;

import com.example.newgroup4.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @FormUrlEncoded
    @POST("api/users/login")
    Call<User> login(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("api/users/login")
    Call<User> LoginEmail(@Field("email") String email, @Field("password") String password);

}
