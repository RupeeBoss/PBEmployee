package com.android.policyboss.carinsurance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.policyboss.R;
import com.android.policyboss.core.response.MotorQuotesResponse;
import com.android.policyboss.utility.Constants;

public class CarQuoteGenerate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_quote_generate);


        MotorQuotesResponse getQuoteResponse;

        if (getIntent().getParcelableExtra(Constants.MOTOR_QUOTE_DATA) != null){
            getQuoteResponse =getIntent().getParcelableExtra(Constants.MOTOR_QUOTE_DATA);

        }
    }
}
