package com.example.jharwalshoes.myshoes.api;

import com.example.jharwalshoes.myshoes.model.UserRewards;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

 /*   @Headers("Content-Type: application/json")
    @POST("/api/users/login")
    Call<ResponseModel> login(@Body RequestModel requestModel);*/

    @GET("/v2/5cb80a104c0000b714d3d305")
    Call<UserRewards> getAllRewards();

    /*@GET("/v2/5ca89970520000582697b7db")
    Call<Songs> getAllImages();*/
}
