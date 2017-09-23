package com.example.shower.artist_shower;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
    private APIinterface apIinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_sample);

        img_sample = (ImageView) findViewById(R.id.sample_img);
        Log.d("test", "http://52.79.134.200/sample/" + FirebaseInstanceId.getInstance().getToken());
        Glide.with(getApplicationContext()).load("http://52.79.134.200/sample/" + FirebaseInstanceId.getInstance().getToken());

        // ImageURL 받음
        Call<Item> sample = apIinterface.getSample(FirebaseInstanceId.getInstance().getToken());
        sample.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.code() == 200)  {
                    Log.i("Test", "success");
                    Item item = response.body();
                    Glide.with(getApplicationContext())
                            .load(item.getSample_id())
                            .into(img_sample);
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
