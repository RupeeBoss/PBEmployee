package com.android.policyboss.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.variant.VarientMasterController;
import com.android.policyboss.core.response.AllMastersResponse;
import com.android.policyboss.facade.LoginFacade;
import com.android.policyboss.login.LoginActivity;
import com.android.policyboss.navigationview.HomeActivity;
import com.android.policyboss.utility.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

public class SplashScreenActivity extends BaseActivity implements IResponseSubcriber {

    private static final String TAG = "SplashScreenActivity";
    private static final int SPLASH_DISPLAY_LENGTH = 3000;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        realm = Realm.getDefaultInstance();

        editor = Constants.getSharedPreferenceEditor(this);

        // boolean isYesterday = LoginFacade.getDayDifference(Long.parseLong("1497332094000"));
        //fetch all master tables
        if (Constants.getSharedPreference(this).getBoolean(Constants.SHARED_PREF_ALL_MASTER, false)) {
            new VarientMasterController(this, realm).getAllMasters(this);
            new VarientMasterController(this, realm).getAllCityMasters(this);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //checkForUser exist
                    finish();
                    if (new LoginFacade(SplashScreenActivity.this).getUser() != null)
                        startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    else
                        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }
            }, SPLASH_DISPLAY_LENGTH);
        }

    }


    @Override
    public void OnSuccess(APIResponse response, String message) {

        if (response instanceof AllMastersResponse) {
            if (response.getStatusNo() == 0) {

                finish();
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));

            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed())
            realm.close();
    }
}
