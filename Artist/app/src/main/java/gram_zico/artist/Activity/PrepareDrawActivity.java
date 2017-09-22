package gram_zico.artist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import gram_zico.artist.BaseActivity;
import gram_zico.artist.Connect.RetrofitClass;
import gram_zico.artist.Model.CategoryIDModel;
import gram_zico.artist.Model.IntentDataModel;
import gram_zico.artist.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root1 on 2017. 9. 13..
 */

public class PrepareDrawActivity extends BaseActivity {

    private int count;
    private String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_draw);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        TextView titleText = (TextView)findViewById(R.id.titleText);
        titleText.setText(title);
        count = intent.getIntExtra("count", -1);
        Button startButton = (Button)findViewById(R.id.startButton);

        getID();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userID == null){
                    showToast("다시 한 번 눌러주세요");
                }else{
                    RetrofitClass.getInstance().apiInterface.startDraw(userID).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code() == 201){
                                ArrayList<IntentDataModel> data = new ArrayList<>();
                                data.add(new IntentDataModel("userID", userID));
                                data.add(new IntentDataModel("category", "" + count));
                                goNextActivity(CanvasActivity.class, data);
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            }
        });
    }

    private void getID(){
        RetrofitClass.getInstance().apiInterface.getCategoryID(count).enqueue(new Callback<CategoryIDModel>() {
            @Override
            public void onResponse(Call<CategoryIDModel> call, Response<CategoryIDModel> response) {
                if(response.code() == 201){
                    Log.d("xxx", ""+response.body().getCategoryID());
                    userID = response.body().getCategoryID();
                }
            }

            @Override
            public void onFailure(Call<CategoryIDModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
