package com.android.policyboss.carinsurance;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.fastlane.FastlaneController;
import com.android.policyboss.core.response.FastLaneResponse;

public class CarInsuranceActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    ImageView ivNewCar, ivRenewCar;
    CardView llBuyorRenew;
    CardView cvBuyorRenew, cvRegNo, cvInvDate;
    TextView tvBuyTiltle, txtDontRem;

    EditText etRenewRegNo, etInvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_insurance);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Car Insurance");
        collapsingToolbar.setExpandedTitleTextColor(ColorStateList.valueOf(getResources().getColor(R.color.application_secondary_text_color)));

        init();
        setClickListeners();

    }

    private void setClickListeners() {
        ivNewCar.setOnClickListener(this);
        ivRenewCar.setOnClickListener(this);
        llBuyorRenew.setOnClickListener(this);
        cvBuyorRenew.setOnClickListener(this);
        txtDontRem.setOnClickListener(this);
        etRenewRegNo.addTextChangedListener(renewtextWatcher);
    }

    TextWatcher renewtextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count == 10) {
                Toast.makeText(CarInsuranceActivity.this, "Digit reached :" + count, Toast.LENGTH_SHORT).show();
                new FastlaneController(CarInsuranceActivity.this).getCarDetails(s.toString(), CarInsuranceActivity.this);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void init() {
        ivNewCar = (ImageView) findViewById(R.id.ivNewCar);
        ivRenewCar = (ImageView) findViewById(R.id.ivRenewCar);
        llBuyorRenew = (CardView) findViewById(R.id.llBuyorRenew);
        cvBuyorRenew = (CardView) findViewById(R.id.cvBuyorRenew);
        cvRegNo = (CardView) findViewById(R.id.cvRegNo);
        cvInvDate = (CardView) findViewById(R.id.cvInvDate);
        tvBuyTiltle = (TextView) findViewById(R.id.tvBuyTiltle);
        txtDontRem = (TextView) findViewById(R.id.txtDontRem);
        etRenewRegNo = (EditText) findViewById(R.id.etRenewRegNo);
        etInvDate = (EditText) findViewById(R.id.etInvDate);

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
            case R.id.ivNewCar:
                cvBuyorRenew.callOnClick();
                ivNewCar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                cvRegNo.setVisibility(View.GONE);
                cvInvDate.setVisibility(View.VISIBLE);
                //Animate.translateUpAnimation(cvInvDate, 1.0f, 1.0f, 0.0f, -llBuyorRenew.getHeight());
                //cvInvDate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
                break;
            case R.id.ivRenewCar:
                cvBuyorRenew.callOnClick();
                ivRenewCar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                cvInvDate.setVisibility(View.GONE);
                cvRegNo.setVisibility(View.VISIBLE);
                //Animate.translateUpAnimation(cvRegNo, 1.0f, 1.0f, 0.0f, -llBuyorRenew.getHeight());
                //cvRegNo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));

                break;
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        if (response instanceof FastLaneResponse) {
            //startActivity(new Intent(this,));
        }
    }

    @Override
    public void OnFailure(Throwable t) {

    }
}
