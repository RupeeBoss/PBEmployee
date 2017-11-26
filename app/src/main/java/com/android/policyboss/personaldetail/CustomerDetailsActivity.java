package com.android.policyboss.personaldetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.bikeinsurance.BikeInsuranceActivity;
import com.android.policyboss.bikeinsurance.BikeQuoteActivity;
import com.android.policyboss.carinsurance.CarQuoteGenerate;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.core.controller.car.CarController;
import com.android.policyboss.core.controller.healthquote.HealthQuoteController;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.requestEntity.HealthRequestEntity;
import com.android.policyboss.core.response.BikeUniqueResponse;
import com.android.policyboss.core.response.HealthQuoteResponse;
import com.android.policyboss.core.response.MotorQuotesResponse;
import com.android.policyboss.healthinsurance.HealthInsuranceAgeDetailActivity;
import com.android.policyboss.healthinsurance.HealthInsuranceQuotes;
import com.android.policyboss.utility.Constants;

import static com.android.policyboss.carinsurance.CarDetailsActivity.CAR_DETAIL;

public class CustomerDetailsActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText etCustomerName, etCustomerEmail, etCustomerMobile;
    Button btnGetQuote;
    BikeRequestEntity carRequestEntity;
    String fromWhichClass = "";
    HealthRequestEntity healthRequestEntity;
    BikeRequestEntity bikeRequestEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();

        if (getIntent().getParcelableExtra(CAR_DETAIL) != null) {
            carRequestEntity = (BikeRequestEntity) getIntent().getParcelableExtra(CAR_DETAIL);
            fromWhichClass = CAR_DETAIL;
            getSupportActionBar().setTitle("CAR QUOTES");
        } else if (getIntent().getParcelableExtra(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE) != null) {
            healthRequestEntity = (HealthRequestEntity) getIntent().getParcelableExtra(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE);
            fromWhichClass = HealthInsuranceAgeDetailActivity.HEALTH_QUOTE;
            getSupportActionBar().setTitle("HEALTH QUOTES");
        } else if (getIntent().getParcelableExtra(BikeInsuranceActivity.BIKE_INSURENCE) != null) {
            bikeRequestEntity = (BikeRequestEntity) getIntent().getParcelableExtra(BikeInsuranceActivity.BIKE_INSURENCE);
            fromWhichClass = BikeInsuranceActivity.BIKE_INSURENCE;
            getSupportActionBar().setTitle("BIKE QUOTES");
        }

    }

    private void init_widgets() {
        etCustomerName = (EditText) findViewById(R.id.etCustomerName);
        etCustomerEmail = (EditText) findViewById(R.id.etCustomerEmail);
        etCustomerMobile = (EditText) findViewById(R.id.etCustomerMobile);
        btnGetQuote = (Button) findViewById(R.id.btnGetQuote);
        btnGetQuote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnGetQuote) {
            //validation
            if (etCustomerName.getText().toString().equals("")) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValideEmailID(etCustomerEmail)) {
                Toast.makeText(this, "Invalid Email ID", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidePhoneNumber(etCustomerMobile)) {
                Toast.makeText(this, "Invalid mobile number", Toast.LENGTH_SHORT).show();
                return;
            }


            //server hit and redirect to quote
            if (fromWhichClass.equals(CAR_DETAIL)) {
                //motor
                String[] fullName = etCustomerName.getText().toString().split(" ");

                if (fullName.length == 1) {
                    carRequestEntity.setFirst_name(fullName[0]);
                } else if (fullName.length == 2) {
                    carRequestEntity.setFirst_name(fullName[0]);
                    carRequestEntity.setLast_name(fullName[1]);

                } else if (fullName.length == 3) {
                    carRequestEntity.setFirst_name(fullName[0]);
                    carRequestEntity.setMiddle_name(fullName[1]);
                    carRequestEntity.setLast_name(fullName[2]);
                }
                carRequestEntity.setMobile(etCustomerMobile.getText().toString());
                carRequestEntity.setEmail(etCustomerEmail.getText().toString());
                showDialog();
                //new MotorQuoteController(this).getQuoteDetails(entity, this);
                new CarController(this).getCarQuote(carRequestEntity, this);

            } else if (fromWhichClass.equals(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE)) {
                //health
                healthRequestEntity.setContactEmail(etCustomerEmail.getText().toString());
                healthRequestEntity.setContactMobile(etCustomerMobile.getText().toString());
                healthRequestEntity.setContactName(etCustomerName.getText().toString());
                showDialog();
                new HealthQuoteController(this).getHealthQuotes(healthRequestEntity, this);
            } else if (fromWhichClass.equals(BikeInsuranceActivity.BIKE_INSURENCE)) {
                //bike

                String[] fullName = etCustomerName.getText().toString().split(" ");

                if (fullName.length == 1) {
                    bikeRequestEntity.setFirst_name(fullName[0]);
                } else if (fullName.length == 2) {
                    bikeRequestEntity.setFirst_name(fullName[0]);
                    bikeRequestEntity.setLast_name(fullName[1]);

                } else if (fullName.length == 3) {
                    bikeRequestEntity.setFirst_name(fullName[0]);
                    bikeRequestEntity.setMiddle_name(fullName[1]);
                    bikeRequestEntity.setLast_name(fullName[2]);
                }
                bikeRequestEntity.setMobile(etCustomerMobile.getText().toString());
                bikeRequestEntity.setEmail(etCustomerEmail.getText().toString());
                showDialog();
                new BikeController(this).getBikeQuote(bikeRequestEntity, this);
            }
        }
    }


    //region Quote response

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof MotorQuotesResponse) {
            if (response.getStatusNo() == 0) {
                startActivity(new Intent(this, CarQuoteGenerate.class)
                        .putExtra(Constants.MOTOR_QUOTE_DATA, (MotorQuotesResponse) response));
            }
        } else if (response instanceof HealthQuoteResponse) {

            if (((HealthQuoteResponse) response).getHealthQuotes() != null) {
                startActivity(new Intent(this, HealthInsuranceQuotes.class)
                        .putExtra(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE, (HealthQuoteResponse) response));
            }
        } else if (response instanceof BikeUniqueResponse) {
            if (fromWhichClass.equals(CAR_DETAIL)) {
                startActivity(new Intent(this, BikeQuoteActivity.class)
                        .putExtra("CAR", carRequestEntity));
            } else {
                startActivity(new Intent(this, BikeQuoteActivity.class)
                        .putExtra("BIKE", bikeRequestEntity));
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    //endregion
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}