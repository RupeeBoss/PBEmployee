package com.android.policyboss.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.variant.VarientMasterController;
import com.android.policyboss.core.response.AllMastersResponse;
import com.android.policyboss.navigationview.HomeActivity;

import io.realm.Realm;

public class SplashScreenActivity extends BaseActivity implements IResponseSubcriber {

    private static final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        realm = Realm.getDefaultInstance();

        //fetch all master tables
        new VarientMasterController(this, realm).getAllMasters(this);
        new VarientMasterController(this, realm).getAllCityMasters(this);

    }


    @Override
    public void OnSuccess(APIResponse response, String message) {

        if (response instanceof AllMastersResponse) {
            if (response.getStatusNo() == 0) {
                finish();
                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));

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
