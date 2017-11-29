package com.android.policyboss.motorinsurance;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.controller.fastlane.FastlaneController;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.utility.Constants;

import java.util.List;

import io.realm.Realm;

public class MotorInsuranceActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    String insuranceType;
    ImageView backdrop;
    AutoCompleteTextView autoCarMakeModel;
    EditText etRenewRegNo, etExternallyFitted;
    Button btnGEtDetails, btnCont;
    TextView tvDontRem, tvNew, tvCvHeader;
    Spinner spCarFuelType, spCarVarient;
    CardView cvMotorDetails;
    TextInputLayout tilExternal;
    List<String> makeModelList, fuelList, variantList;
    ArrayAdapter<String> makeModelAdapter, varientAdapter, fuelAdapter;
    DatabaseController databaseController;
    LinearLayout llVarient;
    int modelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_insurance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        insuranceType = getInsuranceType();

        initView();

        realm = Realm.getDefaultInstance();
        databaseController = new DatabaseController(this, realm);
        changeTextLabels();

        initializeList();

        setListeners();


    }

    private void initializeList() {
        if (insuranceType.equals("CAR")) {
            makeModelList = databaseController.getCarMakeModel();
            makeModelAdapter = new
                    ArrayAdapter(MotorInsuranceActivity.this, android.R.layout.simple_list_item_1, makeModelList);
            autoCarMakeModel.setAdapter(makeModelAdapter);


            // region  Auto Complete car make
            autoCarMakeModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    hideKeyBoard(backdrop, MotorInsuranceActivity.this);
                    modelId = databaseController.getModelID(getModel(autoCarMakeModel.getText().toString()));

                    fuelList = databaseController.getFuelType(modelId);
                    variantList = databaseController.getVariantbyModelID(modelId);

                    spCarFuelType.setVisibility(View.VISIBLE);
                    spCarVarient.setVisibility(View.VISIBLE);

                    fuelAdapter = new
                            ArrayAdapter(MotorInsuranceActivity.this, android.R.layout.simple_list_item_1, fuelList);
                    spCarFuelType.setAdapter(fuelAdapter);

                    varientAdapter = new
                            ArrayAdapter(MotorInsuranceActivity.this, android.R.layout.simple_list_item_1, variantList);
                    spCarFuelType.setAdapter(varientAdapter);
                }
            });
            //endregion


        } else if (insuranceType.equals("BIKE")) {
            makeModelList = databaseController.getBikeMakeModel();
            makeModelAdapter = new
                    ArrayAdapter(MotorInsuranceActivity.this, android.R.layout.simple_list_item_1, makeModelList);
            autoCarMakeModel.setAdapter(makeModelAdapter);

            // region  Auto Complete car make
            autoCarMakeModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    hideKeyBoard(backdrop, MotorInsuranceActivity.this);
                    modelId = databaseController.getBikeModelID(getModel(autoCarMakeModel.getText().toString()));

                    fuelList = databaseController.getBikeFuelTypebyModelID(modelId);
                    variantList = databaseController.getBikeVariantbyModelID(modelId);

                    spCarFuelType.setVisibility(View.VISIBLE);
                    spCarVarient.setVisibility(View.VISIBLE);

                    fuelAdapter = new
                            ArrayAdapter(MotorInsuranceActivity.this, android.R.layout.simple_list_item_1, fuelList);
                    spCarFuelType.setAdapter(fuelAdapter);

                    varientAdapter = new
                            ArrayAdapter(MotorInsuranceActivity.this, android.R.layout.simple_list_item_1, variantList);
                    spCarFuelType.setAdapter(varientAdapter);
                }
            });
            //endregion

        }

    }

    private void changeTextLabels() {
        if (insuranceType.equals("CAR")) {
            backdrop.setImageDrawable(getResources().getDrawable(R.drawable.car_images));
        } else {
            backdrop.setImageDrawable(getResources().getDrawable(R.drawable.bike_homepage_banner));
        }
    }

    private void initView() {
        llVarient = (LinearLayout) findViewById(R.id.llVarient);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        etRenewRegNo = (EditText) findViewById(R.id.etRenewRegNo);
        etRenewRegNo.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etExternallyFitted = (EditText) findViewById(R.id.etExternallyFitted);
        autoCarMakeModel = (AutoCompleteTextView) findViewById(R.id.autoCarMakeModel);
        btnGEtDetails = (Button) findViewById(R.id.btnGEtDetails);
        btnCont = (Button) findViewById(R.id.btnCont);
        tvDontRem = (TextView) findViewById(R.id.tvDontRem);
        tvNew = (TextView) findViewById(R.id.tvNew);
        tvCvHeader = (TextView) findViewById(R.id.tvCvHeader);
        spCarFuelType = (Spinner) findViewById(R.id.spCarFuelType);
        spCarVarient = (Spinner) findViewById(R.id.spCarVarient);
        cvMotorDetails = (CardView) findViewById(R.id.cvMotorDetails);
        tilExternal = (TextInputLayout) findViewById(R.id.tilExternal);
        cvMotorDetails.setVisibility(View.GONE);

    }

    private void setListeners() {
        btnCont.setOnClickListener(this);
        btnGEtDetails.setOnClickListener(this);
        tvDontRem.setOnClickListener(this);
        tvNew.setOnClickListener(this);
        etRenewRegNo.addTextChangedListener(renewtextWatcher);
    }

    public String getInsuranceType() {
        if (getIntent().hasExtra(Constants.CAR)) {
            return "CAR";
        } else if (getIntent().hasExtra(Constants.BIKE)) {
            return "BIKE";
        }
        return "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvDontRem:
                cvMotorDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.tvNew:
                cvMotorDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.btnGEtDetails:
                if (etRenewRegNo.getText().toString().equals("") || etRenewRegNo.getText().toString().length() != 10) {
                    etRenewRegNo.requestFocus();
                    etRenewRegNo.setError("Enter Valid Registration No");
                    return;
                }
                showDialog();
                Constants.hideKeyBoard(backdrop, MotorInsuranceActivity.this);
                new FastlaneController(MotorInsuranceActivity.this).getCarDetails(etRenewRegNo.getText().toString(), MotorInsuranceActivity.this);

                break;
            case R.id.btnCont:
                break;

        }
    }

    TextWatcher renewtextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 10) {
                showDialog();
                Constants.hideKeyBoard(backdrop, MotorInsuranceActivity.this);
                new FastlaneController(MotorInsuranceActivity.this).getCarDetails(s.toString(), MotorInsuranceActivity.this);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof FastLaneResponse) {
            cancelDialog();
           /* quoteRequestEntity.setNew(false);
            quoteRequestEntity.setRenew(true);
            quoteRequestEntity.setDontRem(false);
            quoteRequestEntity.setRegistrationNumber(etRenewRegNo.getText().toString());
            startActivity(new Intent(this, FastLaneCarDetails.class).putExtra(Constants.QUOTE, quoteRequestEntity)
                    .putExtra(FASTLANE_DATA, ((FastLaneResponse) response).getFLResponse()));*/
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        /*quoteRequestEntity.setNew(false);
        quoteRequestEntity.setRenew(false);
        quoteRequestEntity.setDontRem(true);
        quoteRequestEntity.setRegistrationNumber(etRenewRegNo.getText().toString());
        startActivity(new Intent(this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));*/
    }

    public String getModel(String makeModel) {
        String[] parts = makeModel.split(",");
        return parts[1];
    }
}
