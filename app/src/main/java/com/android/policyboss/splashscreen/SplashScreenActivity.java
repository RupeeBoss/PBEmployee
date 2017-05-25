package com.android.policyboss.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.navigationview.HomeActivity;

import io.realm.Realm;

public class SplashScreenActivity extends BaseActivity implements IResponseSubcriber {

    private static final String TAG = "SplashScreenActivity";
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        realm = Realm.getDefaultInstance();
        // new VarientMasterController(this, realm).getVarientMaster(new VarientMasterRequestEntity("24-03-2017", "04-05-2017"), this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
/*
                RealmResults<VarientEntity> r1 = realm.where(VarientEntity.class)
                        .distinct("Model_ID").sort("Make_Name");
                for (int i = 0; i < r1.size(); i++)
                    Log.d("SIze", "" + r1.get(i).getMake_Name() + "," + r1.get(i).getModel_Name());*/
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    @Override
    public void OnSuccess(APIResponse response, String message) {

    }

    @Override
    public void OnFailure(Throwable t) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed())
            realm.close();
    }
}
