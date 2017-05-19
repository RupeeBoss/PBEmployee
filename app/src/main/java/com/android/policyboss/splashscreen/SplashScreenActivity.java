package com.android.policyboss.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.navigationview.HomeActivity;

public class SplashScreenActivity extends BaseActivity {

    private static final String TAG = "SplashScreenActivity";
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
