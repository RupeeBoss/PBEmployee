package com.android.policyboss.carinsurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.android.policyboss.R;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.utility.Constants;

public class CarDetailsActivity extends AppCompatActivity {

    LinearLayout llWhenPolicyExpiring, llVarientDetails, llAdditionalDetails, llAdditionAcc, llNcb;
    QuoteRequestEntity quoteRequestEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quoteRequestEntity = getIntent().getParcelableExtra(Constants.QUOTE);
        init_widgets();
        setListeners();
        showOrHideLayout();

    }

    private void showOrHideLayout() {
        if (quoteRequestEntity.isNew()) {
            llVarientDetails.setVisibility(View.VISIBLE);
        } else if (quoteRequestEntity.isRenew()) {
            llWhenPolicyExpiring.setVisibility(View.VISIBLE);
        }
    }

    private void setListeners() {
    }

    private void init_widgets() {
        llWhenPolicyExpiring = (LinearLayout) findViewById(R.id.llWhenPolicyExpiring);
        llVarientDetails = (LinearLayout) findViewById(R.id.llVarientDetails);
        llAdditionalDetails = (LinearLayout) findViewById(R.id.llAdditionalDetails);
        llAdditionAcc = (LinearLayout) findViewById(R.id.llAdditionAcc);
        llNcb = (LinearLayout) findViewById(R.id.llNcb);
    }


}