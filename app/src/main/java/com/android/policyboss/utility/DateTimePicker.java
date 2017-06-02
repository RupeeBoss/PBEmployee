package com.android.policyboss.utility;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.util.Calendar;

/**
 * Created by Rohit on 26/01/16.
 */
public class DateTimePicker {



    public static void showDataPickerDialog(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1);

        //always shows 1 day ahead in calender
        // added 1 day in calender
       // calendar.add(Calendar.MONTH, -6);

        // disable all before date,

        //dialog.getDatePicker().setMinDate(calendar.getTimeInMillis() + 180 * 24 * 60 * 60 * 1000);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        dialog.show();
    }

    public static void showYearPickerDialog(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR),0,0);

        //always shows 1 day ahead in calender
        // added 1 day in calender
        //calendar.add(Calendar.MONTH, -6);

        // disable all before date,

        //dialog.getDatePicker().setMinDate(calendar.getTimeInMillis() + 180 * 24 * 60 * 60 * 1000);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        dialog.show();
    }

    public static void showTimePickerDialog(Context mContex, TimePickerDialog.OnTimeSetListener callBack) {
        final Calendar c = Calendar.getInstance();
        // Current Hour

        TimePickerDialog timePickerDialog = new TimePickerDialog(mContex, callBack,
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.HOUR),
                true);

        timePickerDialog.show();
    }

}
