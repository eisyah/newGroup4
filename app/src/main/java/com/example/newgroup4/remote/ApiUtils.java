package com.example.newgroup4.remote;


public class ApiUtils {
    //rest api server url
    public static final String BASE_URL = "https://csc557g42.000webhostapp.com/prestige/";

    //return UserService instance
    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

    // return ApptService
    public static ApptService getApptService(){
        return RetrofitClient.getClient(BASE_URL).create(ApptService.class);
    }

    //return LectService instance
    public static LectService getLectService() {
        return RetrofitClient.getClient(BASE_URL).create(LectService.class);
    }



}


