package com.android.policyboss.carinsurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.android.policyboss.R;

public class CarDetailsActivity extends AppCompatActivity {

    LinearLayout llWhenPolicyExpiring, llVarientDetails, llAdditionalDetails, llAdditionAcc, llNcb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();
    }

    private void init_widgets() {
        llWhenPolicyExpiring = (LinearLayout)findViewById(R.id.llWhenPolicyExpiring);
        llVarientDetails = (LinearLayout)findViewById(R.id.llVarientDetails);
        llAdditionalDetails = (LinearLayout)findViewById(R.id.llAdditionalDetails);
        llAdditionAcc = (LinearLayout)findViewById(R.id.llAdditionAcc);
        llNcb = (LinearLayout)findViewById(R.id.llNcb);
    }

}
