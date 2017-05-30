package com.android.policyboss.carinsurance;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.utility.Constants;

public class FastLaneCarDetails extends BaseActivity {

    QuoteRequestEntity quoteRequestEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_lane_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quoteRequestEntity = getIntent().getParcelableExtra(Constants.QUOTE);

    }

}
