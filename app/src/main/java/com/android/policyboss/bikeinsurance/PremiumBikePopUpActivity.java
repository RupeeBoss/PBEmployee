package com.android.policyboss.bikeinsurance;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.R;
import com.android.policyboss.core.models.InsurerEntity;
import com.android.policyboss.core.models.LiabilityEntity;
import com.android.policyboss.core.models.MototrQuotesEntity;
import com.android.policyboss.core.models.OwnDamageEntity;
import com.android.policyboss.core.models.PremiumBreakupEntity;
import com.android.policyboss.utility.Constants;

import java.util.List;

public class PremiumBikePopUpActivity extends AppCompatActivity implements View.OnClickListener {


    PremiumBreakupEntity premiumBreakupEntity;
    InsurerEntity insurerEntity;

    LiabilityEntity liabilityEntity;

    TextView txtPlanName, txtBasicOD, txtODdiscount, txtNEA, txtEA, txtBiFuel, txtAntiTheftDisc, txtVolDisc, txtAAIDisc, txtNCB, txtUnderWriter, txtTotalODPremium;
    TextView txtCarModel, txtIDV, txtBasic3rdparty, txtUnNamedPA, txPACover, txtLegalLiability, txtBiFuelKit, txtTotalLiabPrem, txtNetPremium, txtServtax, txttotalPremium, txtNamedPA, txtPAcoverPaidDriver;

   Button btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_bike_pop_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);
        initialize();

        if (getIntent().getParcelableExtra(Constants.Bike_QUOTE_PRIMIUM) != null) {
            premiumBreakupEntity = getIntent().getParcelableExtra(Constants.Bike_QUOTE_PRIMIUM);
            getData();
        }


        if (getIntent().getParcelableExtra(Constants.Bike_QUOTE_INSURER) != null) {
            insurerEntity = getIntent().getParcelableExtra(Constants.Bike_QUOTE_INSURER);

            if (insurerEntity != null) {
                txtPlanName.setText("" + insurerEntity.getInsurer_Name());
            }
        }
        if(getIntent().getStringExtra(Constants.IDV_DATA) != null)
        {
            txtIDV.setText("\u20B9 " + getIntent().getStringExtra(Constants.IDV_DATA).toString());
        }
    }

    private void initialize() {
        txtPlanName = (TextView) findViewById(R.id.txtPlanName);
        txtBasicOD = (TextView) findViewById(R.id.txtBasicOD);
        txtNEA = (TextView) findViewById(R.id.txtNEA);
        txtEA = (TextView) findViewById(R.id.txtEA);
        txtBiFuel = (TextView) findViewById(R.id.txtBiFuel);

        txtVolDisc = (TextView) findViewById(R.id.txtVolDisc);
        txtAAIDisc = (TextView) findViewById(R.id.txtAAIDisc);
        txtNCB = (TextView) findViewById(R.id.txtNCB);
//        txtAgeDisc = (TextView) findViewById(R.id.txtAgeDisc);
//        txtProfDisc = (TextView) findViewById(R.id.txtProfDisc);

        txtODdiscount = (TextView) findViewById(R.id.txtODdiscount);
        txtAntiTheftDisc = (TextView) findViewById(R.id.txtAntiTheftDisc);
        txtUnderWriter = (TextView) findViewById(R.id.txtUnderWriter);
        txtTotalODPremium = (TextView) findViewById(R.id.txtTotalODPremium);

        txtCarModel = (TextView) findViewById(R.id.txtCarModel);
        txtIDV = (TextView) findViewById(R.id.txtIDV);
        txtBasic3rdparty = (TextView) findViewById(R.id.txtBasic3rdparty);
        txtUnNamedPA = (TextView) findViewById(R.id.txtUnNamedPA);
        txPACover = (TextView) findViewById(R.id.txPACover);

        txtLegalLiability = (TextView) findViewById(R.id.txtLegalLiability);
        txtBiFuelKit = (TextView) findViewById(R.id.txtBiFuelKit);
        txtTotalLiabPrem = (TextView) findViewById(R.id.txtTotalLiabPrem);
        txtNetPremium = (TextView) findViewById(R.id.txtNetPremium);
        txtServtax = (TextView) findViewById(R.id.txtServtax);
        txttotalPremium = (TextView) findViewById(R.id.txttotalPremium);

        txtNamedPA = (TextView) findViewById(R.id.txtNamedPA);
        txtPAcoverPaidDriver = (TextView) findViewById(R.id.txtPAcoverPaidDriver);

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);

    }

    private void getData() {

        if (premiumBreakupEntity != null) {
            OwnDamageEntity own_damage = premiumBreakupEntity.getOwn_damage();
            liabilityEntity = premiumBreakupEntity.getLiability();
            if (own_damage != null) {

                txtBasicOD.setText("" + getRound(own_damage.getOd_basic()));
                txtODdiscount.setText("" + getRound(own_damage.getOd_disc()));
                txtNEA.setText("" + getRound(own_damage.getOd_non_elect_access()));
                txtEA.setText("" + getRound(own_damage.getOd_elect_access()) );
                txtBiFuel.setText("" + getRound( own_damage.getOd_cng_lpg()));                                                 // 05
                txtAntiTheftDisc.setText("" + getRound(own_damage.getOd_disc_anti_theft()) );

                txtVolDisc.setText("" + getRound(own_damage.getOd_disc_vol_deduct()) );
                txtAAIDisc.setText("" + getRound(own_damage.getOd_disc_aai()) );
                txtNCB.setText("" +  getRound(own_damage.getOd_disc_ncb()));
                txtUnderWriter.setText("" + getRound(own_damage.getOd_loading()) );
                txtTotalODPremium.setText("" + getRupeesRound(own_damage.getOd_final_premium()) );
                txtCarModel.setText("");

            }

            if (liabilityEntity != null) {

                txtBasic3rdparty.setText("" + getRound(liabilityEntity.getTp_basic()) );
                txPACover.setText("" +getRound(liabilityEntity.getTp_cover_owner_driver_pa()) );
                txtUnNamedPA.setText("" +getRound(liabilityEntity.getTp_cover_unnamed_passenger_pa()) );
                txtNamedPA.setText("" + getRound(liabilityEntity.getTp_cover_named_passenger_pa()) );
                txtPAcoverPaidDriver.setText("" + getRound(liabilityEntity.getTp_cover_paid_driver_pa()) );

                txtLegalLiability.setText("" + getRound(liabilityEntity.getTp_cover_paid_driver_ll()) );
                txtBiFuelKit.setText("" + getRound(liabilityEntity.getTp_cng_lpg()) );
                txtTotalLiabPrem.setText("" + getRupeesRound(liabilityEntity.getTp_final_premium()) );


            }
            txtNetPremium.setText("" + getRupeesRound(premiumBreakupEntity.getNet_premium()) );
            txtServtax.setText("" + getRupeesRound(premiumBreakupEntity.getService_tax()) );
            txttotalPremium.setText("" + getRupeesRound(premiumBreakupEntity.getFinal_premium()) );


        }


    }



    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnClose)
        {
            this.finish();
        }

    }

    private long getRound(String strText)
    {
        return  Math.round(Double.parseDouble(strText));
    }


    private String getRupeesRound(String strText)
   {
      return "\u20B9 " + Math.round(Double.parseDouble(strText));
   }


}
