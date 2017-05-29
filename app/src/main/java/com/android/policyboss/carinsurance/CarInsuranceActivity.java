package com.android.policyboss.carinsurance;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.animation.Animate;

public class CarInsuranceActivity extends BaseActivity implements View.OnClickListener {

    ImageView ivNewCar, ivRenewCar;
    CardView llBuyorRenew;
    CardView cvBuyorRenew, cvRegNo, cvInvDate;
    TextView tvBuyTiltle;

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
    }

    private void init() {
        ivNewCar = (ImageView) findViewById(R.id.ivNewCar);
        ivRenewCar = (ImageView) findViewById(R.id.ivRenewCar);
        llBuyorRenew = (CardView) findViewById(R.id.llBuyorRenew);
        cvBuyorRenew = (CardView) findViewById(R.id.cvBuyorRenew);
        cvRegNo = (CardView) findViewById(R.id.cvRegNo);
        cvInvDate = (CardView) findViewById(R.id.cvInvDate);
        tvBuyTiltle = (TextView) findViewById(R.id.tvBuyTiltle);


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

}
