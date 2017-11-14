package com.android.policyboss.bikeinsurance;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.models.CommonAddonEntity;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.response.BikePremiumResponse;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class BikeQuoteActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    BikePremiumResponse bikePremiumResponse;
    RecyclerView bikeQuoteRecycler;
    BikeQuoteAdapter mAdapter;
    BikeRequestEntity bikeRequestEntity;
    Menu menuAddon;
    String[] addOns;
    DatabaseController databaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_quote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        bikeRequestEntity = getIntent().getParcelableExtra("BIKE_REQUEST");
        initialize();
        realm = Realm.getDefaultInstance();
        databaseController = new DatabaseController(this, realm);

    }

    private void initialize() {
        bikeQuoteRecycler = (RecyclerView) findViewById(R.id.bikeQuoteRecycler);
        bikeQuoteRecycler.setHasFixedSize(true);

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
                openPopUp();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void openPopUp() {

        Dialog dialog;
        ArrayList<String> itemsArray = getAddons(bikePremiumResponse.getSummary().getCommon_Addon());
        final String[] items = itemsArray.toArray(new String[itemsArray.size()]);

        final ArrayList itemsSelected = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Add-on :");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedItemId,
                                        boolean isSelected) {
                        if (isSelected) {
                            itemsSelected.add(selectedItemId);
                        } else if (itemsSelected.contains(selectedItemId)) {
                            itemsSelected.remove(Integer.valueOf(selectedItemId));
                        }
                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            ArrayList<String> selectedItems = new ArrayList<String>();

                            for (int i = 0; i < itemsSelected.size(); i++) {
                                selectedItems.add(databaseController.getAddonKey(items[i]));
                            }
                            applyAddon(selectedItems, bikePremiumResponse);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

    }

    //endregion

    //region apply addon


    private void applyAddon(ArrayList<String> addonList, BikePremiumResponse bikeResponse) {
        List<ResponseEntity> list = new ArrayList<>();

        for (int i = 0; i < addonList.size(); i++) {
            if (addonList.get(i).matches("addon_zero_dep_cover")) {
                for (ResponseEntity entity :
                        bikeResponse.getResponse()) {

                    if (entity.getAddon_List().getAddon_zero_dep_cover() != 0) {
                        Log.d("Zero", "" + entity.getAddon_List().getAddon_zero_dep_cover());
                        Log.d("Zero final", "" + entity.getPremium_Breakup().getFinal_premium());
                        int zero = entity.getAddon_List().getAddon_zero_dep_cover();
                        double finalPre = entity.getPremium_Breakup().getFinal_premium();

                        int ss = (int) (zero + finalPre);


                        double prevPremium = (entity.getPremium_Breakup().getFinal_premium() + entity.getAddon_List().getAddon_zero_dep_cover());
                        entity.getPremium_Breakup().setFinal_premium(getAddonPrice(prevPremium));
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
        new BikeController(BikeQuoteActivity.this).getBikePremium(this);
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
            CommonAddonEntity commonAddonEntity = bikePremiumResponse.getSummary().getCommon_Addon();


            if (((BikePremiumResponse) response).getResponse().size() != 0)
                menuAddon.findItem(R.id.add_on).setVisible(true);
            else
                menuAddon.findItem(R.id.add_on).setVisible(false);
        }

    }

    public void rebindAdapter(BikePremiumResponse response) {
        bikeQuoteRecycler.setAdapter(null);
        mAdapter = new BikeQuoteAdapter(BikeQuoteActivity.this, bikePremiumResponse);
        bikeQuoteRecycler.setAdapter(mAdapter);
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(BikeQuoteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

    }

    private ArrayList<String> getAddons(CommonAddonEntity entity) {
        ArrayList<String> item = new ArrayList<>();


        if (entity.getAddon_ambulance_charge_cover() != null) {
            item.add(databaseController.getAddonName("addon_ambulance_charge_cover"));
        }
        if (entity.getAddon_consumable_cover() != null) {
            item.add(databaseController.getAddonName("addon_consumable_cover"));
        }
        if (entity.getAddon_daily_allowance_cover() != null) {
            item.add(databaseController.getAddonName("addon_daily_allowance_cover"));
        }

        if (entity.getAddon_engine_protector_cover() != null) {
            item.add(databaseController.getAddonName("addon_engine_protector_cover"));
        }

        if (entity.getAddon_hospital_cash_cover() != null) {
            item.add(databaseController.getAddonName("addon_hospital_cash_cover"));
        }

        if (entity.getAddon_hydrostatic_lock_cover() != null) {
            item.add(databaseController.getAddonName("addon_hydrostatic_lock_cover"));
        }

        if (entity.getAddon_inconvenience_allowance_cover() != null) {
            item.add(databaseController.getAddonName("addon_inconvenience_allowance_cover"));
        }


        if (entity.getAddon_invoice_price_cover() != null) {
            item.add(databaseController.getAddonName("addon_invoice_price_cover"));
        }

        if (entity.getAddon_key_lock_cover() != null) {
            item.add(databaseController.getAddonName("addon_key_lock_cover"));
        }

        if (entity.getAddon_losstime_protection_cover() != null) {
            item.add(databaseController.getAddonName("addon_losstime_protection_cover"));
        }


        if (entity.getAddon_medical_expense_cover() != null) {
            item.add(databaseController.getAddonName("addon_medical_expense_cover"));
        }

        if (entity.getAddon_ncb_protection_cover() != null) {
            item.add(databaseController.getAddonName("addon_ncb_protection_cover"));
        }

        if (entity.getAddon_passenger_assistance_cover() != null) {
            item.add(databaseController.getAddonName("addon_passenger_assistance_cover"));
        }

        if (entity.getAddon_personal_belonging_loss_cover() != null) {
            item.add(databaseController.getAddonName("addon_personal_belonging_loss_cover"));
        }

        if (entity.getAddon_road_assist_cover() != null) {
            item.add(databaseController.getAddonName("addon_road_assist_cover"));
        }
        if (entity.getAddon_rodent_bite_cover() != null) {
            item.add(databaseController.getAddonName("addon_rodent_bite_cover"));
        }

        if (entity.getAddon_tyre_coverage_cover() != null) {
            item.add(databaseController.getAddonName("addon_tyre_coverage_cover"));
        }

        if (entity.getAddon_windshield_cover() != null) {
            item.add(databaseController.getAddonName("addon_windshield_cover"));
        }

        if (entity.getAddon_zero_dep_cover() != null) {
            item.add(databaseController.getAddonName("addon_zero_dep_cover"));
        }
        return item;
    }
}
