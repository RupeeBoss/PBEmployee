package com.android.policyboss.bikeinsurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.policyboss.R;
import com.android.policyboss.core.models.MototrQuotesEntity;
import com.android.policyboss.utility.Constants;

public class PremiumPopUpActivity extends AppCompatActivity {

    MototrQuotesEntity motoEntity;
    TextView txtPlanName, txtBasicOD, txtODdiscount, txtNEA, txtEA, txtBiFuel, txtAntiTheftDisc, txtVolDisc, txtAAIDisc, txtNCB, txtAgeDisc, txtProfDisc, txtUnderWriter, txtTotalODPremium;
    TextView txtCarModel, txtIDV, txtBasic3rdparty, txtUnNamedPA, txPACover, txtLegalLiability, txtBiFuelKit, txtTotalLiabPrem, txtNetPremium, txtServtax, txttotalPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_pop_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);
        initialize();

        if (getIntent().getParcelableExtra(Constants.QUOTE_ENTITY) != null) {
            motoEntity = getIntent().getParcelableExtra(Constants.QUOTE_ENTITY);
            getData();
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
        txtAgeDisc = (TextView) findViewById(R.id.txtAgeDisc);
        txtProfDisc = (TextView) findViewById(R.id.txtProfDisc);

        txtODdiscount = (TextView) findViewById(R.id.txtODdiscount);
        txtAntiTheftDisc  = (TextView) findViewById(R.id.txtAntiTheftDisc);
        txtUnderWriter = (TextView) findViewById(R.id.txtUnderWriter);
        txtTotalODPremium  = (TextView) findViewById(R.id.txtTotalODPremium);

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
    }


    private void getData() {
        if (motoEntity != null) {
            txtPlanName.setText("");
            txtBasicOD.setText("" + motoEntity.getBasicOwnDamage());
            txtODdiscount.setText("" + motoEntity.getODDiscount());
            txtNEA.setText("" + motoEntity.getNonElectricalAccessoriesPremium());
            txtEA.setText("" + motoEntity.getElectricalAcessoriesPremium());

            txtBiFuel.setText("" + motoEntity.getBiFuelKitLiabilityPremium());
            txtAntiTheftDisc.setText("" + motoEntity.getAntiTheftDiscount());
            txtVolDisc.setText("" + motoEntity.getVoluntaryDeductions());
            txtAAIDisc.setText("" + motoEntity.getElectricalAcessoriesPremium());
            txtNCB.setText("" + motoEntity.getElectricalAcessoriesPremium());

            txtAgeDisc.setText("" + motoEntity.getAgeDiscount());
            txtProfDisc.setText("" + motoEntity.getProfessionDiscount());
            txtUnderWriter.setText("" + motoEntity.getUnderwriterLoading());
            txtTotalODPremium.setText("" + motoEntity.getTotalODPremium());



            txtCarModel.setText("" );
            txtIDV.setText("" + motoEntity.getIDV());
            txtBasic3rdparty.setText("" + motoEntity.getThirdPartyLiablityPremium());
            txtUnNamedPA.setText("" +motoEntity.getPersonalAccidentCoverForUnammedPassenger());
            txPACover.setText("" + motoEntity.getPersonalAccidentCoverForOwnerDriver());

            txtLegalLiability.setText("" + motoEntity.getLegalLiabilityPremiumForPaidDriver());
            txtBiFuelKit.setText("" + motoEntity.getBiFuelKitLiabilityPremium());
            txtTotalLiabPrem.setText("" + motoEntity.getTotalLiabilityPremium());
            txtNetPremium.setText("" +motoEntity.getNetPayablePayablePremium());
            txtServtax.setText("" + motoEntity.getServiceTax());
            txttotalPremium.setText("" + motoEntity.getTotalPremium());



        }

    }

}
