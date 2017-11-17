package com.android.policyboss.bikeinsurance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.response.BikeUniqueResponse;
import com.android.policyboss.personaldetail.CustomerDetailsActivity;
import com.android.policyboss.utility.Constants;
import com.android.policyboss.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;

public class BikeInsuranceActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String BIKE_INSURENCE = "BikeInsuranceActivity";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ImageView ivNewBike, ivRenewBike;
    CardView llBuyorRenew;
    CardView cvBuyorRenew, cvInvDate;
    TextView tvBuyTiltle;
    DatabaseController databaseController;
    EditText etInvDate, etPolicyExp, etManufactYearMonth;
    CardView cvNew, cvRenew;
    LinearLayout llRenewBike, llNcb;
    Spinner spNcbPercent, spPrevInsurer, spManufactureYear;
    Switch switchNcb;
    Button btnGetQuote;
    AutoCompleteTextView acBikeVarient, acRegPlace;
    boolean isVarientSelected, isPlaceSelected;
    ArrayAdapter<String> cityAdapter, varientAdapter, ncbPerctAdapter, prevInsAdapter;
    BikeRequestEntity bikeRequestEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_insurance);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Bike Insurance");
        realm = Realm.getDefaultInstance();
        databaseController = new DatabaseController(this, realm);
        //collapsingToolbar.setExpandedTitleTextColor(ColorStateList.valueOf(getResources().getColor(R.color.application_secondary_text_color)));
        init();
        setClickListeners();
    }

    private void init() {
        bikeRequestEntity = new BikeRequestEntity();
        etManufactYearMonth = (EditText) findViewById(R.id.etManufactYearMonth);
        btnGetQuote = (Button) findViewById(R.id.btnGetQuote);
        etPolicyExp = (EditText) findViewById(R.id.etPolicyExp);
        spPrevInsurer = (Spinner) findViewById(R.id.spPrevInsurer);
        spNcbPercent = (Spinner) findViewById(R.id.spNcbPercent);
        llNcb = (LinearLayout) findViewById(R.id.llNcb);
        switchNcb = (Switch) findViewById(R.id.switchNcb);
        ivNewBike = (ImageView) findViewById(R.id.ivNewBike);
        ivRenewBike = (ImageView) findViewById(R.id.ivRenewBike);
        llBuyorRenew = (CardView) findViewById(R.id.llBuyorRenew);
        cvBuyorRenew = (CardView) findViewById(R.id.cvBuyorRenew);
        cvInvDate = (CardView) findViewById(R.id.cvInvDate);
        tvBuyTiltle = (TextView) findViewById(R.id.tvBuyTiltle);
        etInvDate = (EditText) findViewById(R.id.etInvDate);
        cvRenew = (CardView) findViewById(R.id.cvRenew);
        llRenewBike = (LinearLayout) findViewById(R.id.llRenewBike);
        acBikeVarient = (AutoCompleteTextView) findViewById(R.id.acBikeVarient);
        acRegPlace = (AutoCompleteTextView) findViewById(R.id.acRegPlace);

        //List<String> bikevariant = databaseController.getBikeVarientList();
        // List<String> city = databaseController.getCity();


        // region  bike varient adapter
        varientAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, databaseController.getBikeVarientList());
        acBikeVarient.setAdapter(varientAdapter);
        acBikeVarient.setThreshold(2);

        //endregion

        // region city adapter
        cityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, databaseController.getCity());
        acRegPlace.setAdapter(cityAdapter);
        acRegPlace.setThreshold(2);
        //endregion

        ncbPerctAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ncb_percent));
        spNcbPercent.setAdapter(ncbPerctAdapter);

        prevInsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, databaseController.getInsurerList());
        spPrevInsurer.setAdapter(prevInsAdapter);


    }

    private void setClickListeners() {
        ivNewBike.setOnClickListener(this);
        ivRenewBike.setOnClickListener(this);
        llBuyorRenew.setOnClickListener(this);
        cvBuyorRenew.setOnClickListener(this);
        etInvDate.setOnClickListener(datePickerDialog);
        etPolicyExp.setOnClickListener(datePickerDialog);
        etManufactYearMonth.setOnClickListener(datePickerDialog);
        switchNcb.setOnCheckedChangeListener(this);
        btnGetQuote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvBuyorRenew:
                if (llBuyorRenew.getVisibility() == View.VISIBLE) {
                    tvBuyTiltle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
                } else {
                    tvBuyTiltle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
                }

                break;
            case R.id.ivNewBike:
                cvRenew.setVisibility(View.VISIBLE);
                cvBuyorRenew.callOnClick();
                ivNewBike.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                llRenewBike.setVisibility(View.GONE);
                break;
            case R.id.ivRenewBike:
                cvRenew.setVisibility(View.VISIBLE);
                cvBuyorRenew.callOnClick();
                ivRenewBike.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                llRenewBike.setVisibility(View.VISIBLE);

                break;
            case R.id.btnGetQuote:

                if (etInvDate.getText().toString().equals("")) {
                    etInvDate.requestFocus();
                    etInvDate.setError("Enter Invoice Date");
                    return;
                }
                if (acBikeVarient.getText().toString().equals("") && databaseController.getBikeVarientID(acBikeVarient.getText().toString().trim()) != "") {
                    acBikeVarient.requestFocus();
                    acBikeVarient.setError("Select Bike Varient");
                    return;
                }
                if (acRegPlace.getText().toString().equals("") && databaseController.getBikeVarientID(acBikeVarient.getText().toString().trim()) != "") {
                    acRegPlace.requestFocus();
                    acRegPlace.setError("Enter Registration Place");
                    return;
                }
                if (etManufactYearMonth.getText().toString().equals("")) {
                    etManufactYearMonth.requestFocus();
                    etManufactYearMonth.setError("Enter Manufacturing Date");
                    return;
                }

                if (llRenewBike.getVisibility() == View.VISIBLE) {
                    if (etPolicyExp.getText().toString().equals("")) {
                        etPolicyExp.requestFocus();
                        etPolicyExp.setError("Enter Policy Expiry Date");
                        return;
                    }
                }
                setRequest();

                // showDialog("Initiate quotes..");
                // new BikeController(this).getBikeQuote(bikeRequestEntity, this);

                startActivity(new Intent(BikeInsuranceActivity.this, CustomerDetailsActivity.class)
                        .putExtra(BIKE_INSURENCE, bikeRequestEntity));

                break;
        }
    }


    //region create bike request
    private void setRequest() {

        bikeRequestEntity.setProduct_id(10);
        bikeRequestEntity.setVehicle_registration_date(etInvDate.getText().toString());
        bikeRequestEntity.setVehicle_id(Integer.parseInt(databaseController.getBikeVarientID(acBikeVarient.getText().toString())));
        bikeRequestEntity.setRto_id(databaseController.getCityID(acRegPlace.getText().toString()));
        bikeRequestEntity.setVehicle_manf_date(etManufactYearMonth.getText().toString());
        bikeRequestEntity.setVehicle_insurance_type("new");
        bikeRequestEntity.setSecret_key(Constants.SECRET_KEY);
        bikeRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeRequestEntity.setExecution_async("yes");
        bikeRequestEntity.setMethod_type("Premium");
        bikeRequestEntity.setVehicle_insurance_type("Premium");
        bikeRequestEntity.setIs_llpd("no");
        bikeRequestEntity.setIs_external_bifuel("no");
        bikeRequestEntity.setVehicle_registration_type("individual");
        bikeRequestEntity.setRegistration_no(getRegistrationNo(acRegPlace.getText().toString()));


        if (llRenewBike.getVisibility() == View.VISIBLE) {
            bikeRequestEntity.setVehicle_insurance_type("renew");
            bikeRequestEntity.setPolicy_expiry_date(etPolicyExp.getText().toString());
            bikeRequestEntity.setPrev_insurer_id(String.valueOf(databaseController.getInsurenceID((String) spPrevInsurer.getSelectedItem().toString())));

            if (switchNcb.isChecked()) {
                bikeRequestEntity.setIs_claim_exists("no");
            } else {
                bikeRequestEntity.setIs_claim_exists("yes");
                bikeRequestEntity.setVehicle_ncb_current(spNcbPercent.getSelectedItem().toString());
            }
        }
    }

    //endregion

    // region Date picker

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, BikeInsuranceActivity.this);

            if (view.getId() == R.id.etInvDate) {
                if (llRenewBike.getVisibility() == View.VISIBLE) {
                    DateTimePicker.firstRegReNewDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                            if (view1.isShown()) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                String currentDay = simpleDateFormat.format(calendar.getTime());
                                etInvDate.setText(currentDay);
                            }
                        }
                    });
                } else {
                    DateTimePicker.firstRegNewDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                            if (view1.isShown()) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                String currentDay = simpleDateFormat.format(calendar.getTime());
                                etInvDate.setText(currentDay);
                            }
                        }
                    });
                }


            } else if (view.getId() == R.id.etPolicyExp) {
                DateTimePicker.policyExpDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etPolicyExp.setText(currentDay);
                        }
                    }
                });
            } else if (view.getId() == R.id.etManufactYearMonth) {
                DateTimePicker.manufactDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
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
    };

    //endregion

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
                break;
        }

    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof BikeUniqueResponse) {
            //TODO : redirect to customer detail
            startActivity(new Intent(BikeInsuranceActivity.this, CustomerDetailsActivity.class)
                    .putExtra(BIKE_INSURENCE, bikeRequestEntity));
            // startActivity(new Intent(BikeInsuranceActivity.this, BikeQuoteActivity.class));
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private String getRegistrationNo(String city) {
        return "" + city.charAt(1) + city.charAt(2) + "-" + city.charAt(3) + city.charAt(4) + "-AA-1234";
    }

    private String getManufacturingDate(String manufac) {
        final Calendar calendar = Calendar.getInstance();
        return manufac + "-" + calendar.getTime().getMonth() + "-" + calendar.getTime().getDate();

    }
}
