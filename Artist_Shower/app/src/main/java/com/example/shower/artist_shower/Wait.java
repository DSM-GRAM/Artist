package com.example.shower.artist_shower;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wait extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private ArrayList<WaitItem> Dataset;
    private APIinterface apIinterface;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait);

        Log.d("xxx", "" + FirebaseInstanceId.getInstance().getToken());

//        Dataset = new ArrayList<>();
//        myAdapter = new WaitAdapter(Dataset);
//        mRecyclerView.setAdapter(myAdapter);

        apIinterface = APIClient.getClient().create(APIinterface.class);

        apIinterface.registDevice(FirebaseInstanceId.getInstance().getToken(), 2).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });


        apIinterface.getRank().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.code() == 200) {
                    Log.d("Test", response.body().get(0).getAsJsonObject().get("phone")+"");
//                    Gson gson = new Gson();
//                    WaitItem[] items = gson.fromJson(response.body().toString(), WaitItem[].class);
//                    ((WaitAdapter)mRecyclerView.getAdapter()).setData(items);
//                    mRecyclerView.getAdapter().notifyDataSetChanged();
//
//                    response.body();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.d("Test", "Fail");
                t.printStackTrace();
            }
        });

//        for(int i = 0; i < 5; i++) {
//            WaitItem waitItem = new WaitItem();
//            waitItem.setName("서윤호");
//            waitItem.setSchool("대덕소프트웨어마이스터고");
//            waitItem.setScore("999,999,999");
//            this.Dataset.add(i, waitItem);
//        }
    }
}
