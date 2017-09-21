package com.example.shower.artist_shower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ShowSample extends AppCompatActivity {

    private ImageView img_sample;
    private Retrofit retrofit;
    private GitHubService gitHubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_sample);

        img_sample = (ImageView) findViewById(R.id.sample_img);

        gitHubService = retrofit.create(GitHubService.class);

        retrofit = new Retrofit.Builder()
                .baseUrl("52.79.134.200:5590")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubService = retrofit.create(GitHubService.class);

        // 요청 보냄
        Call<Item> callSample = gitHubService.callSample();
        callSample.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Log.d("TEST", "SUCCESS");
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("Test", "FAIL");
            }
        });

        // ImageURL 받음
        Call<Item> sample = gitHubService.getSample(1);
        sample.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Log.i("Test", response.body().toString());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("TEST", "FAIL");
            }
        });

        // 푸쉬 알림을 줌
        Call<Item> pushNotice = gitHubService.postStart("Start");
        pushNotice.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });

    }



}
