package com.android.policyboss.motorinsurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.models.MototrQuotesEntity;
import com.android.policyboss.core.response.MotorQuotesResponse;
import com.android.policyboss.utility.Constants;
import com.android.policyboss.webview.WebViewBuyInsurenceActivity;

public class CarQuoteGenerate extends BaseActivity {

    public static final String CAR_BUYNOW = "car_buy_now";
    MotorQuotesResponse getQuoteResponse;
    Toolbar toolbar;
    RecyclerView rvQuotes;
    CarQuotesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_quote_generate);
        initialise_widget();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (getIntent().getParcelableExtra(Constants.MOTOR_QUOTE_DATA) != null) {
            getQuoteResponse = getIntent().getParcelableExtra(Constants.MOTOR_QUOTE_DATA);
            mAdapter = new CarQuotesAdapter(CarQuoteGenerate.this, getQuoteResponse.getMototrQuotes());
            rvQuotes.setAdapter(mAdapter);
        }
    }

    private void initialise_widget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvQuotes = (RecyclerView) findViewById(R.id.rvQuotes);
        rvQuotes.setLayoutManager(new LinearLayoutManager(CarQuoteGenerate.this));

    }

    public void redirectToApplyLoan(MototrQuotesEntity entity) {
        Intent intent = new Intent(this, WebViewBuyInsurenceActivity.class);
        intent.putExtra(CAR_BUYNOW, entity.getProposerPageUrl());
        startActivity(intent);
    }

    public void redirectToPopUpPremium(MototrQuotesEntity entity) {
        startActivity(new Intent(this, PremiumPopUpActivity.class)
                .putExtra(Constants.QUOTE_ENTITY, entity));


    }

    public void redirectToPopUpCard(MototrQuotesEntity entity) {

        startActivity(new Intent(this, PopUpCardQuoteActivity.class));
    }


}
