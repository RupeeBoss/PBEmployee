package com.android.policyboss.carinsurance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.controller.motorquote.MotorQuoteController;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.core.response.MotorQuotesResponse;
import com.android.policyboss.utility.Constants;
import com.android.policyboss.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;

public class CarDetailsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, IResponseSubcriber {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");
    LinearLayout llWhenPolicyExpiring, llVarientDetails, llAdditionalDetails, llAdditionAcc, llNcb;
    QuoteRequestEntity quoteRequestEntity;
    FastLaneResponse.FLResponseBean fastLaneResponseEntity;
    DatabaseController databaseController;

    List<String> cityList;
    List<String> makeList;
    List<String> modelList;
    List<String> fuelList;
    List<String> variantList;
    List<String> yearList;

    int makeId, modelID, fuelId, varientId;

    ArrayAdapter<String> yearAdapter;
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
    EditText etElecAcc, etNonElecAcc, etPolicyExpDate, etFirstRegDate;

    Spinner spManufactureYear;
    ;

    Button btnGetQuote;

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
        init_widgets();
        setListeners();

        QuoteRequestEntity entity = new QuoteRequestEntity();
        realm = Realm.getDefaultInstance();
        databaseController = new DatabaseController(this, realm);
        initialise_list();

        // initialise all insurence and profession
        //new DatabaseController(this, realm).MapInsurence();
        //new DatabaseController(this, realm).MapProfession();

        quoteRequestEntity = new QuoteRequestEntity();
        fastLaneResponseEntity = new FastLaneResponse.FLResponseBean();
        fetchMasterFromDatabase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quoteRequestEntity = getIntent().getParcelableExtra(Constants.QUOTE);
        fastLaneResponseEntity = getIntent().getParcelableExtra(CarInsuranceActivity.FASTLANE_DATA);
        bindingAdapters();
    }

    private void bindingAdapters() {

        yearAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, yearList);
        spManufactureYear.setAdapter(yearAdapter);

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
        yearList = Constants.getPastFifteenYear();
        //modelList = databaseController.getModelList(0);

    }

    private void initialise_list() {
        cityList = new ArrayList<>();
        makeList = new ArrayList<>();
        modelList = new ArrayList<>();
        variantList = new ArrayList<>();
        yearList = new ArrayList<>();
    }

    private void showOrHideLayout() {
        if (quoteRequestEntity.isNew()) {
            llVarientDetails.setVisibility(View.VISIBLE);
        } else if (quoteRequestEntity.isRenew()) {
            llVarientDetails.setVisibility(View.GONE);
            llWhenPolicyExpiring.setVisibility(View.VISIBLE);
        } else if (quoteRequestEntity.isDontRem()) {
            llWhenPolicyExpiring.setVisibility(View.VISIBLE);
            llVarientDetails.setVisibility(View.VISIBLE);
        }
    }

    private void setListeners() {

        btnGetQuote.setOnClickListener(this);
        etFirstRegDate.setOnClickListener(firstDatePickerDialog);
        switchNcb.setOnCheckedChangeListener(this);
        switchAdditional.setOnCheckedChangeListener(this);
        etPolicyExpDate.setOnClickListener(policyDatePickerDialog);

        // region  Auto Complete car make
        autoCarMake.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                makeId = databaseController.getMakeID(makeAdapter.getItem(position).toString());
                modelList = databaseController.getModelList(makeId);

                spCarModel.setVisibility(View.VISIBLE);

                modelAdapter = new
                        ArrayAdapter(CarDetailsActivity.this, android.R.layout.simple_list_item_1, modelList);
                spCarModel.setAdapter(modelAdapter);
            }
        });
        //endregion

        //region model spinner
        spCarModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                spCarVarient.setVisibility(View.VISIBLE);

                modelID = databaseController.getModelID(makeId, modelAdapter.getItem(position).toString());
                variantList = databaseController.getVariantList(modelID);

                varientAdapter = new ArrayAdapter(CarDetailsActivity.this, android.R.layout.simple_list_item_1, variantList);
                spCarVarient.setAdapter(varientAdapter);


                spCarFuelType.setVisibility(View.VISIBLE);
                fuelList = databaseController.getFuelType(modelID);
                fuelAdapter = new ArrayAdapter(CarDetailsActivity.this, android.R.layout.simple_list_item_1, fuelList);
                spCarFuelType.setAdapter(fuelAdapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

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

        spManufactureYear = (Spinner) findViewById(R.id.spManufactureYear);
        etFirstRegDate = (EditText) findViewById(R.id.etFirstRegDate);
        etElecAcc = (EditText) findViewById(R.id.etElecAcc);
        etNonElecAcc = (EditText) findViewById(R.id.etNonElecAcc);
        btnGetQuote = (Button) findViewById(R.id.btnGetQuote);
        etPolicyExpDate = (EditText) findViewById(R.id.etPolicyExpDate);
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
        spWhenPolicyExp = (Spinner) findViewById(R.id.spWhenPolicyExp);
        spPrevInsurer = (Spinner) findViewById(R.id.spPrevInsurer);
        spNcbPercent = (Spinner) findViewById(R.id.spNcbPercent);
    }

    //region  datepickerdialog
    protected View.OnClickListener firstDatePickerDialog = new View.OnClickListener() {
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
                        etFirstRegDate.setText(currentDay);
                        //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                        //etDate.setTag(R.id.et_date, calendar.getTime());
                    }
                }
            });
        }
    };
    //endregion


    //region  datepickerdialog
    protected View.OnClickListener policyDatePickerDialog = new View.OnClickListener() {
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
                        etPolicyExpDate.setText(currentDay);
                        //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                        //etDate.setTag(R.id.et_date, calendar.getTime());
                    }
                }
            });
        }
    };
    //endregion

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGetQuote) {

            varientId = databaseController.getVariantID(spCarVarient.getSelectedItem().toString());
            fuelId = databaseController.getFuelID(spCarFuelType.getSelectedItem().toString(), modelID);

            setInputParametrs();
            showDialog();
            new MotorQuoteController(this).getQuoteDetails(createQuoteRequest(quoteRequestEntity, fastLaneResponseEntity), CarDetailsActivity.this);

        }
    }

    private void setInputParametrs() {

        quoteRequestEntity.setSourceType("APP");
        quoteRequestEntity.setProductID(1);
        quoteRequestEntity.setProfession_Id(6);
        quoteRequestEntity.setValueOfElectricalAccessories("" + etElecAcc.getText().toString());
        quoteRequestEntity.setValueOfNonElectricalAccessories("" + etNonElecAcc.getText().toString());
        quoteRequestEntity.setIsClaimInExpiringPolicy(!switchNcb.isChecked());
        quoteRequestEntity.setManufacturingYear(Integer.parseInt(spManufactureYear.getSelectedItem().toString()));

        if (quoteRequestEntity.isRenew()) {
            quoteRequestEntity.setPreveious_Insurer_Id("" + databaseController.getInsurenceID(spPrevInsurer.getSelectedItem().toString()));
            quoteRequestEntity.setDateofPurchaseofCar("" + fastLaneResponseEntity.getRegistration_Date());
            quoteRequestEntity.setVariant_ID(fastLaneResponseEntity.getVariant_Id());
            quoteRequestEntity.setPolicyExpiryDate(simpleDateFormat.format(etPolicyExpDate.getText().toString()));
            quoteRequestEntity.setVehicleCity_Id(fastLaneResponseEntity.getVehicleCity_Id());

        } else {
            quoteRequestEntity.setVariant_ID(varientId);
            quoteRequestEntity.setVehicleCity_Id(databaseController.getCityID(autoCity.getText().toString()));
            quoteRequestEntity.setPolicyExpiryDate("");
        }


        quoteRequestEntity.setVehicleRegisteredName(1);
        quoteRequestEntity.setSupportsAgentID(2);
        quoteRequestEntity.setCurrentNCB("" + spNcbPercent.getSelectedItem().toString());

    }

    //region create Quote

    public QuoteRequestEntity createQuoteRequest(QuoteRequestEntity quoteRequestEntity, FastLaneResponse.FLResponseBean fastlane) {
        QuoteRequestEntity entity = new QuoteRequestEntity();
        return entity;
    }

    //endregion


    //region Quote response

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof MotorQuotesResponse) {
            if (response.getStatusNo() == 0) {
                startActivity(new Intent(this, CarQuoteGenerate.class)
                        .putExtra(Constants.MOTOR_QUOTE_DATA, (MotorQuotesResponse) response));
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    //endregion

    //region rahul commented
    /*  quotesReqEntity.setVehicleNo("");
        quotesReqEntity.setCustomerReferenceID("");
        quotesReqEntity.setProductID(1);
        quotesReqEntity.setExpectedIDV(0);
        quotesReqEntity.setIDVinExpiryPolicy(0);
        quotesReqEntity.setDateofPurchaseofCar("2017-05-30");
        quotesReqEntity.setVD_Amount(0);
        quotesReqEntity.setPACoverValue(0);
        quotesReqEntity.setVehicleCity_Id(580);
        quotesReqEntity.setProfession_Id(6);

        quotesReqEntity.setValueOfElectricalAccessories(0);
        quotesReqEntity.setValueOfNonElectricalAccessories(0);
        quotesReqEntity.setValueOfBiFuelKit(0);
        quotesReqEntity.setCurrentNCB(0);
        quotesReqEntity.setIsClaimInExpiringPolicy(false);
        quotesReqEntity.setApplyAntiTheftDiscount(false);
        quotesReqEntity.setApplyAutomobileAssociationDiscount(false);
        quotesReqEntity.setAutomobileAssociationName("");
        quotesReqEntity.setAutomobileMembershipExpiryDate("");
        quotesReqEntity.setAutomobileAssociationMembershipNumber("");

        quotesReqEntity.setPaidDriverCover(false);
        quotesReqEntity.setOwnerDOB("");
        quotesReqEntity.setPreveious_Insurer_Id(0);
        quotesReqEntity.setManufacturingYear(2017);
        quotesReqEntity.setPolicyExpiryDate("2017-06-24");
        quotesReqEntity.setVehicleRegisteredName(1);
        quotesReqEntity.setVariant_ID(690);
        quotesReqEntity.setRegistrationNumber("");
        quotesReqEntity.setPlaceofRegistration("");
        quotesReqEntity.setVehicleType("1");

        quotesReqEntity.setExisting_CustomerReferenceID("");
        quotesReqEntity.setContactName("Umesh");
        quotesReqEntity.setContactEmail("");
        quotesReqEntity.setContactMobile("");
        quotesReqEntity.setLandmarkEmployeeCode("");
        quotesReqEntity.setSupportsAgentID(123);
        quotesReqEntity.setSessionID("59e979ed-dfc7-4d79-9f28-d427a554917e");
        quotesReqEntity.setSourceType("APP");
        quotesReqEntity.setInsurerIDArray("");

        Gson gson = new Gson();
      String strJson = gson.toJson(quotesReqEntity);
        showDialog();
        new MotorQuoteController(this).getQuoteDetails(quotesReqEntity ,CarDetailsActivity.this);
*/
    //endregion
}
