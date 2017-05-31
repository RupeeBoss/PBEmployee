package com.android.policyboss.carinsurance;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.utility.Constants;
import com.android.policyboss.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;

public class CarDetailsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yyyy");
    LinearLayout llWhenPolicyExpiring, llVarientDetails, llAdditionalDetails, llAdditionAcc, llNcb;
    QuoteRequestEntity quoteRequestEntity;
    DatabaseController databaseController;

    List<String> cityList;
    List<String> makeList;
    List<String> modelList;
    List<String> fuelList;
    List<String> variantList;

    int makeId, modelID, fuelId, varientId;

    ArrayAdapter<String> makeAdapter;
    ArrayAdapter<String> cityAdapter;
    ArrayAdapter<String> modelAdapter;
    ArrayAdapter<String> varientAdapter;
    ArrayAdapter<String> fuelAdapter;
    ArrayAdapter<String> prevInsAdapter;
    ArrayAdapter<String> policyExpAdapter;
    ArrayAdapter<String> ncbPerctAdapter;

    Spinner spCarModel, spCarFuelType, spCarVarient, spWhenPolicyExp, spPrevInsurer, spNcbPercent;
    AutoCompleteTextView autoCarMake, autoCity;
    Switch switchAdditional, switchNcb;
    EditText etManufactYear;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed())
            realm.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        realm = Realm.getDefaultInstance();
        databaseController = new DatabaseController(this, realm);
        initialise_list();
        fetchMasterFromDatabase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quoteRequestEntity = getIntent().getParcelableExtra(Constants.QUOTE);
        init_widgets();
        setListeners();

        bindingAdapters();


    }

    private void bindingAdapters() {

        makeAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, makeList);
        autoCarMake.setAdapter(makeAdapter);
        autoCarMake.setThreshold(1);


        varientAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, variantList);
        cityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList);
        autoCity.setAdapter(cityAdapter);
        autoCity.setThreshold(1);

        prevInsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.array_insurance_company));
        spPrevInsurer.setAdapter(prevInsAdapter);


        policyExpAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.policy_expiry));
        spWhenPolicyExp.setAdapter(policyExpAdapter);

        ncbPerctAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ncb_percent));
        spNcbPercent.setAdapter(ncbPerctAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showOrHideLayout();
    }

    private void fetchMasterFromDatabase() {
        cityList = databaseController.getCity();
        makeList = databaseController.getMakeList();
        //modelList = databaseController.getModelList(0);

    }

    private void initialise_list() {
        cityList = new ArrayList<>();
        makeList = new ArrayList<>();
        modelList = new ArrayList<>();
        variantList = new ArrayList<>();
    }

    private void showOrHideLayout() {
        if (quoteRequestEntity.isNew()) {
            llVarientDetails.setVisibility(View.VISIBLE);
        } else if (quoteRequestEntity.isRenew()) {
            llWhenPolicyExpiring.setVisibility(View.VISIBLE);
        } else if (quoteRequestEntity.isDontRem()) {
            llWhenPolicyExpiring.setVisibility(View.VISIBLE);
            llVarientDetails.setVisibility(View.VISIBLE);
        }
    }

    private void setListeners() {

        etManufactYear.setOnClickListener(datePickerDialog);
        switchNcb.setOnCheckedChangeListener(this);
        switchAdditional.setOnCheckedChangeListener(this);

        autoCarMake.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                makeId = databaseController.getMakeID(makeAdapter.getItem(position).toString());
                modelList = databaseController.getModelList(makeId);
                //Toast.makeText(CarDetailsActivity.this, "" + makeAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
                modelAdapter = new
                        ArrayAdapter(CarDetailsActivity.this, android.R.layout.simple_list_item_1, modelList);
                spCarModel.setVisibility(View.VISIBLE);
                spCarModel.setAdapter(modelAdapter);
            }
        });

        spCarModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelID = databaseController.getModelID(makeId, modelAdapter.getItem(position).toString());

                variantList = databaseController.getVariantList(modelID);
                varientAdapter = new ArrayAdapter(CarDetailsActivity.this, android.R.layout.simple_list_item_1, variantList);
                spCarVarient.setVisibility(View.VISIBLE);
                spCarVarient.setAdapter(varientAdapter);


                fuelList = databaseController.getFuelType(modelID);
                fuelAdapter = new
                        ArrayAdapter(CarDetailsActivity.this, android.R.layout.simple_list_item_1, fuelList);
                spCarFuelType.setVisibility(View.VISIBLE);
                spCarFuelType.setAdapter(fuelAdapter);
                varientId = databaseController.getVariantID(varientAdapter.getItem(position).toString());
                fuelId = databaseController.getFuelID(fuelAdapter.getItem(position).toString(), modelID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //region varient Spinner
        spCarVarient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                varientId = databaseController.getVariantID(varientAdapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        //region fuel Spinner
        spCarFuelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fuelId = databaseController.getFuelID(fuelAdapter.getItem(position).toString(), modelID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion


    }

    private void init_widgets() {
        llWhenPolicyExpiring = (LinearLayout) findViewById(R.id.llWhenPolicyExpiring);
        llVarientDetails = (LinearLayout) findViewById(R.id.llVarientDetails);
        llAdditionalDetails = (LinearLayout) findViewById(R.id.llAdditionalDetails);
        llAdditionAcc = (LinearLayout) findViewById(R.id.llAdditionAcc);
        llNcb = (LinearLayout) findViewById(R.id.llNcb);
        spCarFuelType = (Spinner) findViewById(R.id.spCarFuelType);
        spCarVarient = (Spinner) findViewById(R.id.spCarVarient);
        spCarModel = (Spinner) findViewById(R.id.spCarModel);
        autoCarMake = (AutoCompleteTextView) findViewById(R.id.autoCarMake);
        autoCity = (AutoCompleteTextView) findViewById(R.id.autoCity);
        switchAdditional = (Switch) findViewById(R.id.switchAdditional);
        switchNcb = (Switch) findViewById(R.id.switchNcb);
        etManufactYear = (EditText) findViewById(R.id.etManufactYear);
        spWhenPolicyExp = (Spinner) findViewById(R.id.spWhenPolicyExp);
        spPrevInsurer = (Spinner) findViewById(R.id.spPrevInsurer);
        spNcbPercent = (Spinner) findViewById(R.id.spNcbPercent);
    }

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, CarDetailsActivity.this);
            DateTimePicker.showDataPickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if (view.isShown()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        String currentDay = simpleDateFormat.format(calendar.getTime());
                        etManufactYear.setText(currentDay);
                        //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                        //etDate.setTag(R.id.et_date, calendar.getTime());
                    }
                }
            });
        }
    };


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switchAdditional:
                if (isChecked) {
                    llAdditionAcc.setVisibility(View.VISIBLE);
                    llAdditionAcc.requestFocus();
                } else {
                    llAdditionAcc.setVisibility(View.GONE);
                }
                break;
            case R.id.switchNcb:
                if (isChecked) {
                    llNcb.setVisibility(View.VISIBLE);
                    llNcb.requestFocus();
                } else {
                    llNcb.setVisibility(View.GONE);
                }
        }
    }
}
