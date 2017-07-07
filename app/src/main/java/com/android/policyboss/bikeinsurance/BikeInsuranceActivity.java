package com.android.policyboss.bikeinsurance;

import android.app.DatePickerDialog;
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

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.utility.Constants;
import com.android.policyboss.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;

public class BikeInsuranceActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ImageView ivNewBike, ivRenewBike;
    CardView llBuyorRenew;
    CardView cvBuyorRenew, cvInvDate;
    TextView tvBuyTiltle;
    DatabaseController databaseController;
    EditText etInvDate, etPolicyExp;
    CardView cvNew, cvRenew;
    LinearLayout llRenewBike, llNcb;
    Spinner spNcbPercent, spPrevInsurer, spManufactureYear;
    Switch switchNcb;
    Button btnGetQuote;
    AutoCompleteTextView acBikeVarient, acRegPlace;
    boolean isVarientSelected, isPlaceSelected;
    ArrayAdapter<String> cityAdapter, varientAdapter, ncbPerctAdapter, prevInsAdapter;

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
        //cvNew = (CardView) findViewById(R.id.cvNew);
        cvRenew = (CardView) findViewById(R.id.cvRenew);
        llRenewBike = (LinearLayout) findViewById(R.id.llRenewBike);
        acBikeVarient = (AutoCompleteTextView) findViewById(R.id.acBikeVarient);
        acRegPlace = (AutoCompleteTextView) findViewById(R.id.acRegPlace);


        // region  bike varient adapter
        varientAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, databaseController.getBikeVarientList());
        //preferedCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spPreferedCity.setAdapter(preferedCityAdapter);
        acBikeVarient.setAdapter(varientAdapter);
        acBikeVarient.setThreshold(2);
        //endregion

        // region city adapter
        cityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, databaseController.getCity());
        //preferedCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spPreferedCity.setAdapter(preferedCityAdapter);
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
        switchNcb.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvBuyorRenew:
                if (llBuyorRenew.getVisibility() == View.VISIBLE) {
                    // Animate.SlideUpAnimation(llBuyorRenew);
                    //llBuyorRenew.setVisibility(View.GONE);
                    tvBuyTiltle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
                } else {
                    //Animate.SlideDownAnimation(llBuyorRenew);
                    //llBuyorRenew.setVisibility(View.VISIBLE);
                    tvBuyTiltle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
                }

                break;
            case R.id.ivNewBike:
                cvRenew.setVisibility(View.VISIBLE);
                cvBuyorRenew.callOnClick();
                ivNewBike.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                llRenewBike.setVisibility(View.GONE);
                //Animate.translateUpAnimation(cvInvDate, 1.0f, 1.0f, 0.0f, -llBuyorRenew.getHeight());
                //cvInvDate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
                break;
            case R.id.ivRenewBike:
                cvRenew.setVisibility(View.VISIBLE);
                cvBuyorRenew.callOnClick();
                ivRenewBike.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                llRenewBike.setVisibility(View.VISIBLE);
                //Animate.translateUpAnimation(cvRegNo, 1.0f, 1.0f, 0.0f, -llBuyorRenew.getHeight());
                //cvRegNo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));

                break;
        }
    }

    // region Date picker

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, BikeInsuranceActivity.this);
            DateTimePicker.showPrevSixMonthDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if (view.isShown()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        String currentDay = simpleDateFormat.format(calendar.getTime());
                        etInvDate.setText(currentDay);
                        /*quoteRequestEntity.setDontRem(false);
                        quoteRequestEntity.setRenew(false);
                        quoteRequestEntity.setNew(true);
                        quoteRequestEntity.setDateofPurchaseofCar(etInvDate.getText().toString());
                        startActivity(new Intent(CarInsuranceActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                        //etDate.setTag(R.id.et_date, calendar.getTime());*/
                    }
                }
            });
        }
    };

    //endregion
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switchNcb:
                if (isChecked) {
                    llNcb.setVisibility(View.VISIBLE);
                    llNcb.requestFocus();
                } else {
                    llNcb.setVisibility(View.GONE);
                }
                break;
        }

    }

}
