package com.example.shower.artist_shower;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dsm2016 on 2017-09-19.
 */

public class RetrofitClass {
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://52.79.134.200:5590")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
