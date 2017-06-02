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
    public static final String QUOTE = "quote";
    public static final String SHARED_PREFERENCE_POLICYBOSS = "shared_policyboss";
    public static final String SHARED_PREF_ALL_MASTER = "shared_policyboss";

    public static final String MOTOR_QUOTE_DATA = "motor_quote_data";
    public static final String QUOTE_ENTITY = "quote_entity";


    public static List<String> getPastFifteenYear() {
        ArrayList<String> arrayListYear = new ArrayList<>();
        int year, startYear, endYear;

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        startYear = year;
        endYear = startYear - 15;

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
