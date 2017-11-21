package com.android.policyboss.utility;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.policyboss.R;
import com.android.policyboss.login.LoginActivity;
import com.android.policyboss.navigationview.HomeActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by IN-RB on 21-11-2017.
 */

public class MyFirebaseMessagingService  extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage, remoteMessage.getData());
    }

    private void sendNotification(RemoteMessage remoteMessage, Map<String, String> data) {

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() == null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            return;
        }

        Map<String, String> NotifyData = remoteMessage.getData();
        String text= NotifyData.get("notifyFlag");
        Intent intent;
        if (!isAppAlive()) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, HomeActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);



        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

    private boolean isAppAlive() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if ((serviceList.size() > 0)) {
            for (int i = 0; i < serviceList.size(); i++) {
                ActivityManager.RunningServiceInfo serviceInfo = serviceList.get(i);
                ComponentName serviceName = serviceInfo.service;
                if (serviceName.toString().contains("com.android.policyboss")) {
                    return true;
                }
            }
        }
        return false;
    }
}
