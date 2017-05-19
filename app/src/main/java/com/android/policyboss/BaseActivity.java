package com.android.policyboss;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Nilesh Birhade on 18-05-2017.
 */

public class BaseActivity extends AppCompatActivity {

    ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void cancelDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void showDialog() {
        dialog = ProgressDialog.show(this, "", "Loading...", true);
    }

}
