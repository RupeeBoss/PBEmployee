package com.android.policyboss.carinsurance;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;

public class RenewalCarActivity extends BaseActivity {

    EditText etRegistrationNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewal_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init() {
        etRegistrationNo = (EditText) findViewById(R.id.etRegistrationNo);
    }


}
