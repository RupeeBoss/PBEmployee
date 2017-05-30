package com.android.policyboss.carinsurance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.controller.database.DatabaseController;

public class CarDetailsActivity extends BaseActivity {

    LinearLayout llWhenPolicyExpiring, llVarientDetails, llAdditionalDetails, llAdditionAcc, llNcb;
    AutoCompleteTextView autvCity;
    ArrayAdapter<String> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();
        loadAutoComplete();
    }

    private void init_widgets() {
        llWhenPolicyExpiring = (LinearLayout)findViewById(R.id.llWhenPolicyExpiring);
        llVarientDetails = (LinearLayout)findViewById(R.id.llVarientDetails);
        llAdditionalDetails = (LinearLayout)findViewById(R.id.llAdditionalDetails);
        llAdditionAcc = (LinearLayout)findViewById(R.id.llAdditionAcc);
        llNcb = (LinearLayout)findViewById(R.id.llNcb);
        autvCity = (AutoCompleteTextView)findViewById(R.id.autocomp_City);

    }

    private void loadAutoComplete() {
        cityAdapter = new ArrayAdapter<String>(CarDetailsActivity.this,
                android.R.layout.simple_spinner_item, new DatabaseController(this,realm).getCity());
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autvCity.setThreshold(1);//will start working from first character
        autvCity.setAdapter(cityAdapter);//setting the adapter data into the AutoCompleteTextView
//        actv.setTextColor(Color.RED);
    }

}
