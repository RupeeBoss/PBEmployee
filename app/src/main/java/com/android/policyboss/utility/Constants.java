package com.android.policyboss.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Rajeev Ranjan on 30/05/2017.
 */

public class Constants {
    public static final String EXTERNAL_LPG = "External Fitted LPG";
    public static final String EXTERNAL_CNG = "External Fitted CNG";
    public static final String FASTLANE_DATA = "fastlane_response";
    public static final String BIKE = "bikeinsurance";
    public static final String CAR = "carinsurance";
    public static final String QUOTE = "quote";
    public static final String BIKEQUOTE_UNIQUEID = "bike_quote_uniqueid";
    public static final String CARQUOTE_UNIQUEID = "car_quote_uniqueid";
    public static final String SHARED_PREFERENCE_POLICYBOSS = "shared_policyboss";
    public static final String SHARED_PREF_ALL_MASTER = "shared__all_master";

    public static final String MOTOR_QUOTE_DATA = "motor_quote_data";
    public static final String QUOTE_ENTITY = "quote_entity";
    public static final String Bike_QUOTE_PRIMIUM = "Bike_quote_primium";
    public static final String Bike_QUOTE_INSURER = "Bike_quote_insurer";
    public static final String Bike_Summary_ENTITY = "Bike_Summary_entity";
    public static final String IDV_DATA = "idv_dta";

    public static final String USER_CREDENTIAL = "login_user";
    public static final String USER_AUTO_LOGOFF = "auto_logoff";

    public static final String SECRET_KEY = "SECRET-ODARQ6JP-9V2Q-7BIM-0NNM-DNRTXRWMRTAL";
    public static final String CLIENT_KEY = "CLIENT-GLF2SRA5-CFIF-4X2T-HC1Z-CXV4ZWQTFQ3T";
    public static final String VERSION_CODE = "2.0";

    public static final String QUOTE_COUNTER = "quote_counter";
    public static String DEVICE_TOKEN = "devicetoken";

    public static List<String> getPastFifteenYear() {
        ArrayList<String> arrayListYear = new ArrayList<>();
        int year, startYear, endYear;

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        startYear = year;
        endYear = startYear - 15;
        arrayListYear.add("Select Manufacture year");
        for (int i = startYear; i >= endYear; i--) {
            arrayListYear.add("" + i);
        }

        return arrayListYear;
    }


    public static void hideKeyBoard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCE_POLICYBOSS, MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getSharedPreferenceEditor(Context context) {
        return getSharedPreference(context).edit();
    }
}
