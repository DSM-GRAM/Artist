package com.example.shower.artist_shower;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dsm2016 on 2017-09-20.
 */

public class FirebaseManager extends FirebaseMessagingService {
    public static boolean flag = false;
    public static Map<String, String> body;
    public static String uri;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
//        Log.d("xxx", "onMessageReceived: " + remoteMessage.getNotification().getBody());
        Log.d("PUSH", "" + remoteMessage.getData().toString());
        body = remoteMessage.getData();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info;
        info = activityManager.getRunningTasks(1);
        Log.d("sibal", body.get("message"));
        Log.d("sibal", info.get(0).topActivity.getClassName());
        if (body.get("message").equals("sample")) {
            uri = body.get("_id");
            if (info.get(0).topActivity.getClassName().equals(Wait.class.getName())) {
                Intent intent = new Intent(this, ShowSample.class);
                intent.putExtra("_id", uri);
                startActivity(intent);
            }
        } else if (body.get("message").equals("start")) {
            if (info.get(0).topActivity.getClassName().equals(ShowSample.class.getName())) {
                Intent intent = new Intent(this, Drawing.class);
                Log.d("test", uri);
                intent.putExtra("_id", uri);
                startActivity(intent);
            }
        }
    }
}
