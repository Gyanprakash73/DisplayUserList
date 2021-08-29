package com.gyan.disuserslist.Instance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gyan.disuserslist.Api.ApiSet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

    static final String url="https://reqres.in/api/";

    private static ApiController clientObject;
    private static Retrofit retrofit;

    ApiController(){

        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiController getInstance(){
        if (clientObject==null){
            clientObject=new ApiController();
        }
        return clientObject;
    }

    public ApiSet getApi(){
        return retrofit.create(ApiSet.class);
    }
}
