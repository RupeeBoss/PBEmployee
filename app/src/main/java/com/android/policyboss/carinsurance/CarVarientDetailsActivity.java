package com.android.policyboss.carinsurance;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;

public class CarVarientDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_varient_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
