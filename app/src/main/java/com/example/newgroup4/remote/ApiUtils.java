package com.example.newgroup4.remote;


public class ApiUtils {
    //rest api server url
    public static final String BASE_URL = "https://557group4.000webhostapp.com/prestige/";

    //return UserService instance
    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

    // return ApptService
    public static ApptService getAppService(){
        return RetrofitClient.getClient(BASE_URL).create(ApptService.class);
    }

}


