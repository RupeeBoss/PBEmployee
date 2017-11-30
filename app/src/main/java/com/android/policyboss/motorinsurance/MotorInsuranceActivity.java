package com.android.policyboss.motorinsurance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.carinsurance.FastLaneCarDetails;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.controller.fastlane.FastlaneController;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.personaldetail.CustomerDetailsActivity;
import com.android.policyboss.utility.Constants;
import com.android.policyboss.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;

public class MotorInsuranceActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {
    public static final String BIKE_INSURENCE = "BikeInsuranceActivity";
    public static final String CAR_DETAIL = "CarDetailsActivity.class";
    String insuranceType;
    ImageView backdrop;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    AutoCompleteTextView autoCarMakeModel, acRegPlace;
    EditText etRenewRegNo, etExternallyFitted, etFirstRegDate, etManufactYearMonth;
    Button btnGEtDetails, btnCont;
    TextView tvDontRem, tvNew, tvCvHeader;
    Spinner spCarFuelType, spCarVarient;
    CardView cvMotorDetails;
    TextInputLayout tilExternal;
    List<String> makeModelList, fuelList, variantList;
    ArrayAdapter<String> makeModelAdapter, varientAdapter, fuelAdapter, cityAdapter;
    DatabaseController databaseController;
    LinearLayout llVarient, llPolicyDetails;
    int modelId = 0, varientId;
    BikeRequestEntity bikeRequestEntity, carRequestEntity;
    String regplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_insurance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        insuranceType = getInsuranceType();
        if (insuranceType.equals("BIKE")) {
            getSupportActionBar().setTitle("Bike Insurance");
        } else {
            getSupportActionBar().setTitle("Car Insurance");
        }
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

                    fuelList = databaseController.getFuelTypeByModelId(modelId);
                    variantList = databaseController.getVariantbyModelID(modelId);

                    spCarFuelType.setVisibility(View.VISIBLE);
                    spCarVarient.setVisibility(View.VISIBLE);

                    fuelAdapter = new
                            ArrayAdapter(MotorInsuranceActivity.this, android.R.layout.simple_list_item_1, fuelList);
                    spCarFuelType.setAdapter(fuelAdapter);

                    varientAdapter = new
                            ArrayAdapter(MotorInsuranceActivity.this, android.R.layout.simple_list_item_1, variantList);
                    spCarVarient.setAdapter(varientAdapter);
                }
            });
            //endregion


        } else if (insuranceType.equals("BIKE")) {
            makeModelList = databaseController.getBikeMakeModel();
            makeModelAdapter = new
                    ArrayAdapter(MotorInsuranceActivity.this, android.R.layout.simple_list_item_1, makeModelList);
            autoCarMakeModel.setAdapter(makeModelAdapter);

            // region  Auto Complete bike make
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
                    spCarVarient.setAdapter(varientAdapter);

                }
            });
            autoCarMakeModel.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    modelId = 0;
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            //endregion

        }


        // region city adapter
        cityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, databaseController.getCity());
        acRegPlace.setAdapter(cityAdapter);
        acRegPlace.setThreshold(2);

        acRegPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                regplace = cityAdapter.getItem(position).toString();
                hideKeyBoard(acRegPlace, MotorInsuranceActivity.this);
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

    }

    private void changeTextLabels() {
        if (insuranceType.equals("CAR")) {
            backdrop.setImageDrawable(getResources().getDrawable(R.drawable.car_images));
            autoCarMakeModel.setHint("Which car do you drive ?");
        } else {
            backdrop.setImageDrawable(getResources().getDrawable(R.drawable.bike_homepage_banner));
            tvNew.setText(getResources().getString(R.string.new_bike));
            tvNew.setPaintFlags(tvNew.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            tvCvHeader.setText(getResources().getString(R.string.which_bike_header));
            autoCarMakeModel.setHint("Which bike do you drive ?");
        }
    }

    private void initView() {
        bikeRequestEntity = new BikeRequestEntity();
        carRequestEntity = new BikeRequestEntity();
        acRegPlace = (AutoCompleteTextView) findViewById(R.id.acRegPlace);
        etFirstRegDate = (EditText) findViewById(R.id.etFirstRegDate);
        etManufactYearMonth = (EditText) findViewById(R.id.etManufactYearMonth);
        llVarient = (LinearLayout) findViewById(R.id.llVarient);
        llPolicyDetails = (LinearLayout) findViewById(R.id.llPolicyDetails);
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
        tvDontRem.requestFocus();

    }

    private void setListeners() {
        etManufactYearMonth.setOnClickListener(datePickerDialog);
        etFirstRegDate.setOnClickListener(datePickerDialog);
        btnCont.setOnClickListener(this);
        btnGEtDetails.setOnClickListener(this);
        tvDontRem.setOnClickListener(this);
        tvNew.setOnClickListener(this);
        etRenewRegNo.addTextChangedListener(renewtextWatcher);
    }

    // region Date picker

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, MotorInsuranceActivity.this);

            if (view.getId() == R.id.etFirstRegDate) {
                DateTimePicker.firstRegNewDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etFirstRegDate.setText(currentDay);
                            etManufactYearMonth.setText(currentDay);
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
                    DateTimePicker.manufactDatePicker(view.getContext(), getYear(etFirstRegDate.getText().toString()), getMonth(etFirstRegDate.getText().toString()), getDate(etFirstRegDate.getText().toString()),
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

    //endregion

    public String getInsuranceType() {
        if (getIntent().hasExtra(Constants.CAR)) {
            return "CAR";
        } else if (getIntent().hasExtra(Constants.BIKE)) {
            return "BIKE";
        }
        return "";
    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvDontRem:
                tvNew.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                tvDontRem.setTextColor(getResources().getColor(R.color.nav_blue));
                cvMotorDetails.setVisibility(View.VISIBLE);
                llPolicyDetails.setVisibility(View.GONE);
                break;
            case R.id.tvNew:
                tvNew.setTextColor(getResources().getColor(R.color.nav_blue));
                tvDontRem.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                cvMotorDetails.setVisibility(View.VISIBLE);
                llPolicyDetails.setVisibility(View.VISIBLE);
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

                if (autoCarMakeModel.getVisibility() == View.VISIBLE) {
                    if (modelId <= 0) {
                        autoCarMakeModel.requestFocus();
                        ShowError("Select Model",autoCarMakeModel);
                        return;

                    }
                }

                if (insuranceType.equals("BIKE")) {
                    varientId = databaseController.getBikeVariantID(getVarient(spCarVarient.getSelectedItem().toString()), getModel(autoCarMakeModel.getText().toString()), getMake(autoCarMakeModel.getText().toString()));
                    if (llPolicyDetails.getVisibility() == View.VISIBLE) {// New Bike
                        setInputParametersNewBike();
                        startActivity(new Intent(MotorInsuranceActivity.this, CustomerDetailsActivity.class)
                                .putExtra(BIKE_INSURENCE, bikeRequestEntity));
                    } else {    //Dont Rem Bike Number
                        bikeRequestEntity.setVehicle_id(varientId);
                        startActivity(new Intent(this, MotorPolicyDetailsActivity.class).putExtra(Constants.BIKE, bikeRequestEntity));
                    }
                } else if (insuranceType.equals("CAR")) {
                    varientId = databaseController.getVariantID(getVarient(spCarVarient.getSelectedItem().toString()), getModel(autoCarMakeModel.getText().toString()), getMake(autoCarMakeModel.getText().toString()));
                    if (llPolicyDetails.getVisibility() == View.VISIBLE) { // New CAR
                        setInputParametersNewCAR();
                        startActivity(new Intent(MotorInsuranceActivity.this, CustomerDetailsActivity.class)
                                .putExtra(CAR_DETAIL, bikeRequestEntity));
                    } else {   //Dont Rem CAr Number
                        carRequestEntity.setVehicle_id(varientId);
                        startActivity(new Intent(this, MotorPolicyDetailsActivity.class).putExtra(Constants.CAR, carRequestEntity));
                    }
                }

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

                Constants.hideKeyBoard(backdrop, MotorInsuranceActivity.this);
                showDialog();
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
            // quoteRequestEntity.setNew(false);
            // quoteRequestEntity.setRenew(true);
            //quoteRequestEntity.setDontRem(false);
            //quoteRequestEntity.setRegistrationNumber(etRenewRegNo.getText().toString());
            startActivity(new Intent(this, FastLaneCarDetails.class)
                    .putExtra(Constants.FASTLANE_DATA, ((FastLaneResponse) response).getFLResponse()));
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
        tvDontRem.performClick();
    }

    public String getModel(String makeModel) {
        String[] parts = makeModel.split(",");
        return parts[1];
    }

    public String getMake(String makeModel) {
        String[] parts = makeModel.split(",");
        return parts[0];
    }

    public String getVarient(String varientWithCC) {
        String[] parts = varientWithCC.split(",");
        return parts[0];
    }

    private String getRegistrationNo(String city) {
        return "" + city.charAt(1) + city.charAt(2) + "-" + city.charAt(3) + city.charAt(4) + "-AA-1234";
    }

    private String getManufacturingDate(String manufac) {
        //final Calendar calendar = Calendar.getInstance();
        return "" + manufac.charAt(0) + manufac.charAt(1) + manufac.charAt(2) + manufac.charAt(3) + manufac.charAt(4) + manufac.charAt(5) + manufac.charAt(6) + manufac.charAt(7) + "01";
        //return manufac + "-" + calendar.getTime().getMonth() + "-" + calendar.getTime().getDate();

    }

    private void setInputParametersNewBike() {
        bikeRequestEntity.setBirth_date("1992-01-01");
        bikeRequestEntity.setProduct_id(10);
        bikeRequestEntity.setVehicle_id(varientId);
        //bikeRequestEntity.setVehicle_id(50372);
        bikeRequestEntity.setRto_id(databaseController.getCityID(regplace));
        bikeRequestEntity.setSecret_key(Constants.SECRET_KEY);
        bikeRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeRequestEntity.setExecution_async("yes");
        bikeRequestEntity.setVehicle_insurance_type("new");
        bikeRequestEntity.setVehicle_manf_date(getManufacturingDate(etManufactYearMonth.getText().toString()));
        bikeRequestEntity.setVehicle_registration_date(etFirstRegDate.getText().toString());
        bikeRequestEntity.setPolicy_expiry_date("");
        bikeRequestEntity.setPrev_insurer_id("");
        bikeRequestEntity.setVehicle_registration_type("individual");
        bikeRequestEntity.setVehicle_ncb_current("0");
        bikeRequestEntity.setIs_claim_exists("no");
        bikeRequestEntity.setMethod_type("Premium");
        bikeRequestEntity.setElectrical_accessory("0");
        bikeRequestEntity.setNon_electrical_accessory("0");
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

    private void setInputParametersNewCAR() {
        bikeRequestEntity.setBirth_date("1992-01-01");
        bikeRequestEntity.setProduct_id(1);
        bikeRequestEntity.setVehicle_id(varientId);
        bikeRequestEntity.setRto_id(databaseController.getCityID(regplace));
        bikeRequestEntity.setSecret_key(Constants.SECRET_KEY);
        bikeRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeRequestEntity.setExecution_async("yes");
        bikeRequestEntity.setVehicle_insurance_type("new");
        bikeRequestEntity.setVehicle_manf_date(getManufacturingDate(etManufactYearMonth.getText().toString()));
        bikeRequestEntity.setVehicle_registration_date(etFirstRegDate.getText().toString());
        bikeRequestEntity.setPolicy_expiry_date("");
        bikeRequestEntity.setPrev_insurer_id("");
        bikeRequestEntity.setVehicle_registration_type("individual");
        bikeRequestEntity.setVehicle_ncb_current("");
        bikeRequestEntity.setIs_claim_exists("yes");
        bikeRequestEntity.setMethod_type("Premium");
        bikeRequestEntity.setElectrical_accessory("0");
        bikeRequestEntity.setNon_electrical_accessory("0");
        bikeRequestEntity.setRegistration_no(getRegistrationNo(regplace));
        bikeRequestEntity.setIs_llpd("no");
        bikeRequestEntity.setIs_antitheft_fit("no");
        bikeRequestEntity.setVoluntary_deductible(0);
        bikeRequestEntity.setIs_external_bifuel("no");
        bikeRequestEntity.setPa_owner_driver_si("100000");
        bikeRequestEntity.setPa_named_passenger_si("0");
        bikeRequestEntity.setPa_unnamed_passenger_si("0");
        bikeRequestEntity.setPa_paid_driver_si("0");
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
