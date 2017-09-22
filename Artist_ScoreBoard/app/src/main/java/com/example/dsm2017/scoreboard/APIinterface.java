package com.example.dsm2017.scoreboard;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by geni on 2017. 9. 21..
 */

public interface APIinterface {
    @GET("/rank")
    Call<JsonArray> getRank();
}
