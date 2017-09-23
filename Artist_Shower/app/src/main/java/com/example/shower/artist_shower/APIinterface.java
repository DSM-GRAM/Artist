package com.example.shower.artist_shower;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

import static android.R.attr.type;


/**
 * Created by dsm2016 on 2017-09-19.
 */

public interface APIinterface {

    @GET("/sample/{_id}")
    Call<Item> getSample(@Url String url);

    @FormUrlEncoded
    @POST("/start-draw/{_id}")
    Call<Item> postStart(@Query("Message") String message);

    @GET("/rank")
    Call<JsonArray> getRank();

    @FormUrlEncoded
    @POST("/device")
    Call<Void>registDevice(@Field("registration_id") String registration_id,
                           @Field("type") int type_num);
}

