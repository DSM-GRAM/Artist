package com.example.dsm2017.scoreboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private APIinterface apiInterface;
    private ArrayList<RankValueObject> RVOlist = new ArrayList<>();
    private RecyclerView scoreBoards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreBoards = (RecyclerView)findViewById(R.id.scoreBoards);
        apiInterface = APIClient.getClient().create(APIinterface.class);

        apiInterface.getRank().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                for(int i = 0; i < 8; i++){
                    RankValueObject RVO = new RankValueObject();
                    RVO.setName(decode(response.body().get(i).getAsJsonObject().get("name").getAsString()));
                    RVO.setAffiliation(decode(response.body().get(i).getAsJsonObject().get("affiliation").getAsString()));
                    RVO.setScore(response.body().get(i).getAsJsonObject().get("score").getAsInt());
                    RVO.setPhone(response.body().get(i).getAsJsonObject().get("phone").getAsString());
                    RVOlist.add(i, RVO);
                }
                RankAdapter rankAdapter = new RankAdapter(RVOlist, getApplicationContext());
                scoreBoards.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
                scoreBoards.setAdapter(rankAdapter);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static String decode(String encoded) {
        // "%u" followed by 4 hex digits, capture the digits
        Pattern p = Pattern.compile("%u([0-9a-f]{4})", Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(encoded);
        StringBuffer decoded = new StringBuffer(encoded.length());

        // replace every occurrences (and copy the parts between)
        while (m.find()) {
            m.appendReplacement(decoded, Character.toString((char)Integer.parseInt(m.group(1), 16)));
        }

        m.appendTail(decoded);
        return decoded.toString();
    }
}
