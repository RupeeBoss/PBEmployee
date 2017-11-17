package com.android.policyboss.bikeinsurance;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.core.controller.car.CarController;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.models.CommonAddonEntity;
import com.android.policyboss.core.models.MobileAddOn;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.response.BikePremiumResponse;
import com.android.policyboss.utility.Constants;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class BikeQuoteActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    BikePremiumResponse bikePremiumResponse;
    RecyclerView bikeQuoteRecycler;
    BikeQuoteAdapter mAdapter;
    BikeRequestEntity bikeRequestEntity;
    BikeRequestEntity carRequestEntity;
    Menu menuAddon;
    String[] addOns;
    DatabaseController databaseController;
    WebView webViewLoader;
    List<MobileAddOn> listMobileAddOn;

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
                startActivity(new Intent(BikeQuoteActivity.this, ModifyQuotesActivity.class));
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
    }

    private void initialize() {
        bikeQuoteRecycler = (RecyclerView) findViewById(R.id.bikeQuoteRecycler);
        bikeQuoteRecycler.setHasFixedSize(true);
        webViewLoader = (WebView) findViewById(R.id.webViewLoader);
        webViewLoader.loadUrl("file:///android_asset/loader.html");
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
            case R.id.add_on:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    //endregion

    //region apply addon


    private void applyAddon(ArrayList<String> addonList, BikePremiumResponse bikeResponse) {
        List<ResponseEntity> list = new ArrayList<>();

        for (int i = 0; i < addonList.size(); i++) {
            if (addonList.get(i).matches("addon_zero_dep_cover")) {
                for (ResponseEntity entity :
                        bikeResponse.getResponse()) {

                    if (Integer.parseInt(entity.getPremium_Breakup().getAddon().getAddon_zero_dep_cover()) != 0) {
                        double prevPremium = (Integer.parseInt(entity.getPremium_Breakup().getFinal_premium())
                                + Integer.parseInt(entity.getPremium_Breakup().getAddon().getAddon_zero_dep_cover()));
                        entity.getPremium_Breakup().setFinal_premium("" + getAddonPrice(prevPremium));
                    }
                    list.add(entity);
                }
            }
        }
        bikePremiumResponse.setResponse(list);
        rebindAdapter(bikePremiumResponse);

    }

    private double getAddonPrice(double addonPrice) {
        return addonPrice * 0.18;
    }

    //endregion
    @Override
    protected void onResume() {
        showDialog();
        if (getIntent().hasExtra("BIKE")) {
            new BikeController(BikeQuoteActivity.this).getBikePremium(this);
        } else if (getIntent().hasExtra("CAR")) {
            new CarController(BikeQuoteActivity.this).getCarPremium(this);
        }
        super.onResume();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof BikePremiumResponse) {
            bikePremiumResponse = (BikePremiumResponse) response;
            rebindAdapter(bikePremiumResponse);

            if (((BikePremiumResponse) response).getResponse().size() != 0)
                menuAddon.findItem(R.id.add_on).setVisible(true);
            else
                menuAddon.findItem(R.id.add_on).setVisible(false);

            //TODO : Create Add-on here

            //
            if (bikePremiumResponse.getSummary().getStatusX().equals("complete") || Constants.getSharedPreference(this).getInt(Constants.QUOTE_COUNTER, 0) >= 5) {
                webViewLoader.setVisibility(View.GONE);
                new AsyncAddon().execute();

                if (((BikePremiumResponse) response).getResponse().size() != 0)
                    menuAddon.findItem(R.id.add_on).setVisible(true);
                else
                    menuAddon.findItem(R.id.add_on).setVisible(false);
            } else {
                webViewLoader.setVisibility(View.VISIBLE);

            }


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

    class AsyncAddon extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            CommonAddonEntity entity = bikePremiumResponse.getSummary().getCommon_Addon();
            if (entity.getAddon_ambulance_charge_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_ambulance_charge_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_ambulance_charge_cover");
                // item.add(databaseController.getAddonName("addon_ambulance_charge_cover"));
                listMobileAddOn.add(mobileAddOn);
            }
            if (entity.getAddon_consumable_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_consumable_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_consumable_cover");
                listMobileAddOn.add(mobileAddOn);
            }
            if (entity.getAddon_daily_allowance_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_daily_allowance_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_daily_allowance_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_engine_protector_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_engine_protector_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_engine_protector_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_hospital_cash_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_hospital_cash_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_hospital_cash_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_hydrostatic_lock_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_hydrostatic_lock_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_hydrostatic_lock_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_inconvenience_allowance_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_inconvenience_allowance_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_inconvenience_allowance_cover");
                listMobileAddOn.add(mobileAddOn);

            }


            if (entity.getAddon_invoice_price_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_invoice_price_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_invoice_price_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_key_lock_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_key_lock_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_key_lock_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_losstime_protection_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_losstime_protection_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_losstime_protection_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_medical_expense_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_medical_expense_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_medical_expense_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_ncb_protection_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_ncb_protection_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_ncb_protection_cover");
                listMobileAddOn.add(mobileAddOn);


            }

            if (entity.getAddon_passenger_assistance_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_passenger_assistance_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_passenger_assistance_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_personal_belonging_loss_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_personal_belonging_loss_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_personal_belonging_loss_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_road_assist_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_road_assist_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_road_assist_cover");
                listMobileAddOn.add(mobileAddOn);
            }
            if (entity.getAddon_rodent_bite_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_rodent_bite_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_rodent_bite_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_tyre_coverage_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_tyre_coverage_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_tyre_coverage_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_windshield_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_windshield_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
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

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
