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

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    NotificationMasterEntity notificationMaster;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage, remoteMessage.getData());
    }

    private void sendNotification(final RemoteMessage remoteMessage, Map<String, String> data) {

        // Check if message contains a data payload.

        if (remoteMessage.getData().size() == 0) {
            Log.d(TAG, "Message Data Body Empty: ");
            return;
        }
        Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        Map<String, String> NotifyData = remoteMessage.getData();

        notificationMaster = new NotificationMasterEntity();

        //String text = NotifyData.get("notifyFlag");
        Intent intent;
        if (!isAppAlive()) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, NotificationActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        new AddNotification(remoteMessage).execute();

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(NotifyData.get("title"))
                .setContentText(NotifyData.get("body"))
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

    private class AddNotification extends AsyncTask<Void, Void, Integer> {

        Map<String, String> myNotifyData;

        public AddNotification(RemoteMessage remoteMessage){

            this.myNotifyData = remoteMessage.getData();
        }


        @Override
        protected Integer doInBackground(Void... voids) {
            // Now in a background thread.

            // Open the Realm
            Realm realm = Realm.getDefaultInstance();
            try {
                // Work with Realm
//                realm.createAllFromJson(Order.class, api.getNewOrders());
//                Order firstOrder = realm.where(Order.class).findFirst();
//                long orderId = firstOrder.getId(); // Id of order
//                return orderId;

//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        notificationMaster.setTitle(remoteMessage.getNotification().getTitle());
//                        notificationMaster.setMessage(remoteMessage.getNotification().getBody());
//                        realm.copyToRealmOrUpdate(notificationMaster);
//                    }
//                });



                //  remoteMessage.getNotification().getTag()
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        notificationMaster.setNotifyid(System.currentTimeMillis());
                        notificationMaster.setDate(df.format(c.getTime()));
                        notificationMaster.setTitle(myNotifyData.get("title"));
                        notificationMaster.setMessage(myNotifyData.get("body"));
                        notificationMaster.setImgUrl(myNotifyData.get("icon"));
                        realm.copyToRealmOrUpdate(notificationMaster);
                    }
                });
            } finally {
                realm.close();
            }

            return 0;
        }

        protected void onPostExecute(Integer orderId) {

        }
    }




}
