package com.android.policyboss.motorinsurance;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.core.controller.car.CarController;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.models.AppliedAddonsPremiumBreakup;
import com.android.policyboss.core.models.CommonAddonEntity;
import com.android.policyboss.core.models.MobileAddOn;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.models.SummaryEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.requestEntity.SaveAddOnRequestEntity;
import com.android.policyboss.core.response.BikePremiumResponse;
import com.android.policyboss.core.response.SaveAddOnResponse;
import com.android.policyboss.utility.Constants;
import com.android.policyboss.webview.CommonWebViewActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class BikeQuoteActivity extends BaseActivity implements IResponseSubcriber {

    public static int BIKE_RESULT = 1001;
    public static int CAR_RESULT = 1002;

    BikePremiumResponse bikePremiumResponse;
    RecyclerView bikeQuoteRecycler;
    BikeQuoteAdapter mAdapter;
    BikeRequestEntity bikeRequestEntity;
    BikeRequestEntity carRequestEntity;
    Menu menuAddon;
    String[] addOns;
    DatabaseController databaseController;
    ImageView webViewLoader;
    List<MobileAddOn> listMobileAddOn;

    //SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_quote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.filter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getIntent().hasExtra("BIKE")) {

                    if (bikePremiumResponse.getSummary().getPB_CRN() != null
                            && !bikePremiumResponse.getSummary().getPB_CRN().equals(""))
                        bikeRequestEntity.setCrn(Integer.parseInt(bikePremiumResponse.getSummary().getPB_CRN()));

                    startActivityForResult(new Intent(BikeQuoteActivity.this, ModifyQuotesActivity.class)
                            .putExtra("BIKE", bikeRequestEntity), BIKE_RESULT);

                } else if (getIntent().hasExtra("CAR")) {

                    if (bikePremiumResponse.getSummary().getPB_CRN() != null
                            && !bikePremiumResponse.getSummary().getPB_CRN().equals(""))
                        carRequestEntity.setCrn(Integer.parseInt(bikePremiumResponse.getSummary().getPB_CRN()));

                    startActivityForResult(new Intent(BikeQuoteActivity.this, ModifyQuotesActivity.class)
                            .putExtra("CAR", carRequestEntity), CAR_RESULT);
                }
            }
        });

        if (getIntent().hasExtra("BIKE")) {
            bikeRequestEntity = getIntent().getParcelableExtra("BIKE");
        } else if (getIntent().hasExtra("CAR")) {
            carRequestEntity = getIntent().getParcelableExtra("CAR");
            getSupportActionBar().setTitle("CAR INSURANCE");
        }

        initialize();

        realm = Realm.getDefaultInstance();
        databaseController = new DatabaseController(this, realm);

        listMobileAddOn = new ArrayList<MobileAddOn>();
        bikePremiumResponse = new BikePremiumResponse();
        mAdapter = new BikeQuoteAdapter(this, bikePremiumResponse);
        bikeQuoteRecycler.setAdapter(mAdapter);

        /*mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchQuotes();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });*/

        showDialog();
        fetchQuotes();
    }

    private void fetchQuotes() {
        if (getIntent().hasExtra("BIKE")) {
            new BikeController(BikeQuoteActivity.this).getBikePremium(this);
        } else if (getIntent().hasExtra("CAR")) {
            new CarController(BikeQuoteActivity.this).getCarPremium(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BIKE_RESULT) {
            new BikeController(BikeQuoteActivity.this).getBikePremium(this);
        } else if (requestCode == CAR_RESULT) {
            new CarController(BikeQuoteActivity.this).getCarPremium(this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initialize() {
        //mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        bikeQuoteRecycler = (RecyclerView) findViewById(R.id.bikeQuoteRecycler);
        bikeQuoteRecycler.setHasFixedSize(true);
        webViewLoader = (ImageView) findViewById(R.id.webViewLoader);
        Glide.with(this).load(R.drawable.preloader).into(webViewLoader);
        //webViewLoader.loadUrl("file:///android_asset/loader.html");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        bikeQuoteRecycler.setLayoutManager(mLayoutManager);


    }

    //region Add-on
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_on_menu, menu);
        menuAddon = menu;
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.add_on:
                openPopUp();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    //endregion

    private void openPopUp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Add-on :");

        RecyclerView rvAddOne;
        Button btnOk, btnCancel;

        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.layout_addon_popup, null);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        // set the custom dialog components - text, image and button
        btnOk = (Button) dialogView.findViewById(R.id.btnOk);
        btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        rvAddOne = (RecyclerView) dialogView.findViewById(R.id.rvAddOne);
        rvAddOne.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BikeQuoteActivity.this);
        rvAddOne.setLayoutManager(layoutManager);

        final AddonPopUpAdapter popUpAdapter = new AddonPopUpAdapter(BikeQuoteActivity.this, listMobileAddOn);
        rvAddOne.setAdapter(popUpAdapter);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMobileAddOn = popUpAdapter.getUpdateMobileAddonList();
                // applyAddons();
                applyPositiveAddons();
                updateAddonToserver();
                alertDialog.dismiss();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void updateAddonToserver() {
        SaveAddOnRequestEntity entity = new SaveAddOnRequestEntity();
        for (int i = 0; i < listMobileAddOn.size(); i++) {
            MobileAddOn mobileAddOn = listMobileAddOn.get(i);

            if (mobileAddOn.getAddonKey().matches("addon_zero_dep_cover") && mobileAddOn.isSelected) {
                entity.setAddon_zero_dep_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_road_assist_cover") && mobileAddOn.isSelected) {
                entity.setAddon_road_assist_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_ncb_protection_cover") && mobileAddOn.isSelected) {
                entity.setAddon_ncb_protection_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_engine_protector_cover") && mobileAddOn.isSelected) {
                entity.setAddon_engine_protector_cover("yes");
            }

            if (mobileAddOn.getAddonKey().matches("addon_invoice_price_cover") && mobileAddOn.isSelected) {
                entity.setAddon_invoice_price_cover("yes");
            }

            if (mobileAddOn.getAddonKey().matches("addon_key_lock_cover") && mobileAddOn.isSelected) {
                entity.setAddon_key_lock_cover("yes");
            }

            if (mobileAddOn.getAddonKey().matches("addon_consumable_cover") && mobileAddOn.isSelected) {
                entity.setAddon_consumable_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_daily_allowance_cover") && mobileAddOn.isSelected) {
                entity.setAddon_daily_allowance_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_windshield_cover") && mobileAddOn.isSelected) {
                entity.setAddon_windshield_cover("yes");
            }

            if (mobileAddOn.getAddonKey().matches("addon_passenger_assistance_cover") && mobileAddOn.isSelected) {
                entity.setAddon_passenger_assistance_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_tyre_coverage_cover") && mobileAddOn.isSelected) {
                entity.setAddon_tyre_coverage_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_personal_belonging_loss_cover") && mobileAddOn.isSelected) {
                entity.setAddon_personal_belonging_loss_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_inconvenience_allowance_cover") && mobileAddOn.isSelected) {
                entity.setAddon_inconvenience_allowance_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_medical_expense_cover") && mobileAddOn.isSelected) {
                entity.setAddon_medical_expense_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_hospital_cash_cover") && mobileAddOn.isSelected) {
                entity.setAddon_hospital_cash_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_ambulance_charge_cover") && mobileAddOn.isSelected) {
                entity.setAddon_ambulance_charge_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_rodent_bite_cover") && mobileAddOn.isSelected) {
                entity.setAddon_rodent_bite_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_losstime_protection_cover") && mobileAddOn.isSelected) {
                entity.setAddon_losstime_protection_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_hydrostatic_lock_cover") && mobileAddOn.isSelected) {
                entity.setAddon_hydrostatic_lock_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_guaranteed_auto_protection_cover") && mobileAddOn.isSelected) {
                entity.setAddon_guaranteed_auto_protection_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_final_premium") && mobileAddOn.isSelected) {
                entity.setAddon_final_premium("yes");
            }
            /*if (mobileAddOn.getAddonKey().matches("search_reference_number") && mobileAddOn.isSelected) {
                entity.setSearch_reference_number("yes");
            }*/
        }
        if (getIntent().hasExtra("BIKE")) {
            entity.setSearch_reference_number(Constants.getSharedPreference(this).getString(Constants.BIKEQUOTE_UNIQUEID, ""));
        } else if (getIntent().hasExtra("CAR")) {
            entity.setSearch_reference_number(Constants.getSharedPreference(this).getString(Constants.CARQUOTE_UNIQUEID, ""));
        }

        new BikeController(this).saveAddOn(entity, this);
    }


    private void applyPositiveAddons() {

        for (ResponseEntity entity : bikePremiumResponse.getResponse()) { // itrate for each quote
            double addonValue = 0;
            entity.setAddonApplied(false);
            entity.setListAppliedAddons(null);

            if (entity.getAddon_List() != null) {
                List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium =
                        new ArrayList<AppliedAddonsPremiumBreakup>();// list of applied addon

                //region list of available addons
                for (int i = 0; i < listMobileAddOn.size(); i++) {

                    MobileAddOn mobileAddOn = listMobileAddOn.get(i);
                    // check if addon is selected
                    if (!mobileAddOn.isSelected()) {
                        continue;
                    }

                    //region addon_zero_dep_cover
                    if (mobileAddOn.getAddonKey().matches("addon_zero_dep_cover")) {

                        if (entity.getAddon_List().getAddon_zero_dep_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_zero_dep_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_zero_dep_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_zero_dep_cover();
                        }

                    }
                    //endregion

                    //region addon_road_assist_cover

                    if (mobileAddOn.getAddonKey().matches("addon_road_assist_cover")) {

                        if (entity.getAddon_List().getAddon_road_assist_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_road_assist_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_road_assist_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_road_assist_cover();
                        }

                    }
                    //endregion

                    //region addon_ncb_protection_cover

                    if (mobileAddOn.getAddonKey().matches("addon_ncb_protection_cover")) {

                        if (entity.getAddon_List().getAddon_ncb_protection_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_ncb_protection_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_ncb_protection_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_ncb_protection_cover();
                        }

                    }
                    //endregion

                    //region addon_engine_protector_cover

                    if (mobileAddOn.getAddonKey().matches("addon_engine_protector_cover")) {

                        if (entity.getAddon_List().getAddon_engine_protector_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_engine_protector_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_engine_protector_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_engine_protector_cover();
                        }

                    }
                    //endregion

                    //region addon_invoice_price_cover

                    if (mobileAddOn.getAddonKey().matches("addon_invoice_price_cover")) {

                        if (entity.getAddon_List().getAddon_invoice_price_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_invoice_price_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_invoice_price_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_invoice_price_cover();
                        }

                    }
                    //endregion

                    //region addon_key_lock_cover

                    if (mobileAddOn.getAddonKey().matches("addon_key_lock_cover")) {

                        if (entity.getAddon_List().getAddon_key_lock_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_key_lock_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_key_lock_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_key_lock_cover();
                        }

                    }
                    //endregion

                    //region addon_consumable_cover

                    if (mobileAddOn.getAddonKey().matches("addon_consumable_cover")) {

                        if (entity.getAddon_List().getAddon_consumable_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_consumable_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_consumable_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_consumable_cover();
                        }

                    }
                    //endregion

                    //region addon_daily_allowance_cover

                    if (mobileAddOn.getAddonKey().matches("addon_daily_allowance_cover")) {

                        if (entity.getAddon_List().getAddon_daily_allowance_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_daily_allowance_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_daily_allowance_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_daily_allowance_cover();
                        }

                    }
                    //endregion

                    //region addon_windshield_cover

                    if (mobileAddOn.getAddonKey().matches("addon_windshield_cover")) {

                        if (entity.getAddon_List().getAddon_windshield_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_windshield_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_windshield_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_windshield_cover();
                        }

                    }
                    //endregion

                    //region addon_passenger_assistance_cover

                    if (mobileAddOn.getAddonKey().matches("addon_passenger_assistance_cover")) {

                        if (entity.getAddon_List().getAddon_passenger_assistance_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_passenger_assistance_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_passenger_assistance_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_passenger_assistance_cover();
                        }

                    }
                    //endregion

                    //region addon_tyre_coverage_cover

                    if (mobileAddOn.getAddonKey().matches("addon_tyre_coverage_cover")) {

                        if (entity.getAddon_List().getAddon_tyre_coverage_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_tyre_coverage_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_tyre_coverage_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_tyre_coverage_cover();
                        }

                    }
                    //endregion

                    //region addon_personal_belonging_loss_cover

                    if (mobileAddOn.getAddonKey().matches("addon_personal_belonging_loss_cover")) {

                        if (entity.getAddon_List().getAddon_personal_belonging_loss_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_personal_belonging_loss_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_personal_belonging_loss_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_personal_belonging_loss_cover();
                        }

                    }
                    //endregion

                    //region addon_inconvenience_allowance_cover

                    if (mobileAddOn.getAddonKey().matches("addon_inconvenience_allowance_cover")) {

                        if (entity.getAddon_List().getAddon_inconvenience_allowance_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_inconvenience_allowance_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_inconvenience_allowance_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_inconvenience_allowance_cover();
                        }

                    }
                    //endregion

                    //region addon_medical_expense_cover

                    if (mobileAddOn.getAddonKey().matches("addon_medical_expense_cover")) {

                        if (entity.getAddon_List().getAddon_medical_expense_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_medical_expense_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_medical_expense_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_medical_expense_cover();
                        }

                    }
                    //endregion

                    //region addon_hospital_cash_cover

                    if (mobileAddOn.getAddonKey().matches("addon_hospital_cash_cover")) {

                        if (entity.getAddon_List().getAddon_hospital_cash_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_hospital_cash_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_hospital_cash_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_hospital_cash_cover();
                        }

                    }
                    //endregion

                    //region addon_ambulance_charge_cover

                    if (mobileAddOn.getAddonKey().matches("addon_ambulance_charge_cover")) {

                        if (entity.getAddon_List().getAddon_ambulance_charge_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_ambulance_charge_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_ambulance_charge_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_ambulance_charge_cover();
                        }

                    }
                    //endregion

                    //region addon_rodent_bite_cover

                    if (mobileAddOn.getAddonKey().matches("addon_rodent_bite_cover")) {

                        if (entity.getAddon_List().getAddon_rodent_bite_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_rodent_bite_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_rodent_bite_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_rodent_bite_cover();
                        }

                    }
                    //endregion

                    //region addon_losstime_protection_cover

                    if (mobileAddOn.getAddonKey().matches("addon_losstime_protection_cover")) {

                        if (entity.getAddon_List().getAddon_losstime_protection_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_losstime_protection_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_losstime_protection_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_losstime_protection_cover();
                        }

                    }
                    //endregion

                    //region addon_hydrostatic_lock_cover

                    if (mobileAddOn.getAddonKey().matches("addon_hydrostatic_lock_cover")) {

                        if (entity.getAddon_List().getAddon_hydrostatic_lock_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_hydrostatic_lock_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_hydrostatic_lock_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_hydrostatic_lock_cover();
                        }

                    }
                    //endregion

                    //region addon_guaranteed_auto_protection_cover

                    if (mobileAddOn.getAddonKey().matches("addon_guaranteed_auto_protection_cover")) {

                        if (entity.getAddon_List().getAddon_guaranteed_auto_protection_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_guaranteed_auto_protection_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_guaranteed_auto_protection_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_guaranteed_auto_protection_cover();
                        }

                    }
                    //endregion

                    //region addon_final_premium

                    if (mobileAddOn.getAddonKey().matches("addon_final_premium")) {

                        if (entity.getAddon_List().getAddon_final_premium() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_final_premium"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_final_premium());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_final_premium();
                        }

                    }
                    //endregion

                }
                //endregion

                //region update response entity
                double finalPremWithoutGST = addonValue + Double.parseDouble(entity.getPremium_Breakup().getNet_premium());
                double finalPremWithGST = finalPremWithoutGST + (finalPremWithoutGST * Constants.GST);
                entity.setFinal_premium_with_addon("" + finalPremWithGST);
                entity.setListAppliedAddons(listAppliedAddonPremium);
                //endregion
            }
        }

        rebindAdapter(bikePremiumResponse);
    }


    //region apply addon

    private void applyAddons() {

        Double GST_APPLY = .18;
        Double GST_REMOVE = 1.18;
        List<ResponseEntity> listAppliedAddons = new ArrayList<>();
        boolean isAddonApplied = false;
        for (int i = 0; i < listMobileAddOn.size(); i++) {

            MobileAddOn mobileAddOn = listMobileAddOn.get(i);


            if (mobileAddOn.getAddonKey().matches("addon_zero_dep_cover")) {
                for (ResponseEntity entity :
                        bikePremiumResponse.getResponse()) {

                    if (entity.getAddon_List() != null) {

                        List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                        if (entity.getAddon_List().getAddon_zero_dep_cover() != 0) {

                            isAddonApplied = true;
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                            if (mobileAddOn.isSelected) {

                                appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_zero_dep_cover"));
                                appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_zero_dep_cover());
                                listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                        + entity.getAddon_List().getAddon_zero_dep_cover());
                                double ST = getAddonPrice(addonNetPremium);

                                double finalPremium = addonNetPremium + ST;


                                entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                entity.setListAppliedAddons(listAppliedAddonPremium);
                                isAddonApplied = true;
                                listAppliedAddons.add(entity);

                            } else {

                                double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                        - entity.getAddon_List().getAddon_zero_dep_cover();

                                double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                if (entity.getListAppliedAddons() != null) {
                                    for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                        AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                        if (a.getAddonName().equals(databaseController.getAddonName("addon_zero_dep_cover"))) {
                                            entity.getListAppliedAddons().remove(j);
                                            listAppliedAddons.add(entity);
                                        }
                                    }
                                }

                                entity.setListAppliedAddons(listAppliedAddonPremium);
                            }
                        }

                    }
                }
            }

            if (mobileAddOn.getAddonKey().matches("addon_road_assist_cover")) {
                for (ResponseEntity entity :
                        bikePremiumResponse.getResponse()) {
//                    if (entity.getAddon_List() != null) {
//                        if (entity.getAddon_List().getAddon_road_assist_cover() != 0) {
//
//
//
//
//                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();
//
//                            if (mobileAddOn.isSelected()) {
//                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
//                                appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_road_assist_cover"));
//                                appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_road_assist_cover());
//                                listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
//
//                                double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
//                                        + entity.getAddon_List().getAddon_road_assist_cover());
//                                double ST = getAddonPrice(addonNetPremium);
//
//                                double finalPremium = addonNetPremium + ST;
//
//                                entity.setListAppliedAddons(listAppliedAddonPremium);
//
//                                entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
//
//                                isAddonApplied = true;
//
//                                listAppliedAddons.add(entity);
//                            } else {
//                                double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
//                                        - entity.getAddon_List().getAddon_road_assist_cover();
//
//                                double finalPremium = (netPremium * GST_APPLY) + netPremium;
//                                entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
//
//                                if (entity.getListAppliedAddons() != null) {
//                                    for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
//                                        AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
//                                        if (a.getAddonName().equals(databaseController.getAddonName("addon_road_assist_cover"))) {
//                                            entity.getListAppliedAddons().remove(j);
//                                            listAppliedAddons.add(entity);
//                                        }
//                                    }
//                                }
//                                entity.setListAppliedAddons(listAppliedAddonPremium);
//                            }
//
//                        }
//                    }

                    if (entity.getAddon_List() != null) {

                        List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                        if (entity.getAddon_List().getAddon_road_assist_cover() != 0) {

                            isAddonApplied = true;
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                            if (mobileAddOn.isSelected) {

                                appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_road_assist_cover"));
                                appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_road_assist_cover());
                                listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                        + entity.getAddon_List().getAddon_road_assist_cover());
                                double ST = getAddonPrice(addonNetPremium);

                                double finalPremium = addonNetPremium + ST;


                                entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                entity.setListAppliedAddons(listAppliedAddonPremium);
                                isAddonApplied = true;
                                listAppliedAddons.add(entity);

                            } else {

                                double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                        - entity.getAddon_List().getAddon_road_assist_cover();

                                double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                if (entity.getListAppliedAddons() != null) {
                                    for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                        AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                        if (a.getAddonName().equals(databaseController.getAddonName("addon_road_assist_cover"))) {
                                            entity.getListAppliedAddons().remove(j);
                                            listAppliedAddons.add(entity);
                                        }
                                    }
                                }

                                entity.setListAppliedAddons(listAppliedAddonPremium);
                            }
                        }

                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_ncb_protection_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {

                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_ncb_protection_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_ncb_protection_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_ncb_protection_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_ncb_protection_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_ncb_protection_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_ncb_protection_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }

                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_engine_protector_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {
                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_engine_protector_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_engine_protector_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_engine_protector_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_engine_protector_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_engine_protector_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_engine_protector_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_invoice_price_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_invoice_price_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_invoice_price_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_invoice_price_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_invoice_price_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_invoice_price_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_invoice_price_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_key_lock_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_key_lock_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_key_lock_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_key_lock_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_key_lock_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_key_lock_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_key_lock_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_consumable_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_consumable_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_consumable_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_consumable_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_consumable_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_consumable_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_consumable_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_daily_allowance_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {
                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_daily_allowance_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_daily_allowance_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_daily_allowance_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_daily_allowance_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_daily_allowance_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_daily_allowance_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_windshield_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_windshield_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_windshield_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_windshield_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_windshield_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_windshield_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_windshield_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_passenger_assistance_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_passenger_assistance_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_passenger_assistance_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_passenger_assistance_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_passenger_assistance_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_passenger_assistance_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_passenger_assistance_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_tyre_coverage_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_tyre_coverage_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_tyre_coverage_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_tyre_coverage_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_tyre_coverage_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_tyre_coverage_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_tyre_coverage_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_personal_belonging_loss_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_personal_belonging_loss_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_personal_belonging_loss_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_personal_belonging_loss_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_personal_belonging_loss_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_personal_belonging_loss_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_personal_belonging_loss_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_inconvenience_allowance_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_inconvenience_allowance_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_inconvenience_allowance_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_inconvenience_allowance_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_inconvenience_allowance_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_inconvenience_allowance_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_inconvenience_allowance_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_medical_expense_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_medical_expense_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_medical_expense_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_medical_expense_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_medical_expense_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_medical_expense_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_medical_expense_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_hospital_cash_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_medical_expense_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_hospital_cash_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_hospital_cash_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_hospital_cash_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_hospital_cash_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_hospital_cash_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_ambulance_charge_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_ambulance_charge_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_ambulance_charge_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_ambulance_charge_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_ambulance_charge_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_ambulance_charge_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_ambulance_charge_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_rodent_bite_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_rodent_bite_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_rodent_bite_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_rodent_bite_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_rodent_bite_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_rodent_bite_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_rodent_bite_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_losstime_protection_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_losstime_protection_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_losstime_protection_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_losstime_protection_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_losstime_protection_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_losstime_protection_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_losstime_protection_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_hydrostatic_lock_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_hydrostatic_lock_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_hydrostatic_lock_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_hydrostatic_lock_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_hydrostatic_lock_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_hydrostatic_lock_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_hydrostatic_lock_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_guaranteed_auto_protection_cover")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_guaranteed_auto_protection_cover() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_guaranteed_auto_protection_cover"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_guaranteed_auto_protection_cover());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_guaranteed_auto_protection_cover());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_guaranteed_auto_protection_cover();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_guaranteed_auto_protection_cover"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }

                if (mobileAddOn.getAddonKey().matches("addon_final_premium")) {
                    for (ResponseEntity entity :
                            bikePremiumResponse.getResponse()) {

                        if (entity.getAddon_List() != null) {
                            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium = new ArrayList<AppliedAddonsPremiumBreakup>();

                            if (entity.getAddon_List().getAddon_final_premium() != 0) {

                                isAddonApplied = true;
                                AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();

                                if (mobileAddOn.isSelected) {

                                    appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_final_premium"));
                                    appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_final_premium());
                                    listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);

                                    double addonNetPremium = (Double.parseDouble(entity.getPremium_Breakup().getNet_premium())
                                            + entity.getAddon_List().getAddon_final_premium());
                                    double ST = getAddonPrice(addonNetPremium);

                                    double finalPremium = addonNetPremium + ST;


                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));
                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                    isAddonApplied = true;
                                    listAppliedAddons.add(entity);

                                } else {

                                    double netPremium = (Double.parseDouble(entity.getPremium_Breakup().getFinal_premium()) / GST_REMOVE)
                                            - entity.getAddon_List().getAddon_final_premium();

                                    double finalPremium = (netPremium * GST_APPLY) + netPremium;
                                    entity.getPremium_Breakup().setFinal_premium(String.valueOf(finalPremium));

                                    if (entity.getListAppliedAddons() != null) {
                                        for (int j = entity.getListAppliedAddons().size() - 1; j >= 0; j--) {
                                            AppliedAddonsPremiumBreakup a = entity.getListAppliedAddons().get(j);
                                            if (a.getAddonName().equals(databaseController.getAddonName("addon_final_premium"))) {
                                                entity.getListAppliedAddons().remove(j);
                                                listAppliedAddons.add(entity);
                                            }
                                        }
                                    }

                                    entity.setListAppliedAddons(listAppliedAddonPremium);
                                }
                            }
                        }
                    }
                }
            }

            if (isAddonApplied) {
                for (int j = 0; j < listAppliedAddons.size(); j++) {
                    ResponseEntity entity = listAppliedAddons.get(j);
                    for (int k = 0; k < bikePremiumResponse.getResponse().size(); k++) {
                        ResponseEntity existingResponse = bikePremiumResponse.getResponse().get(k);
                        if (existingResponse.getService_Log_Id().equals(entity.getService_Log_Id())) {
                            bikePremiumResponse.getResponse().set(k, entity);
                            break;
                        }
                    }
                }

                rebindAdapter(bikePremiumResponse);
                isAddonApplied = false;
            }
        }
    }

    private double getAddonPrice(double addonPrice) {
        return addonPrice * 0.18;
    }

    //endregion

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof BikePremiumResponse) {
            bikePremiumResponse = (BikePremiumResponse) response;
            rebindAdapter(bikePremiumResponse);
            Log.d("trackIssue", "Summary  = " + bikePremiumResponse.getSummary().getStatusX() +
                    " ,counter = " + Constants.getSharedPreference(this).getInt(Constants.QUOTE_COUNTER, 0));
            if (bikePremiumResponse.getSummary().getStatusX().equals("complete")
                    || Constants.getSharedPreference(this).getInt(Constants.QUOTE_COUNTER, 0) >= CarController.NO_OF_SERVER_HITS) {

                webViewLoader.setVisibility(View.GONE);

                new AsyncAddon().execute();

                if (((BikePremiumResponse) response).getResponse().size() != 0)
                    menuAddon.findItem(R.id.add_on).setVisible(true);
                else {
                    menuAddon.findItem(R.id.add_on).setVisible(false);
                    Toast.makeText(this, "No quotes found.., try later", Toast.LENGTH_SHORT).show();
                }

            } else {
                webViewLoader.setVisibility(View.VISIBLE);

            }
        } else if (response instanceof SaveAddOnResponse) {

        }

    }

    public void rebindAdapter(BikePremiumResponse bikePremiumResponse) {
        mAdapter.setQuoteResponse(bikePremiumResponse);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(BikeQuoteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

    }

    public void redirectToBuy(String Service_Log_Unique_Id) {
        // String URL = "http://qa.policyboss.com/buynowprivatecar/2/arn-5vsdcdks-ifxf-lbo7-imvr-ycc3axgrfrwe/nonposp/0";
        String url = "http://qa.policyboss.com/";
        //String url = "http://policyboss.com/";
        String title = "";
        String name = "";

        if (getIntent().hasExtra("BIKE")) {
            url = url + "buynowTwoWheeler/4/" + Service_Log_Unique_Id + "/nonposp/0";
            title = "Bike Insurance";
        } else if (getIntent().hasExtra("CAR")) {
            url = url + "buynowprivatecar/4/" + Service_Log_Unique_Id + "/nonposp/0";
            title = "Car Insurance";
        }


        startActivity(new Intent(this, CommonWebViewActivity.class)
                .putExtra("URL", url)
                .putExtra("NAME", name)
                .putExtra("TITLE", title));
    }

    public void redirectToPopUpPremium(ResponseEntity entity, SummaryEntity summaryEntity, String IDV) {
        startActivity(new Intent(this, PremiumBikePopUpActivity.class)
                .putExtra(Constants.Bike_QUOTE_PRIMIUM, entity.getPremium_Breakup())
                .putExtra(Constants.Bike_QUOTE_INSURER, entity.getInsurer())
                .putExtra(Constants.IDV_DATA, IDV));


    }

    class AsyncAddon extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            listMobileAddOn.clear();
            CommonAddonEntity entity = bikePremiumResponse.getSummary().getCommon_Addon();
            if (entity.getAddon_ambulance_charge_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_ambulance_charge_cover"));
                mobileAddOn.setMin(entity.getAddon_ambulance_charge_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_ambulance_charge_cover().getMax());
                mobileAddOn.setAddonKey("addon_ambulance_charge_cover");
                // item.add(databaseController.getAddonName("addon_ambulance_charge_cover"));
                listMobileAddOn.add(mobileAddOn);
            }
            if (entity.getAddon_consumable_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_consumable_cover"));
                mobileAddOn.setMin(entity.getAddon_consumable_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_consumable_cover().getMax());
                mobileAddOn.setAddonKey("addon_consumable_cover");
                listMobileAddOn.add(mobileAddOn);
            }
            if (entity.getAddon_daily_allowance_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_daily_allowance_cover"));
                mobileAddOn.setMin(entity.getAddon_daily_allowance_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_daily_allowance_cover().getMax());
                mobileAddOn.setAddonKey("addon_daily_allowance_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_engine_protector_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_engine_protector_cover"));
                mobileAddOn.setMin(entity.getAddon_engine_protector_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_engine_protector_cover().getMax());
                mobileAddOn.setAddonKey("addon_engine_protector_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_hospital_cash_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_hospital_cash_cover"));
                mobileAddOn.setMin(entity.getAddon_hospital_cash_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_hospital_cash_cover().getMax());
                mobileAddOn.setAddonKey("addon_hospital_cash_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_hydrostatic_lock_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_hydrostatic_lock_cover"));
                mobileAddOn.setMin(entity.getAddon_hydrostatic_lock_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_hydrostatic_lock_cover().getMax());
                mobileAddOn.setAddonKey("addon_hydrostatic_lock_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_inconvenience_allowance_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_inconvenience_allowance_cover"));
                mobileAddOn.setMin(entity.getAddon_inconvenience_allowance_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_inconvenience_allowance_cover().getMax());
                mobileAddOn.setAddonKey("addon_inconvenience_allowance_cover");
                listMobileAddOn.add(mobileAddOn);

            }


            if (entity.getAddon_invoice_price_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_invoice_price_cover"));
                mobileAddOn.setMin(entity.getAddon_invoice_price_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_invoice_price_cover().getMax());
                mobileAddOn.setAddonKey("addon_invoice_price_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_key_lock_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_key_lock_cover"));
                mobileAddOn.setMin(entity.getAddon_key_lock_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_key_lock_cover().getMax());
                mobileAddOn.setAddonKey("addon_key_lock_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_losstime_protection_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_losstime_protection_cover"));
                mobileAddOn.setMin(entity.getAddon_losstime_protection_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_losstime_protection_cover().getMax());
                mobileAddOn.setAddonKey("addon_losstime_protection_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_medical_expense_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_medical_expense_cover"));
                mobileAddOn.setMin(entity.getAddon_medical_expense_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_medical_expense_cover().getMax());
                mobileAddOn.setAddonKey("addon_medical_expense_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_ncb_protection_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_ncb_protection_cover"));
                mobileAddOn.setMin(entity.getAddon_ncb_protection_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_ncb_protection_cover().getMax());
                mobileAddOn.setAddonKey("addon_ncb_protection_cover");
                listMobileAddOn.add(mobileAddOn);


            }

            if (entity.getAddon_passenger_assistance_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_passenger_assistance_cover"));
                mobileAddOn.setMin(entity.getAddon_passenger_assistance_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_passenger_assistance_cover().getMax());
                mobileAddOn.setAddonKey("addon_passenger_assistance_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_personal_belonging_loss_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_personal_belonging_loss_cover"));
                mobileAddOn.setMin(entity.getAddon_personal_belonging_loss_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_personal_belonging_loss_cover().getMax());
                mobileAddOn.setAddonKey("addon_personal_belonging_loss_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_road_assist_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_road_assist_cover"));
                mobileAddOn.setMin(entity.getAddon_road_assist_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_road_assist_cover().getMax());
                mobileAddOn.setAddonKey("addon_road_assist_cover");
                listMobileAddOn.add(mobileAddOn);
            }
            if (entity.getAddon_rodent_bite_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_rodent_bite_cover"));
                mobileAddOn.setMin(entity.getAddon_rodent_bite_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_rodent_bite_cover().getMax());
                mobileAddOn.setAddonKey("addon_rodent_bite_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_tyre_coverage_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_tyre_coverage_cover"));
                mobileAddOn.setMin(entity.getAddon_tyre_coverage_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_tyre_coverage_cover().getMax());
                mobileAddOn.setAddonKey("addon_tyre_coverage_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_windshield_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_windshield_cover"));
                mobileAddOn.setMin(entity.getAddon_windshield_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_windshield_cover().getMax());
                mobileAddOn.setAddonKey("addon_windshield_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_zero_dep_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_zero_dep_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_zero_dep_cover");
                listMobileAddOn.add(mobileAddOn);

                //item.add(databaseController.getAddonName("addon_zero_dep_cover"));
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
