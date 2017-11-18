package com.android.policyboss.bikeinsurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.core.controller.car.CarController;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.response.BikeUniqueResponse;

public class ModifyQuotesActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener {
    Button btnReCal;
    EditText etIdv, etElec, etNonElec;
    TextView tvSecMore;
    BikeRequestEntity bikeRequestEntity, carRequestEntity;
    LinearLayout llUnNamed, llLrgalLiability, llNamed, llUnPaidDriverCover;
    Spinner spVoluntaryAccessAmt, spUnNamedPaCover, spNamedPaCover;
    RadioGroup rgAntiTheft, rgllPd, rgpda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        init();
        setListener();
        if (getIntent().hasExtra("BIKE")) {
            bikeRequestEntity = getIntent().getParcelableExtra("BIKE");
            setBikeSpinnerAdapter();
            hideLayouts();
        } else if (getIntent().hasExtra("CAR")) {
            carRequestEntity = getIntent().getParcelableExtra("CAR");
            getSupportActionBar().setTitle("CAR INSURANCE");
            setCarSpinnerAdapter();
        }

    }

    private void hideLayouts() {
        tvSecMore.setVisibility(View.GONE);
        llUnNamed.setVisibility(View.GONE);
        llLrgalLiability.setVisibility(View.GONE);
        llNamed.setVisibility(View.GONE);
        llUnPaidDriverCover.setVisibility(View.GONE);
    }

    private void setCarSpinnerAdapter() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.car_voluntary_excess_amount)); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spVoluntaryAccessAmt.setAdapter(spinnerArrayAdapter);
    }

    private void setBikeSpinnerAdapter() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.bike_voluntary_excess_amount)); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spVoluntaryAccessAmt.setAdapter(spinnerArrayAdapter);
    }

    private void setListener() {
        btnReCal.setOnClickListener(this);
    }

    private void init() {
        btnReCal = (Button) findViewById(R.id.btnReCal);
        rgAntiTheft = (RadioGroup) findViewById(R.id.rgAntiTheft);
        rgllPd = (RadioGroup) findViewById(R.id.rgllPd);
        rgpda = (RadioGroup) findViewById(R.id.rgpda);

        etNonElec = (EditText) findViewById(R.id.etNonElec);
        etElec = (EditText) findViewById(R.id.etElec);
        etIdv = (EditText) findViewById(R.id.etIdv);
        spVoluntaryAccessAmt = (Spinner) findViewById(R.id.spVoluntaryAccessAmt);
        spUnNamedPaCover = (Spinner) findViewById(R.id.spUnNamedPaCover);
        spNamedPaCover = (Spinner) findViewById(R.id.spNamedPaCover);
        tvSecMore = (TextView) findViewById(R.id.tvSecMore);
        llUnNamed = (LinearLayout) findViewById(R.id.llUnNamed);
        llLrgalLiability = (LinearLayout) findViewById(R.id.llLrgalLiability);
        llNamed = (LinearLayout) findViewById(R.id.llNamed);
        llUnPaidDriverCover = (LinearLayout) findViewById(R.id.llUnPaidDriverCover);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReCal:

                RadioButton rbanti = (RadioButton) findViewById(rgAntiTheft.getCheckedRadioButtonId());

                if (getIntent().hasExtra("BIKE")) {

                    bikeRequestEntity.setIs_antitheft_fit(rbanti.getText().toString().toLowerCase());

                    if (spVoluntaryAccessAmt.getSelectedItemPosition() != 0)
                        bikeRequestEntity.setVoluntary_deductible(Integer.parseInt(spVoluntaryAccessAmt.getSelectedItem().toString()));

                    if (!etElec.getText().toString().equals(""))
                        bikeRequestEntity.setElectrical_accessory("" + etElec.getText().toString());

                    if (!etNonElec.getText().toString().equals(""))
                        bikeRequestEntity.setNon_electrical_accessory("" + etNonElec.getText().toString());

                    if (!etIdv.getText().toString().equals(""))
                        bikeRequestEntity.setVehicle_expected_idv(Integer.parseInt(etIdv.getText().toString()));
                    showDialog();
                    new BikeController(this).getBikeQuote(bikeRequestEntity, ModifyQuotesActivity.this);


                } else if (getIntent().hasExtra("CAR")) {
                    RadioButton rbllpd = (RadioButton) findViewById(rgllPd.getCheckedRadioButtonId());
                    RadioButton rbpda = (RadioButton) findViewById(rgpda.getCheckedRadioButtonId());

                    carRequestEntity.setIs_antitheft_fit(rbanti.getText().toString().toLowerCase());
                    if (spVoluntaryAccessAmt.getSelectedItemPosition() != 0)
                        carRequestEntity.setVoluntary_deductible(Integer.parseInt(spVoluntaryAccessAmt.getSelectedItem().toString()));
                    if (spUnNamedPaCover.getSelectedItemPosition() != 0)
                        carRequestEntity.setVoluntary_deductible(Integer.parseInt(spUnNamedPaCover.getSelectedItem().toString()));
                    carRequestEntity.setIs_antitheft_fit(rbllpd.getText().toString().toLowerCase());
                    if (spNamedPaCover.getSelectedItemPosition() != 0)
                        carRequestEntity.setVoluntary_deductible(Integer.parseInt(spNamedPaCover.getSelectedItem().toString()));
                    carRequestEntity.setIs_antitheft_fit(rbpda.getText().toString().toLowerCase());
                    if (!etElec.getText().toString().equals(""))
                        carRequestEntity.setElectrical_accessory("" + etElec.getText().toString());

                    if (!etNonElec.getText().toString().equals(""))
                        carRequestEntity.setNon_electrical_accessory("" + etNonElec.getText().toString());

                    if (!etIdv.getText().toString().equals(""))
                        carRequestEntity.setVehicle_expected_idv(Integer.parseInt(etIdv.getText().toString()));
                    showDialog();
                    new CarController(this).getCarQuote(carRequestEntity, ModifyQuotesActivity.this);
                }
                break;
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof BikeUniqueResponse) {
            if (getIntent().hasExtra("BIKE")) {
                startActivity(new Intent(this, BikeQuoteActivity.class)
                        .putExtra("BIKE", bikeRequestEntity));
            } else if (getIntent().hasExtra("CAR")) {
                startActivity(new Intent(this, BikeQuoteActivity.class)
                        .putExtra("BIKE", carRequestEntity));
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {

    }
}
