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
import com.android.policyboss.core.controller.fetchmaster.MasterController;
import com.android.policyboss.core.controller.variant.VarientMasterController;
import com.android.policyboss.core.models.MasterDataEntity;
import com.android.policyboss.core.response.AllMastersResponse;
import com.android.policyboss.core.response.CarMasterResponse;
import com.android.policyboss.facade.LoginFacade;
import com.android.policyboss.login.LoginActivity;
import com.android.policyboss.navigationview.HomeActivity;
import com.android.policyboss.utility.Constants;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import io.realm.Realm;

public class SplashScreenActivity extends BaseActivity implements IResponseSubcriber {

    private static final String TAG = "SplashScreenActivity";
    private static final int SPLASH_DISPLAY_LENGTH = 2000;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        realm = Realm.getDefaultInstance();

        editor = Constants.getSharedPreferenceEditor(this);
        subscribeToPushService();

        boolean isYesterday = LoginFacade.getDayDifference(Long.parseLong("1497332094000"));


        //fetch all master tables
        if (Constants.getSharedPreference(this).getBoolean(Constants.SHARED_PREF_ALL_MASTER, true)) {

            // new implemented masters
            new MasterController(this).getCarMaster(this);
            new MasterController(this).getBikeMaster(this);
            new VarientMasterController(this, realm).getAllCityMasters(this);
            //delete after dependency removed
            //new VarientMasterController(this, realm).getAllMasters(this);


            //Delete after login working
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_POLICYBOSS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.SHARED_PREF_ALL_MASTER, false).commit();

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

                //  startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));

            }
        }

        if (response instanceof CarMasterResponse) {

            if (((CarMasterResponse) response).getMasterData() != null) {
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

    private void subscribeToPushService() {

        try {
            if (FirebaseMessaging.getInstance() != null) {
                FirebaseMessaging.getInstance().subscribeToTopic("policyBoss2");
            }
        } catch (Exception ex) {
            Log.d("FCM ERROR", ex.getMessage().toString());
        }

    }
}
