package com.example.shower.artist_shower;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by dsm2016 on 2017-09-19.
 */

public interface APIinterface {

    @FormUrlEncoded
    @POST("/new-sample")
    Call<Void> callSample(@Field("category") int category);

    @GET("/sample/{_id}")
    Call<JsonObject> getSample(@Path("id") int sample_id);

//    @FormUrlEncoded
//    @POST("/start-draw/{id}")
//    Call<Void> get

}
