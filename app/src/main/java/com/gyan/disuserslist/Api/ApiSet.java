package com.gyan.disuserslist.Api;

import com.gyan.disuserslist.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiSet {

    @GET("users?page=1")
    Call<UserModel> getPage1();

    @GET("users?page=2")
    Call<UserModel> getPage2();

}
