package com.example.shower.artist_shower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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

//        gitHubService = retrofit.create(GitHubService.class);
//
//        Call<Item> call = gitHubService.getSample(1);
//        call.enqueue(new Callback<Item>() {
//            @Override
//            public void onResponse(Call<Item> call, Response<Item> response) {
//                Item item = response.body();
//                img_sample.setImageResource(item.hashCode());
//            }
//
//            @Override
//            public void onFailure(Call<Item> call, Throwable t) {
//
//            }
//        });
    }

    public void getImage() {
//        gitHubService = APIClient.get
    }


}
