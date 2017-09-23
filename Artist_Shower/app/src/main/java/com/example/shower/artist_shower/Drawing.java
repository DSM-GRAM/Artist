package com.example.shower.artist_shower;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class Drawing extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing);

        CountDownTimer mCountDownTimer;

        mProgressBar=(ProgressBar)findViewById(R.id.pbId);
        mProgressBar.setProgress(i);
        mCountDownTimer=new CountDownTimer(5000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                mProgressBar.setProgress((int)i*100/(5000/1000));

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
