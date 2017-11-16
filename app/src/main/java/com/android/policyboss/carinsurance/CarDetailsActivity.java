package com.android.policyboss.carinsurance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.TextView;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.personaldetail.CustomerDetailsActivity;
import com.android.policyboss.utility.Constants;
import com.android.policyboss.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;

public class CarDetailsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    public static final String CAR_DETAIL = "CarDetailsActivity.class";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    List<String> insurerList;

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
    Button btnGetQuote;

    BikeRequestEntity bikeRequestEntity;

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
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, yearList) {
                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
                    }
                };
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

        prevInsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, insurerList);
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
        insurerList = databaseController.getInsurerList();
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
        etPolicyExpDate.setOnClickListener(policyExpDatePickerDialog);

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
        bikeRequestEntity = new BikeRequestEntity();
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
            DateTimePicker.showFirstRegDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
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
    protected View.OnClickListener policyExpDatePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, CarDetailsActivity.this);
            DateTimePicker.showNextSixMonthDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
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
                    llNcb.setVisibility(View.GONE);
                } else {
                    llNcb.setVisibility(View.VISIBLE);
                    llNcb.requestFocus();
                }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGetQuote) {
            //fuelId = databaseController.getFuelID(spCarFuelType.getSelectedItem().toString(), modelID);
            //setInputParametrs();
            if (quoteRequestEntity.isNew())
                setInputParametersNew();
            else if (quoteRequestEntity.isRenew())
                setInputParametersReNew();
            else if (quoteRequestEntity.isDontRem())
                setInputParametersDontRemember();


            //TODO : redirect to customer detail
            startActivity(new Intent(CarDetailsActivity.this, CustomerDetailsActivity.class)
                    .putExtra(CAR_DETAIL, bikeRequestEntity));


        }
    }

    private void setInputParametersNew() {

       /* varientId = databaseController.getVariantID(spCarVarient.getSelectedItem().toString());
        quoteRequestEntity.setVariant_ID(varientId);
        quoteRequestEntity.setVehicleCity_Id(databaseController.getCityID(autoCity.getText().toString()));
        quoteRequestEntity.setManufacturingYear(Integer.parseInt(spManufactureYear.getSelectedItem().toString()));

        quoteRequestEntity.setValueOfElectricalAccessories("" + etElecAcc.getText().toString());
        quoteRequestEntity.setValueOfNonElectricalAccessories("" + etNonElecAcc.getText().toString());
        quoteRequestEntity.setIsClaimInExpiringPolicy(!switchNcb.isChecked());
        quoteRequestEntity.setCurrentNCB("" + spNcbPercent.getSelectedItem().toString());
        quoteRequestEntity.setSupportsAgentID(945);*/
//        quoteRequestEntity.setSupportsAgentID(new LoginFacade(this).getUser().getEmp_Id());


        bikeRequestEntity.setProduct_id(1);
        bikeRequestEntity.setVehicle_id(databaseController.getVariantID(spCarVarient.getSelectedItem().toString()));
        bikeRequestEntity.setRto_id(databaseController.getCityID(autoCity.getText().toString()));
        bikeRequestEntity.setSecret_key(Constants.SECRET_KEY);
        bikeRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeRequestEntity.setExecution_async("yes");
        bikeRequestEntity.setVehicle_insurance_type("new");
        bikeRequestEntity.setVehicle_manf_date("");
        bikeRequestEntity.setVehicle_registration_date(etFirstRegDate.getText().toString());
        bikeRequestEntity.setPolicy_expiry_date("");
        bikeRequestEntity.setPrev_insurer_id("");
        bikeRequestEntity.setVehicle_registration_type("individual");
        bikeRequestEntity.setVehicle_ncb_current("");
        bikeRequestEntity.setIs_claim_exists("yes");
        bikeRequestEntity.setMethod_type("Premium");
        bikeRequestEntity.setElectrical_accessory("0");
        bikeRequestEntity.setNon_electrical_accessory("0");
        bikeRequestEntity.setRegistration_no(getRegistrationNo(autoCity.getText().toString()));
        bikeRequestEntity.setIs_llpd("no");
        bikeRequestEntity.setIs_antitheft_fit("no");
        bikeRequestEntity.setVoluntary_deductible(0);
        bikeRequestEntity.setIs_external_bifuel("no");
        bikeRequestEntity.setPa_owner_driver_si("");
        bikeRequestEntity.setPa_named_passenger_si("");
        bikeRequestEntity.setPa_unnamed_passenger_si("");
        bikeRequestEntity.setPa_paid_driver_si("");
        bikeRequestEntity.setVehicle_expected_idv(0);
        bikeRequestEntity.setFirst_name("");
        bikeRequestEntity.setMiddle_name("");
        bikeRequestEntity.setLast_name("");
        bikeRequestEntity.setMobile("");
        bikeRequestEntity.setEmail("");
        bikeRequestEntity.setCrn(0);
        bikeRequestEntity.setIp_address("");


    }

    private void setInputParametersReNew() {
       /* quoteRequestEntity.setPreveious_Insurer_Id("" + databaseController.getInsurenceID(spPrevInsurer.getSelectedItem().toString()));
        quoteRequestEntity.setDateofPurchaseofCar("" + changeDateFormat(fastLaneResponseEntity.getPurchase_Date()));
        quoteRequestEntity.setVariant_ID(fastLaneResponseEntity.getVariant_Id());
        quoteRequestEntity.setPolicyExpiryDate(etPolicyExpDate.getText().toString());
        quoteRequestEntity.setVehicleCity_Id(fastLaneResponseEntity.getVehicleCity_Id());
        quoteRequestEntity.setManufacturingYear(Integer.parseInt(fastLaneResponseEntity.getManufacture_Year()));

        quoteRequestEntity.setValueOfElectricalAccessories("" + etElecAcc.getText().toString());
        quoteRequestEntity.setValueOfNonElectricalAccessories("" + etNonElecAcc.getText().toString());
        quoteRequestEntity.setIsClaimInExpiringPolicy(!switchNcb.isChecked());
        quoteRequestEntity.setCurrentNCB("" + spNcbPercent.getSelectedItem().toString());

        quoteRequestEntity.setSupportsAgentID(new LoginFacade(this).getUser().getEmp_Id());
*/

        bikeRequestEntity.setProduct_id(1);
        bikeRequestEntity.setVehicle_id(databaseController.getVariantID(spCarVarient.getSelectedItem().toString()));
        bikeRequestEntity.setRto_id(databaseController.getCityID(autoCity.getText().toString()));
        bikeRequestEntity.setSecret_key(Constants.SECRET_KEY);
        bikeRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeRequestEntity.setExecution_async("yes");
        bikeRequestEntity.setVehicle_insurance_type("renew");
        bikeRequestEntity.setVehicle_manf_date("");
        bikeRequestEntity.setVehicle_registration_date(etFirstRegDate.getText().toString());
        bikeRequestEntity.setPolicy_expiry_date(etPolicyExpDate.getText().toString());
        bikeRequestEntity.setPrev_insurer_id("" + databaseController.getInsurenceID(spPrevInsurer.getSelectedItem().toString()));
        bikeRequestEntity.setVehicle_registration_type("individual");
        bikeRequestEntity.setMethod_type("Premium");

        if (switchNcb.isChecked()) {
            bikeRequestEntity.setIs_claim_exists("no");
            bikeRequestEntity.setVehicle_ncb_current("");
        } else {
            bikeRequestEntity.setIs_claim_exists("yes");
            bikeRequestEntity.setVehicle_ncb_current(spNcbPercent.getSelectedItem().toString());
        }

        if (switchAdditional.isChecked()) {
            bikeRequestEntity.setElectrical_accessory(etElecAcc.getText().toString());
            bikeRequestEntity.setNon_electrical_accessory(etNonElecAcc.getText().toString());
        } else {
            bikeRequestEntity.setElectrical_accessory("0");
            bikeRequestEntity.setNon_electrical_accessory("0");
        }

        bikeRequestEntity.setRegistration_no(quoteRequestEntity.getRegistrationNumber());
        bikeRequestEntity.setIs_llpd("no");
        bikeRequestEntity.setIs_antitheft_fit("no");
        bikeRequestEntity.setVoluntary_deductible(0);
        bikeRequestEntity.setIs_external_bifuel("no");
        bikeRequestEntity.setPa_owner_driver_si("");
        bikeRequestEntity.setPa_named_passenger_si("");
        bikeRequestEntity.setPa_unnamed_passenger_si("");
        bikeRequestEntity.setPa_paid_driver_si("");
        bikeRequestEntity.setVehicle_expected_idv(0);
        bikeRequestEntity.setFirst_name("");
        bikeRequestEntity.setMiddle_name("");
        bikeRequestEntity.setLast_name("");
        bikeRequestEntity.setMobile("");
        bikeRequestEntity.setEmail("");
        bikeRequestEntity.setCrn(0);
        bikeRequestEntity.setIp_address("");


    }

    private void setInputParametersDontRemember() {

    /*    quoteRequestEntity.setPreveious_Insurer_Id("" + databaseController.getInsurenceID(spPrevInsurer.getSelectedItem().toString()));
        quoteRequestEntity.setPolicyExpiryDate(etPolicyExpDate.getText().toString());
        quoteRequestEntity.setDateofPurchaseofCar(etFirstRegDate.getText().toString());
        varientId = databaseController.getVariantID(spCarVarient.getSelectedItem().toString());
        quoteRequestEntity.setVariant_ID(varientId);
        quoteRequestEntity.setVehicleCity_Id(databaseController.getCityID(autoCity.getText().toString()));
        quoteRequestEntity.setManufacturingYear(Integer.parseInt(spManufactureYear.getSelectedItem().toString()));

        quoteRequestEntity.setValueOfElectricalAccessories("" + etElecAcc.getText().toString());
        quoteRequestEntity.setValueOfNonElectricalAccessories("" + etNonElecAcc.getText().toString());
        quoteRequestEntity.setIsClaimInExpiringPolicy(!switchNcb.isChecked());
        quoteRequestEntity.setCurrentNCB("" + spNcbPercent.getSelectedItem().toString());

        quoteRequestEntity.setSupportsAgentID(new LoginFacade(this).getUser().getEmp_Id());*/

        bikeRequestEntity.setProduct_id(1);
        bikeRequestEntity.setVehicle_id(databaseController.getVariantID(spCarVarient.getSelectedItem().toString()));
        bikeRequestEntity.setRto_id(databaseController.getCityID(autoCity.getText().toString()));
        bikeRequestEntity.setSecret_key(Constants.SECRET_KEY);
        bikeRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeRequestEntity.setExecution_async("yes");
        bikeRequestEntity.setVehicle_insurance_type("renew");
        bikeRequestEntity.setVehicle_manf_date("");
        bikeRequestEntity.setVehicle_registration_date(etFirstRegDate.getText().toString());
        bikeRequestEntity.setPolicy_expiry_date(etPolicyExpDate.getText().toString());
        bikeRequestEntity.setPrev_insurer_id("" + databaseController.getInsurenceID(spPrevInsurer.getSelectedItem().toString()));
        bikeRequestEntity.setVehicle_registration_type("individual");
        bikeRequestEntity.setMethod_type("Premium");

        if (switchNcb.isChecked()) {
            bikeRequestEntity.setIs_claim_exists("no");
            bikeRequestEntity.setVehicle_ncb_current("");
        } else {
            bikeRequestEntity.setIs_claim_exists("yes");
            bikeRequestEntity.setVehicle_ncb_current(spNcbPercent.getSelectedItem().toString());
        }

        if (switchAdditional.isChecked()) {
            bikeRequestEntity.setElectrical_accessory(etElecAcc.getText().toString());
            bikeRequestEntity.setNon_electrical_accessory(etNonElecAcc.getText().toString());
        } else {
            bikeRequestEntity.setElectrical_accessory("0");
            bikeRequestEntity.setNon_electrical_accessory("0");
        }


        if (quoteRequestEntity.getRegistrationNumber() != "")
            bikeRequestEntity.setRegistration_no(bikeRequestEntity.getRegistration_no());
        else
            bikeRequestEntity.setRegistration_no(getRegistrationNo(autoCity.getText().toString()));
        bikeRequestEntity.setIs_llpd("no");
        bikeRequestEntity.setIs_antitheft_fit("no");
        bikeRequestEntity.setVoluntary_deductible(0);
        bikeRequestEntity.setIs_external_bifuel("no");
        bikeRequestEntity.setPa_owner_driver_si("");
        bikeRequestEntity.setPa_named_passenger_si("");
        bikeRequestEntity.setPa_unnamed_passenger_si("");
        bikeRequestEntity.setPa_paid_driver_si("");
        bikeRequestEntity.setVehicle_expected_idv(0);
        bikeRequestEntity.setFirst_name("");
        bikeRequestEntity.setMiddle_name("");
        bikeRequestEntity.setLast_name("");
        bikeRequestEntity.setMobile("");
        bikeRequestEntity.setEmail("");
        bikeRequestEntity.setCrn(0);
        bikeRequestEntity.setIp_address("");


    }


    public String changeDateFormat(String date) {
        String[] parts = date.split("/");
        String newDate = parts[2] + "-" + parts[1] + "-" + parts[0];
        return newDate;
    }

    private String getRegistrationNo(String city) {
        return "" + city.charAt(1) + city.charAt(2) + "-" + city.charAt(3) + city.charAt(4) + "-AA-1234";
    }
}
