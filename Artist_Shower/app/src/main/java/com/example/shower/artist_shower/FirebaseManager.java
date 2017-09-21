package com.example.shower.artist_shower;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by dsm2016 on 2017-09-20.
 */

public class FirebaseManager extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("xxx", "onMessageReceived: " + remoteMessage.getNotification().getBody());
    }
}
