package com.example.shower.artist_shower;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

public class Drawing extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private int i=0;
    private ImageView imgDrawing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing);
        Intent intent = getIntent();
        String _id = FirebaseManager.uri;
        Log.d("i_Id", _id);
        imgDrawing = (ImageView)findViewById(R.id.img_drawing);

        CountDownTimer mCountDownTimer;

        Log.d("img url", "http://52.79.134.200:5590/sample/" + _id);
        Glide.with(getApplicationContext()).load("http://52.79.134.200:5590/sample/" + _id).into(imgDrawing);

        mProgressBar=(ProgressBar)findViewById(R.id.pbId);
        mProgressBar.setProgress(i);
        mCountDownTimer=new CountDownTimer(120000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                mProgressBar.setProgress((int)i*100/(120000/1000));

            }

            @Override
            public void onFinish() {
                //Do what you want
                i++;
                mProgressBar.setProgress(100);
            }
        };
        mCountDownTimer.start();
    }
}
