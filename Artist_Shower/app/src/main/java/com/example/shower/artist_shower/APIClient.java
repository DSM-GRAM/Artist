package com.example.shower.artist_shower;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dsm2016 on 2017-09-20.
 */

public class APIClient {
    private static  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://52.79.134.200:5590")
                .addConverterFactory(GsonConverterFactory.create())
            .build();
}
