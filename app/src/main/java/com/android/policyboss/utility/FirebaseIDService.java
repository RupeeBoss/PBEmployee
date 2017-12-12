package com.android.policyboss.utility;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by IN-RB on 21-11-2017.
 */

public class FirebaseIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIDService";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onTokenRefresh() {
        // super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);
    }

    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.


        sharedPreferences = getSharedPreferences("CALLER_AGENT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Constants.DEVICE_TOKEN, token);
        editor.commit();

        Log.d("TOKEN", token);

    }
}
