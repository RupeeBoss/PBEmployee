package com.android.policyboss.quotegenerate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.policyboss.R;
import com.android.policyboss.core.response.MotorQuotesResponse;
import com.android.policyboss.utility.Constants;

public class QuoteGenerateActivity extends AppCompatActivity {

    MotorQuotesResponse getQuoteResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_generate);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (getIntent().getParcelableExtra(Constants.MOTOR_QUOTE_DATA) != null){
            getQuoteResponse =getIntent().getParcelableExtra(Constants.MOTOR_QUOTE_DATA);

        }

    /*    if(getIntent().getParcelableArrayExtra(Constants.MOTOR_QUOTE_DATA) !=null) {
            getQuoteResponse = getIntent().getParcelableExtra(Constants.MOTOR_QUOTE_DATA);
        }*/
    }
}
