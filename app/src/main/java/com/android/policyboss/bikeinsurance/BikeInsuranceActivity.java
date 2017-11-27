package com.android.policyboss.bikeinsurance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

public class BikeInsuranceActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String BIKE_INSURENCE = "BikeInsuranceActivity";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ImageView ivNewBike, ivRenewBike;
    CardView llBuyorRenew, cvNcb;
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

    String bikeVarient, regplace, prevIns;

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
        setDropDownSelectListeners();
    }

    private void setDropDownSelectListeners() {

        //region auto complete bike varient

        varientAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, databaseController.getBikeVarientList());
        acBikeVarient.setAdapter(varientAdapter);
        acBikeVarient.setThreshold(2);
        acBikeVarient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bikeVarient = varientAdapter.getItem(position).toString();
            }
        });
        /**
         * Unset the var whenever the user types. Validation will
         * then fail. This is how we enforce selecting from the list.
         */
        acBikeVarient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bikeVarient = null;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //endregion

        // region city adapter
        cityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, databaseController.getCity());
        acRegPlace.setAdapter(cityAdapter);
        acRegPlace.setThreshold(2);

        acRegPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                regplace = cityAdapter.getItem(position).toString();
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

        //region prev Insurer Adapter
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
    }

    private void init() {
        cvNcb = (CardView) findViewById(R.id.cvNcb);
        cvNcb.setVisibility(View.GONE);
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
        cvRenew.setVisibility(View.GONE);
        //List<String> bikevariant = databaseController.getBikeVarientList();
        // List<String> city = databaseController.getCity();


        ncbPerctAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ncb_percent));
        spNcbPercent.setAdapter(ncbPerctAdapter);


        Calendar calendar = Calendar.getInstance();
        String currentDay = simpleDateFormat.format(calendar.getTime());
        etPolicyExp.setText(currentDay);


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
                cvNcb.setVisibility(View.GONE);
                ivNewBike.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                llRenewBike.setVisibility(View.GONE);
                break;
            case R.id.ivRenewBike:
                cvRenew.setVisibility(View.VISIBLE);
                cvBuyorRenew.callOnClick();
                ivRenewBike.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                llRenewBike.setVisibility(View.VISIBLE);
                cvNcb.setVisibility(View.VISIBLE);
                break;
            case R.id.btnGetQuote:

                // region validating new bike
                if (bikeVarient == null || bikeVarient.equals("")) {
                    acBikeVarient.requestFocus();
                    acBikeVarient.setError("Select Bike Varient");
                    return;
                }
                if (etInvDate.getText().toString().equals("")) {
                    etInvDate.requestFocus();
                    etInvDate.setError("Enter Invoice Date");
                    return;
                }

                if (regplace == null || regplace.equals("")) {
                    acRegPlace.requestFocus();
                    acRegPlace.setError("Enter Registration Place");
                    return;
                }
                if (etManufactYearMonth.getText().toString().equals("")) {
                    etManufactYearMonth.requestFocus();
                    etManufactYearMonth.setError("Enter Manufacturing Date");
                    return;
                }
                //endregion

                if (llRenewBike.getVisibility() == View.VISIBLE) {

                    // region validating renew bike
                    if (etPolicyExp.getText().toString().equals("")) {
                        etPolicyExp.requestFocus();
                        etPolicyExp.setError("Enter Policy Expiry Date");
                        return;
                    }
                    if (spPrevInsurer.getSelectedItem().toString().contains("Select")) {
                        spPrevInsurer.requestFocus();
                        TextView errorText = (TextView) spPrevInsurer.getSelectedView();
                        errorText.setError("Select Prev Ins");
                        errorText.setTextColor(Color.RED);//just to highlight that this is an error
                        errorText.setText("Select Prev Insurer");
                        return;
                    }
                    //endregion

                    setInputParametersReNew();

                } else if (llRenewBike.getVisibility() == View.GONE) {
                    setInputParametersNew();
                }
                // setRequest();

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
        bikeRequestEntity.setVehicle_id(Integer.parseInt(databaseController.getBikeVarientID(bikeVarient)));
        bikeRequestEntity.setRto_id(databaseController.getCityID(regplace));
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
        bikeRequestEntity.setRegistration_no(getRegistrationNo(regplace));


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

    private void setInputParametersNew() {
        bikeRequestEntity.setBirth_date("1992-01-01");
        bikeRequestEntity.setProduct_id(10);
        bikeRequestEntity.setVehicle_id(Integer.parseInt(databaseController.getBikeVarientID(bikeVarient)));
        //bikeRequestEntity.setVehicle_id(50372);
        bikeRequestEntity.setRto_id(databaseController.getCityID(regplace));
        bikeRequestEntity.setSecret_key(Constants.SECRET_KEY);
        bikeRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeRequestEntity.setExecution_async("yes");
        bikeRequestEntity.setVehicle_insurance_type("new");
        bikeRequestEntity.setVehicle_manf_date(getManufacturingDate(etManufactYearMonth.getText().toString()));
        bikeRequestEntity.setVehicle_registration_date(etInvDate.getText().toString());
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

    private void setInputParametersReNew() {
        bikeRequestEntity.setBirth_date("1992-01-01");
        bikeRequestEntity.setProduct_id(10);
        bikeRequestEntity.setVehicle_id(Integer.parseInt(databaseController.getBikeVarientID(bikeVarient)));
        bikeRequestEntity.setRto_id(databaseController.getCityID(regplace));
        bikeRequestEntity.setSecret_key(Constants.SECRET_KEY);
        bikeRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeRequestEntity.setExecution_async("yes");
        bikeRequestEntity.setVehicle_insurance_type("renew");
        bikeRequestEntity.setVehicle_manf_date(getManufacturingDate(etManufactYearMonth.getText().toString()));
        bikeRequestEntity.setVehicle_registration_date(etInvDate.getText().toString());
        bikeRequestEntity.setPolicy_expiry_date(etPolicyExp.getText().toString());
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
                                etManufactYearMonth.setText(currentDay);
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
                                etManufactYearMonth.setText(currentDay);
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
                if (etInvDate.getText().toString().equals("") || etInvDate.getText().toString() == null) {
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
                    DateTimePicker.manufactDatePicker(view.getContext(), getYear(etInvDate.getText().toString()), getMonth(etInvDate.getText().toString()), getDate(etInvDate.getText().toString()),
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
        //final Calendar calendar = Calendar.getInstance();
        return "" + manufac.charAt(0) + manufac.charAt(1) + manufac.charAt(2) + manufac.charAt(3) + manufac.charAt(4) + manufac.charAt(5) + manufac.charAt(6) + manufac.charAt(7) + "01";
        //return manufac + "-" + calendar.getTime().getMonth() + "-" + calendar.getTime().getDate();

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

    private void calculateDateDiff(String startDate, String endDate) {
        Date startDt = null, enfDt = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDt = simpleDateFormat.parse(startDate);
            enfDt = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //milliseconds
        long different = enfDt.getTime() - startDt.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        Log.d("DIFF", "elapsedDays : " + elapsedDays);

    }

}
