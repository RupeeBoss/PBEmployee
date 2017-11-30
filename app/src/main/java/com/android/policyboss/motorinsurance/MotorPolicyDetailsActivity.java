package com.android.policyboss.motorinsurance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.personaldetail.CustomerDetailsActivity;
import com.android.policyboss.utility.Constants;
import com.android.policyboss.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;

public class MotorPolicyDetailsActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber, CompoundButton.OnCheckedChangeListener {
    public static final String BIKE_INSURENCE = "BikeInsuranceActivity";
    public static final String CAR_DETAIL = "CarDetailsActivity.class";

    FastLaneResponse.FLResponseBean fastLaneResponseEntity;
    EditText etFirstRegDate, etPolicyExpDate, etManufactYearMonth;
    Spinner spPrevInsurer, spNcbPercent;
    AutoCompleteTextView acRegPlace;
    Switch switchNcb;
    LinearLayout llNcb;
    Button btnCont;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ArrayAdapter<String> cityAdapter, prevInsAdapter, ncbPerctAdapter;
    DatabaseController databaseController;
    String regplace, fromWhichClass;
    BikeRequestEntity bikeRequestEntity, carRequestEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_policy_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bikeRequestEntity = new BikeRequestEntity();
        carRequestEntity = new BikeRequestEntity();
        if (getIntent().hasExtra(Constants.BIKE)) {
            fromWhichClass = "BIKE";
            bikeRequestEntity = getIntent().getParcelableExtra(Constants.BIKE);
        } else if (getIntent().hasExtra(Constants.CAR)) {
            fromWhichClass = "CAR";
            carRequestEntity = getIntent().getParcelableExtra(Constants.CAR);
        } else if (getIntent().hasExtra(Constants.FASTLANE_DATA)) {
            fromWhichClass = "FASTLANE";
            fastLaneResponseEntity = getIntent().getParcelableExtra(Constants.FASTLANE_DATA);
        }
        realm = Realm.getDefaultInstance();
        databaseController = new DatabaseController(this, realm);
        initWidgets();
        setOnClickListener();
        bindingAdapters();
        if (fastLaneResponseEntity != null) {
            bindFastLaneData();
        } else {
            final Calendar calendar = Calendar.getInstance();
            String currentDay = simpleDateFormat.format(calendar.getTime());
            etPolicyExpDate.setText(currentDay);
        }
    }

    private void bindFastLaneData() {
        if (fastLaneResponseEntity != null) {
            final Calendar calendar = Calendar.getInstance();
            etFirstRegDate.setText(changeDateFormat(fastLaneResponseEntity.getRegistration_Date()));
            String currentDay = simpleDateFormat.format(calendar.getTime());
            etPolicyExpDate.setText(currentDay);
            etManufactYearMonth.setText(changeDateFormat(fastLaneResponseEntity.getRegistration_Date()));
            acRegPlace.setText(databaseController.getCityName(fastLaneResponseEntity.getVehicleCity_Id()));
            setNcbAdapter(getYearDiffForNCB(etFirstRegDate.getText().toString(), etPolicyExpDate.getText().toString()));
            hideKeyBoard(acRegPlace, this);
        }
    }

    private void bindingAdapters() {

        // region city adapter
        cityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, databaseController.getCity());
        acRegPlace.setAdapter(cityAdapter);
        acRegPlace.setThreshold(2);

        acRegPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                regplace = cityAdapter.getItem(position).toString();
                hideKeyBoard(acRegPlace, MotorPolicyDetailsActivity.this);
            }
        });
        /**
         * Unset the var whenever the user types. Validation will
         * then fail. This is how we enforce selecting from the list.
         */
        acRegPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                regplace = null;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //endregion

        // region prev insurer adapter
        prevInsAdapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, databaseController.getInsurerList()) {
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
        spPrevInsurer.setAdapter(prevInsAdapter);

        //endregion

        // region ncb adapter
        ncbPerctAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ncb_percent));
        spNcbPercent.setAdapter(ncbPerctAdapter);
        //endregion
    }

    private void initWidgets() {
        etFirstRegDate = (EditText) findViewById(R.id.etFirstRegDate);
        btnCont = (Button) findViewById(R.id.btnCont);
        etPolicyExpDate = (EditText) findViewById(R.id.etPolicyExpDate);
        llNcb = (LinearLayout) findViewById(R.id.llNcb);
        acRegPlace = (AutoCompleteTextView) findViewById(R.id.acRegPlace);
        etManufactYearMonth = (EditText) findViewById(R.id.etManufactYearMonth);
        switchNcb = (Switch) findViewById(R.id.switchNcb);
        spPrevInsurer = (Spinner) findViewById(R.id.spPrevInsurer);
        spNcbPercent = (Spinner) findViewById(R.id.spNcbPercent);
    }

    private void setOnClickListener() {
        etFirstRegDate.setOnClickListener(datePickerDialog);
        etPolicyExpDate.setOnClickListener(datePickerDialog);
        etManufactYearMonth.setOnClickListener(datePickerDialog);
        btnCont.setOnClickListener(this);
        switchNcb.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCont:

                if (etFirstRegDate.getText().toString().equals("")) {
                    etFirstRegDate.requestFocus();
                    ShowError("Select first registration date", etFirstRegDate);
                    return;
                }
                if (etManufactYearMonth.getText().toString().equals("")) {
                    etManufactYearMonth.requestFocus();
                    ShowError("Select manufacturing date", etManufactYearMonth);
                    return;

                }
                if (etPolicyExpDate.getText().toString().equals("")) {
                    etPolicyExpDate.requestFocus();
                    ShowError("Select policy expiry date", etManufactYearMonth);
                    return;

                }
                if (spPrevInsurer.getSelectedItem().toString().contains("Select")) {
                    etPolicyExpDate.requestFocus();
                    ShowError("Select previous insurer", etManufactYearMonth);
                    return;
                }
                if (regplace == null || regplace.equals("")) {
                    etManufactYearMonth.requestFocus();
                    ShowError("Select Registration place", etManufactYearMonth);
                    return;

                }
                if (fromWhichClass.equals("BIKE")) {
                    setInputParametersReNewBike();
                    startActivity(new Intent(MotorPolicyDetailsActivity.this, CustomerDetailsActivity.class)
                            .putExtra(BIKE_INSURENCE, bikeRequestEntity));
                } else if (fromWhichClass.equals("CAR") || fromWhichClass.equals("FASTLANE")) {
                    setInputParametersReNewCar();
                    startActivity(new Intent(MotorPolicyDetailsActivity.this, CustomerDetailsActivity.class)
                            .putExtra(CAR_DETAIL, carRequestEntity));
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
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
    public void OnSuccess(APIResponse response, String message) {

    }

    @Override
    public void OnFailure(Throwable t) {

    }

    private int getYearDiffForNCB(String firstDay, String lastDay) {
        try {
            return getDiffYears(simpleDateFormat.parse(firstDay), simpleDateFormat.parse(lastDay));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    // region Date picker

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, MotorPolicyDetailsActivity.this);

            if (view.getId() == R.id.etFirstRegDate) {
                DateTimePicker.firstRegReNewDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etFirstRegDate.setText(currentDay);
                            etManufactYearMonth.setText(currentDay);
                            int yearDiff = getYearDiffForNCB(currentDay, etPolicyExpDate.getText().toString());
                            setNcbAdapter(yearDiff);

                        }
                    }


                });

            } else if (view.getId() == R.id.etPolicyExpDate) {
                DateTimePicker.policyExpDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etPolicyExpDate.setText(currentDay);
                            if (etFirstRegDate.getText().toString() != null && !etFirstRegDate.getText().toString().equals("")) {
                                int yearDiff = getYearDiffForNCB(currentDay, etFirstRegDate.getText().toString());
                                setNcbAdapter(yearDiff);
                            }
                        }
                    }
                });
            } else if (view.getId() == R.id.etManufactYearMonth) {
                if (etFirstRegDate.getText().toString().equals("") || etFirstRegDate.getText().toString() == null) {
                    DateTimePicker.firstRegNewDatePicker(view.getContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                                    if (view1.isShown()) {
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.set(year, monthOfYear, dayOfMonth);
                                        String currentDay = simpleDateFormat.format(calendar.getTime());
                                        etManufactYearMonth.setText(currentDay);
                                    }

                                }
                            });
                } else {
                    DateTimePicker.manufactDatePicker(view.getContext(), getYear(etFirstRegDate.getText().toString()),
                            getMonth(etFirstRegDate.getText().toString()), getDate(etFirstRegDate.getText().toString()),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                                    if (view1.isShown()) {
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.set(year, monthOfYear, dayOfMonth);
                                        String currentDay = simpleDateFormat.format(calendar.getTime());
                                        etManufactYearMonth.setText(currentDay);
                                    }
                                }
                            });
                }
            }

        }
    };

    private void setNcbAdapter(int yearDiff) {
        if (yearDiff >= 5) {
            spNcbPercent.setSelection(5);
        } else {
            spNcbPercent.setSelection(yearDiff);
        }
    }

    //endregion

    private int getMonth(String date) {
        String mon = "" + date.charAt(5) + date.charAt(6);
        return Integer.parseInt(mon);
    }

    private int getYear(String date) {
        String year = "" + date.charAt(0) + date.charAt(1) + date.charAt(2) + date.charAt(3);
        return Integer.parseInt(year);
    }

    private int getDate(String date) {
        String dat = "" + date.charAt(8) + date.charAt(9);
        return Integer.parseInt(dat);
    }

    public String changeDateFormat(String date) {
        String[] parts = date.split("/");
        String newDate = parts[2] + "-" + parts[1] + "-" + parts[0];
        return newDate;
    }

    private String getRegistrationNo(String city) {
        return "" + city.charAt(1) + city.charAt(2) + "-" + city.charAt(3) + city.charAt(4) + "-AA-1234";
    }

    private String formatRegistrationNo(String regNo) {
        return "" + regNo.charAt(0) + regNo.charAt(1) + "-" + regNo.charAt(2) + regNo.charAt(3) + "-" + regNo.charAt(4) + regNo.charAt(5) + "-" + regNo.charAt(6) + regNo.charAt(7) + regNo.charAt(8) + regNo.charAt(9);
    }

    private String getManufacturingDate(String manufac) {
        //final Calendar calendar = Calendar.getInstance();
        return "" + manufac.charAt(0) + manufac.charAt(1) + manufac.charAt(2) + manufac.charAt(3) + manufac.charAt(4) + manufac.charAt(5) + manufac.charAt(6) + manufac.charAt(7) + "01";
        //return manufac + "-" + calendar.getTime().getMonth() + "-" + calendar.getTime().getDate();

    }

    private void setInputParametersReNewCar() {
        if (fastLaneResponseEntity != null) {
            carRequestEntity.setVehicle_id(fastLaneResponseEntity.getVariant_Id());
            carRequestEntity.setRto_id(fastLaneResponseEntity.getVehicleCity_Id());
            carRequestEntity.setVehicle_manf_date(changeDateFormat(fastLaneResponseEntity.getRegistration_Date()));
            carRequestEntity.setRegistration_no(formatRegistrationNo(fastLaneResponseEntity.getRegistration_Number()));
        } else {
            //carRequestEntity.setVehicle_id(databaseController.getVariantID(spCarVarient.getSelectedItem().toString()));
            carRequestEntity.setRto_id(databaseController.getCityID(acRegPlace.getText().toString()));
            carRequestEntity.setVehicle_manf_date(getManufacturingDate(etManufactYearMonth.getText().toString()));
            if (carRequestEntity.getRegistration_no().equals(""))
                carRequestEntity.setRegistration_no(getRegistrationNo(acRegPlace.getText().toString()));
        }

        carRequestEntity.setVehicle_registration_date(etFirstRegDate.getText().toString());
        carRequestEntity.setPolicy_expiry_date(etPolicyExpDate.getText().toString());
        carRequestEntity.setPrev_insurer_id("" + databaseController.getInsurenceID(spPrevInsurer.getSelectedItem().toString()));

        carRequestEntity.setBirth_date("1992-01-01");
        carRequestEntity.setProduct_id(1);
        carRequestEntity.setSecret_key(Constants.SECRET_KEY);
        carRequestEntity.setClient_key(Constants.CLIENT_KEY);
        carRequestEntity.setExecution_async("yes");
        carRequestEntity.setVehicle_insurance_type("renew");


        carRequestEntity.setVehicle_registration_type("individual");
        carRequestEntity.setMethod_type("Premium");

        if (switchNcb.isChecked()) {
            carRequestEntity.setIs_claim_exists("yes");
            carRequestEntity.setVehicle_ncb_current("");
        } else {
            carRequestEntity.setIs_claim_exists("no");
            carRequestEntity.setVehicle_ncb_current(spNcbPercent.getSelectedItem().toString());
        }

        carRequestEntity.setElectrical_accessory("0");
        carRequestEntity.setNon_electrical_accessory("0");

        carRequestEntity.setIs_llpd("no");
        carRequestEntity.setIs_antitheft_fit("no");
        carRequestEntity.setVoluntary_deductible(0);
        carRequestEntity.setIs_external_bifuel("no");
        carRequestEntity.setPa_owner_driver_si("100000");
        carRequestEntity.setPa_named_passenger_si("0");
        carRequestEntity.setPa_unnamed_passenger_si("0");
        carRequestEntity.setPa_paid_driver_si("0");
        carRequestEntity.setVehicle_expected_idv(0);
        carRequestEntity.setFirst_name("");
        carRequestEntity.setMiddle_name("");
        carRequestEntity.setLast_name("");
        carRequestEntity.setMobile("");
        carRequestEntity.setEmail("");
        carRequestEntity.setCrn(0);
        carRequestEntity.setIp_address("");


    }

    private void setInputParametersReNewBike() {
        bikeRequestEntity.setBirth_date("1992-01-01");
        bikeRequestEntity.setProduct_id(10);
        //bikeRequestEntity.setVehicle_id(Integer.parseInt(databaseController.getBikeVarientID(bikeVarient)));
        bikeRequestEntity.setRto_id(databaseController.getCityID(regplace));
        bikeRequestEntity.setSecret_key(Constants.SECRET_KEY);
        bikeRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeRequestEntity.setExecution_async("yes");
        bikeRequestEntity.setVehicle_insurance_type("renew");
        bikeRequestEntity.setVehicle_manf_date(getManufacturingDate(etManufactYearMonth.getText().toString()));
        bikeRequestEntity.setVehicle_registration_date(etFirstRegDate.getText().toString());
        bikeRequestEntity.setPolicy_expiry_date(etPolicyExpDate.getText().toString());
        bikeRequestEntity.setPrev_insurer_id("" + databaseController.getInsurenceID(spPrevInsurer.getSelectedItem().toString()));
        bikeRequestEntity.setVehicle_registration_type("individual");
        bikeRequestEntity.setMethod_type("Premium");

        if (switchNcb.isChecked()) {
            bikeRequestEntity.setIs_claim_exists("yes");
            bikeRequestEntity.setVehicle_ncb_current("");
        } else {
            bikeRequestEntity.setIs_claim_exists("no");
            bikeRequestEntity.setVehicle_ncb_current(spNcbPercent.getSelectedItem().toString());
        }


        bikeRequestEntity.setRegistration_no(getRegistrationNo(regplace));
        bikeRequestEntity.setIs_llpd("no");
        bikeRequestEntity.setIs_antitheft_fit("no");
        bikeRequestEntity.setVoluntary_deductible(0);
        bikeRequestEntity.setIs_external_bifuel("no");
        bikeRequestEntity.setPa_owner_driver_si("100000");
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
}
