package com.android.policyboss.carinsurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.models.FastLaneResponseEntity;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.utility.Constants;

public class FastLaneCarDetails extends BaseActivity implements View.OnClickListener {
    Button btnCont;
    QuoteRequestEntity quoteRequestEntity;
    FastLaneResponseEntity fastLaneResponseEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_lane_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quoteRequestEntity = getIntent().getParcelableExtra(Constants.QUOTE);
        fastLaneResponseEntity = getIntent().getParcelableExtra("FASTLANE");
        init();
        setListener();

    }

    private void setListener() {
        btnCont.setOnClickListener(this);
    }

    private void init() {
        btnCont = (Button) findViewById(R.id.btnCont);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCont:
                quoteRequestEntity.setNew(false);
                quoteRequestEntity.setRenew(true);
                quoteRequestEntity.setDontRem(false);
                startActivity(new Intent(FastLaneCarDetails.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                break;
        }
    }
}
