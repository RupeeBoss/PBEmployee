package com.android.policyboss.facade;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.policyboss.core.models.UserEntity;
import com.android.policyboss.utility.Constants;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nilesh Birhade on 12-06-2017.
 */

public class LoginFacade {
    public static final String TAG = "LoginFacade";
    Context mContext;

    public LoginFacade(Context context) {
        mContext = context;
    }

    public void storeUserCredentials(UserEntity entity) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(Constants.SHARED_PREFERENCE_POLICYBOSS, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(Constants.USER_CREDENTIAL, gson.toJson(entity));
        //store user current hour to log off before working hour
        editor.putLong(Constants.USER_AUTO_LOGOFF, Calendar.getInstance().getTimeInMillis());

        editor.putBoolean(Constants.SHARED_PREF_ALL_MASTER, entity.isIsMasterUpdate());

        editor.commit();
    }

    public UserEntity getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(Constants.SHARED_PREFERENCE_POLICYBOSS, mContext.MODE_PRIVATE);

        long StoredDate = sharedPreferences.getLong(Constants.USER_AUTO_LOGOFF, 0);

        //if true then logoff else login
        if (!getDayDifference(StoredDate)) {
            String user = sharedPreferences.getString(Constants.USER_CREDENTIAL, "");
            if (!user.matches("")) {
                Gson gson = new Gson();
                UserEntity entity = gson.fromJson(user, UserEntity.class);
                Log.d(TAG, "get User");
                return entity;
            }
        }


        return null;

    }

    public static boolean getDayDifference(long storedDate) {
        try {

            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");

            Date storeDate = dates.parse(dates.format(new Date(storedDate)));
            Date currentDate = new Date();
            //Comparing dates
            long difference = Math.abs(currentDate.getTime() - storeDate.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            if (differenceDates > 0)
                return true;
            else
                return false;

        } catch (Exception exception) {

        }
        return false;
    }
}
