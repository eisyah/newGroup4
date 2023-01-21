package com.example.newgroup4.remote;


public class ApiUtils {
    //rest api server url
    public static final String BASE_URL = "https://hihang-hoheng-hanah.000webhostapp.com/pRESTige/";

    //return UserService instance
    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}


