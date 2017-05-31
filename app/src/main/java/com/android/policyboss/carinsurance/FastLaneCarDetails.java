package com.android.policyboss.carinsurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.utility.Constants;

public class FastLaneCarDetails extends BaseActivity implements View.OnClickListener {
    Button btnCont;
    QuoteRequestEntity quoteRequestEntity;
    FastLaneResponse.FLResponseBean fastLaneResponseEntity;

    EditText etVehicleDetails, etFueltype, etRTO, etManufactYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_lane_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quoteRequestEntity = getIntent().getParcelableExtra(Constants.QUOTE);
        fastLaneResponseEntity = getIntent().getParcelableExtra(CarInsuranceActivity.FASTLANE_DATA);

        init();
        setListener();

        if (fastLaneResponseEntity != null) {
            bindVehicleDetails(fastLaneResponseEntity);
        }


    }

    private void bindVehicleDetails(FastLaneResponse.FLResponseBean entity) {
        etVehicleDetails.setText(entity.getMake_Name() + "," + entity.getModel_Name() + "," + entity.getVariant_Name());
        etFueltype.setText(entity.getFuel_Type());
        etRTO.setText(entity.getRTO_Name());
        etManufactYear.setText(entity.getManufacture_Year());
    }

    private void setListener() {
        btnCont.setOnClickListener(this);
    }

    private void init() {
        btnCont = (Button) findViewById(R.id.btnCont);
        etVehicleDetails = (EditText) findViewById(R.id.etVehicleDetails);
        etFueltype = (EditText) findViewById(R.id.etFueltype);
        etRTO = (EditText) findViewById(R.id.etRTO);
        etManufactYear = (EditText) findViewById(R.id.etManufactYear);
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
