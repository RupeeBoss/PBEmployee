package com.android.policyboss.utility;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.policyboss.R;
import com.android.policyboss.core.models.NotificationMasterEntity;
import com.android.policyboss.login.LoginActivity;
import com.android.policyboss.navigationview.HomeActivity;
import com.android.policyboss.notification.NotificationActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import io.realm.Realm;

/**
 * Created by IN-RB on 21-11-2017.
 */

public class MyFirebaseMessagingService  extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Realm realm = Realm.getDefaultInstance();

    NotificationMasterEntity notificationMaster;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage, remoteMessage.getData());
    }

    private void sendNotification(final RemoteMessage remoteMessage, Map<String, String> data) {

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() == null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            return;
        }

        notificationMaster = new NotificationMasterEntity();

        Map<String, String> NotifyData = remoteMessage.getData();
        String text= NotifyData.get("notifyFlag");
        Intent intent;
        if (!isAppAlive()) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, NotificationActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);


       // new AddNotification().execute(remoteMessage);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Get a Realm instance for this thread
                Realm realm = Realm.getDefaultInstance();
              //  remoteMessage.getNotification().getTag()
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        notificationMaster.setNotifyid(System.currentTimeMillis());
                        notificationMaster.setDate(df.format(c.getTime()));
                        notificationMaster.setTitle(remoteMessage.getNotification().getTitle());
                        notificationMaster.setMessage(remoteMessage.getNotification().getBody());
                        notificationMaster.setImgUrl(remoteMessage.getNotification().getIcon());
                        realm.copyToRealmOrUpdate(notificationMaster);
                    }
                });
            }
        });
        thread.start();



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




//    private class AddNotification extends AsyncTask<RemoteMessage, Void, Integer> {
//
//        @Override
//        protected Integer doInBackground(final RemoteMessage... _remoteMessages) {
//            // Now in a background thread.
//
//            // Open the Realm
//            Realm realm = Realm.getDefaultInstance();
//            try {
//                // Work with Realm
////                realm.createAllFromJson(Order.class, api.getNewOrders());
////                Order firstOrder = realm.where(Order.class).findFirst();
////                long orderId = firstOrder.getId(); // Id of order
////                return orderId;
//
//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        notificationMaster.setTitle(_remoteMessages.getNotification().getTitle());
//                        notificationMaster.setMessage(_remoteMessages.getNotification().getBody());
//                        realm.copyToRealmOrUpdate(notificationMaster);
//                    }
//                });
//            } finally {
//                realm.close();
//            }
//
//            return 0;
//        }
//
//        protected void onPostExecute(Integer orderId) {
//            // Back on the Android mainThread
//            // do something with orderId such as query Realm
//            // for the order and perform some operation with it.
//           ;
//        }
//    }


}
