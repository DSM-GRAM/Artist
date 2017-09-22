package com.example.shower.artist_shower;

import android.app.ActivityManager;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dsm2016 on 2017-09-20.
 */

public class FirebaseManager extends FirebaseMessagingService {
    public static boolean flag = false;
    public static String body = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
//        Log.d("xxx", "onMessageReceived: " + remoteMessage.getNotification().getBody());
        body = remoteMessage.getNotification().getBody();
        try {
            JSONObject obj = new JSONObject(body);
            ActivityManager activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> info;
            info = activityManager.getRunningTasks(1);
            if(info.get(0).topActivity.getClassName().equals(Wait.class)) {
                if(obj.getString("message").equals("sample")) {
                    String uri = obj.getString("_id");

                    Intent intent = new Intent(this, ShowSample.class);
                    startActivity(intent);
                } else if(obj.getString("message").equals("start")) {

                    Intent intent = new Intent(this, Drawing.class);
                }
            } else {
                return;
            }

        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
