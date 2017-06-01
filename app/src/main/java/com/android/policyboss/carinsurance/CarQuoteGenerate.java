package com.android.policyboss.carinsurance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.models.MototrQuotesEntity;
import com.android.policyboss.core.response.MotorQuotesResponse;
import com.android.policyboss.utility.Constants;

public class CarQuoteGenerate extends BaseActivity {

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

        if (getIntent().getParcelableExtra(Constants.MOTOR_QUOTE_DATA) != null){
            getQuoteResponse =getIntent().getParcelableExtra(Constants.MOTOR_QUOTE_DATA);
            mAdapter = new CarQuotesAdapter(CarQuoteGenerate.this, getQuoteResponse.getMototrQuotes());
            rvQuotes.setAdapter(mAdapter);
        }
    }
    private void initialise_widget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvQuotes = (RecyclerView) findViewById(R.id.rvQuotes);
        rvQuotes.setLayoutManager(new LinearLayoutManager(CarQuoteGenerate.this));
//        rvQuotes.addItemDecoration(
//                new DividerItemDecoration(ContextCompat.getDrawable(QuoteActivity.this,
//                        R.drawable.item_decor)));
    }

    public void redirectToApplyLoan(MototrQuotesEntity entity) {
//
//        new QuoteController(this).sendSelectedQuoteInfo(String.valueOf(getQuoteResponse.getQuote_id()),
//                String.valueOf(entity.getBank_Id()),
//                entity.getRoi_type(),
//                String.valueOf(entity.getLoan_eligible()),
//                String.valueOf(entity.getProcessingfee()));

//        startActivity(new Intent(this, HomeLoanApplyActivity.class)
//                .putExtra("QUOTE_ENTITY", entity)
//                .putExtra("URL", getQuoteResponse.getUrl())
//                .putExtra("QUOTE_ID", getQuoteResponse.getQuote_id())
//        );
    }

    public void redirectToPopUpPremium(MototrQuotesEntity entity) {
        startActivity(new Intent(this, PremiumPopUpActivity.class)
                .putExtra(Constants.QUOTE_ENTITY, entity));


    }

    public void redirectToPopUpCard(MototrQuotesEntity entity) {

        startActivity(new Intent(this, PopUpCardQuoteActivity.class));
    }


}
