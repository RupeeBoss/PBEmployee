package com.android.policyboss.personaldetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.bikeinsurance.BikeInsuranceActivity;
import com.android.policyboss.bikeinsurance.BikeQuoteActivity;
import com.android.policyboss.carinsurance.CarDetailsActivity;
import com.android.policyboss.carinsurance.CarQuoteGenerate;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.core.controller.healthquote.HealthQuoteController;
import com.android.policyboss.core.controller.motorquote.MotorQuoteController;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.requestEntity.HealthRequestEntity;
import com.android.policyboss.core.response.BikeUniqueResponse;
import com.android.policyboss.core.response.HealthQuoteResponse;
import com.android.policyboss.core.response.MotorQuotesResponse;
import com.android.policyboss.healthinsurance.HealthInsuranceAgeDetailActivity;
import com.android.policyboss.healthinsurance.HealthQuoteActivity;
import com.android.policyboss.utility.Constants;

public class CustomerDetailsActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText etCustomerName, etCustomerEmail, etCustomerMobile;
    Button btnGetQuote;
    QuoteRequestEntity entity;
    String fromWhichClass = "";
    HealthRequestEntity healthRequestEntity;
    BikeRequestEntity bikeRequestEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init_widgets();

        if (getIntent().getParcelableExtra(CarDetailsActivity.CAR_DETAIL) != null) {
            entity = (QuoteRequestEntity) getIntent().getParcelableExtra(CarDetailsActivity.CAR_DETAIL);
            fromWhichClass = CarDetailsActivity.CAR_DETAIL;
        } else if (getIntent().getParcelableExtra(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE) != null) {
            healthRequestEntity = (HealthRequestEntity) getIntent().getParcelableExtra(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE);
            fromWhichClass = HealthInsuranceAgeDetailActivity.HEALTH_QUOTE;
        } else if (getIntent().getParcelableExtra(BikeInsuranceActivity.BIKE_INSURENCE) != null) {
            bikeRequestEntity = (BikeRequestEntity) getIntent().getParcelableExtra(BikeInsuranceActivity.BIKE_INSURENCE);
            fromWhichClass = BikeInsuranceActivity.BIKE_INSURENCE;
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
            if (fromWhichClass.equals(CarDetailsActivity.CAR_DETAIL)) {
                //motor
                entity.setContactEmail(etCustomerEmail.getText().toString());
                entity.setContactMobile(etCustomerMobile.getText().toString());
                entity.setContactName(etCustomerName.getText().toString());
                showDialog();
                new MotorQuoteController(this).getQuoteDetails(entity, this);

            } else if (fromWhichClass.equals(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE)) {
                //health
                healthRequestEntity.setContactEmail(etCustomerEmail.getText().toString());
                healthRequestEntity.setContactMobile(etCustomerMobile.getText().toString());
                healthRequestEntity.setContactName(etCustomerName.getText().toString());
                showDialog();
                new HealthQuoteController(this).getHealthQuotes(healthRequestEntity, this);
            } else if (fromWhichClass.equals(BikeInsuranceActivity.BIKE_INSURENCE)) {
                //bike
                bikeRequestEntity.setFirst_name(etCustomerName.getText().toString());
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
                startActivity(new Intent(this, HealthQuoteActivity.class)
                        .putExtra(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE, (HealthQuoteResponse) response));
            }
        } else if (response instanceof BikeUniqueResponse) {
            startActivity(new Intent(this, BikeQuoteActivity.class));
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    //endregion

}
