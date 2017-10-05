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
      final  ArrayList<QuoteSelected> itemsList = getAddons(bikePremiumResponse.getSummary().getCommon_Addon());
       // final String[] items = itemsArray.toArray(new String[itemsArray.size()]);
        final String[] items = new String[itemsList.size()];
       // final String[] items   = {" Easy "," Medium "," Hard "," Very Hard "};
        boolean[] checkedValues = new boolean[itemsList.size()];

        for(int i = 0; i < itemsList.size(); i++) {

            items[i] = itemsList.get(i).getAddOns().toString();
            checkedValues[i] = itemsList.get(i).isSelected;
        }
        final ArrayList itemsSelected = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //checkedValues[0] = true;
        builder.setTitle("Select Add-on :");
        builder.setMultiChoiceItems(items, checkedValues,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedItemId,
                                        boolean isSelected) {
//                        if (isSelected) {
//                          //  itemsSelected.add(selectedItemId);
//                           // itemsList.get(selectedItemId).setSelected(isSelected);
//
//                            //  commonAddonEntity.getAddon_zero_dep_cover().setSelected();
//
//                        } else if (itemsSelected.contains(selectedItemId)) {
//                          //  itemsSelected.remove(Integer.valueOf(selectedItemId));
//                            itemsList.get(selectedItemId).setSelected(isSelected);
//                        }

//                        CommonAddonEntity commonAddonEntity = bikePremiumResponse.getSummary().getCommon_Addon();
//                        commonAddonEntity.getAddon_zero_dep_cover().setSelected(isSelected);


                        setAddonsCheckLst(bikePremiumResponse.getSummary().getCommon_Addon(),isSelected);
                    //   bikePremiumResponse.getSummary().getCommon_Addon().getAddon_zero_dep_cover().setSelected(isSelected);


                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            ArrayList<String> selectedItems = new ArrayList<String>();

                            for (int i = 0; i < itemsList.size(); i++) {
                                if (itemsList.get(i).isSelected) {
                                    selectedItems.add(databaseController.getAddonKey(items[i]));
                                }
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
      //  ((AlertDialog) dialog).getListView().setItemChecked(0, true);
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
                        Log.d("Zero dep", "" + entity.getAddon_List().getAddon_zero_dep_cover());
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

    private ArrayList<QuoteSelected> getAddons(CommonAddonEntity entity) {
        ArrayList<QuoteSelected> item = new ArrayList<>();

         QuoteSelected quoteSelected= new QuoteSelected();
        if (entity.getAddon_ambulance_charge_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_ambulance_charge_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            // item.add(databaseController.getAddonName("addon_ambulance_charge_cover"));
            item.add(quoteSelected);
        }
        if (entity.getAddon_consumable_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_consumable_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());

            //item.add(databaseController.getAddonName("addon_consumable_cover"));
            item.add(quoteSelected);
        }
        if (entity.getAddon_daily_allowance_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_daily_allowance_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());

            // item.add(databaseController.getAddonName("addon_daily_allowance_cover"));
            item.add(quoteSelected);
        }

        if (entity.getAddon_engine_protector_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_engine_protector_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());

            // item.add(databaseController.getAddonName("addon_engine_protector_cover"));
            item.add(quoteSelected);
        }

        if (entity.getAddon_hospital_cash_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_engine_protector_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());

            // item.add(databaseController.getAddonName("addon_hospital_cash_cover"));
            item.add(quoteSelected);
        }

        if (entity.getAddon_hydrostatic_lock_cover() != null) {

            quoteSelected.setAddOns(databaseController.getAddonName("addon_hydrostatic_lock_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

            //item.add(databaseController.getAddonName("addon_hydrostatic_lock_cover"));
        }

        if (entity.getAddon_inconvenience_allowance_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_inconvenience_allowance_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

          //  item.add(databaseController.getAddonName("addon_inconvenience_allowance_cover"));
        }


        if (entity.getAddon_invoice_price_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_invoice_price_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

           // item.add(databaseController.getAddonName("addon_invoice_price_cover"));
        }

        if (entity.getAddon_key_lock_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_key_lock_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

            //item.add(databaseController.getAddonName("addon_key_lock_cover"));
        }

        if (entity.getAddon_losstime_protection_cover() != null) {

            quoteSelected.setAddOns(databaseController.getAddonName("addon_losstime_protection_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

           // item.add(databaseController.getAddonName("addon_losstime_protection_cover"));
        }


        if (entity.getAddon_medical_expense_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_medical_expense_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

           // item.add(databaseController.getAddonName("addon_medical_expense_cover"));
        }

        if (entity.getAddon_ncb_protection_cover() != null) {

            quoteSelected.setAddOns(databaseController.getAddonName("addon_ncb_protection_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

          //  item.add(databaseController.getAddonName("addon_ncb_protection_cover"));
        }

        if (entity.getAddon_passenger_assistance_cover() != null) {

            quoteSelected.setAddOns(databaseController.getAddonName("addon_passenger_assistance_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

           // item.add(databaseController.getAddonName("addon_passenger_assistance_cover"));
        }

        if (entity.getAddon_personal_belonging_loss_cover() != null) {

            quoteSelected.setAddOns(databaseController.getAddonName("addon_personal_belonging_loss_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

           // item.add(databaseController.getAddonName("addon_personal_belonging_loss_cover"));
        }

        if (entity.getAddon_road_assist_cover() != null) {

            quoteSelected.setAddOns(databaseController.getAddonName("addon_road_assist_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

            //item.add(databaseController.getAddonName("addon_road_assist_cover"));
        }
        if (entity.getAddon_rodent_bite_cover() != null) {

            quoteSelected.setAddOns(databaseController.getAddonName("addon_rodent_bite_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

           // item.add(databaseController.getAddonName("addon_rodent_bite_cover"));
        }

        if (entity.getAddon_tyre_coverage_cover() != null) {

            quoteSelected.setAddOns(databaseController.getAddonName("addon_tyre_coverage_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

            //item.add(databaseController.getAddonName("addon_tyre_coverage_cover"));
        }

        if (entity.getAddon_windshield_cover() != null) {

            quoteSelected.setAddOns(databaseController.getAddonName("addon_windshield_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

           // item.add(databaseController.getAddonName("addon_windshield_cover"));
        }

        if (entity.getAddon_zero_dep_cover() != null) {
            quoteSelected.setAddOns(databaseController.getAddonName("addon_zero_dep_cover"));
            quoteSelected.setSelected(entity.getAddon_zero_dep_cover().isSelected());
            item.add(quoteSelected);

            //item.add(databaseController.getAddonName("addon_zero_dep_cover"));
        }
        return item;
    }

    private void setAddonsCheckLst(CommonAddonEntity entity ,boolean bln) {
        ArrayList<QuoteSelected> item = new ArrayList<>();

        QuoteSelected quoteSelected= new QuoteSelected();
        if (entity.getAddon_ambulance_charge_cover() != null) {
            entity.getAddon_ambulance_charge_cover().setSelected(bln);
        }
        if (entity.getAddon_consumable_cover() != null) {
            entity.getAddon_consumable_cover().setSelected(bln);
        }
        if (entity.getAddon_daily_allowance_cover() != null) {
            entity.getAddon_daily_allowance_cover().setSelected(bln);
        }

        if (entity.getAddon_engine_protector_cover() != null) {
            entity.getAddon_engine_protector_cover().setSelected(bln);
        }

        if (entity.getAddon_hospital_cash_cover() != null) {

            entity.getAddon_hospital_cash_cover().setSelected(bln);
        }

        if (entity.getAddon_hydrostatic_lock_cover() != null) {

            entity.getAddon_hydrostatic_lock_cover().setSelected(bln);
        }

        if (entity.getAddon_inconvenience_allowance_cover() != null) {
            entity.getAddon_inconvenience_allowance_cover().setSelected(bln);
        }


        if (entity.getAddon_invoice_price_cover() != null) {
            entity.getAddon_invoice_price_cover().setSelected(bln);
        }

        if (entity.getAddon_key_lock_cover() != null) {

            entity.getAddon_key_lock_cover().setSelected(bln);
        }

        if (entity.getAddon_losstime_protection_cover() != null) {

            entity.getAddon_losstime_protection_cover().setSelected(bln);
        }


        if (entity.getAddon_medical_expense_cover() != null) {
            entity.getAddon_medical_expense_cover().setSelected(bln);
        }

        if (entity.getAddon_ncb_protection_cover() != null) {

            entity.getAddon_ncb_protection_cover().setSelected(bln);
        }

        if (entity.getAddon_passenger_assistance_cover() != null) {

            entity.getAddon_passenger_assistance_cover().setSelected(bln);
        }

        if (entity.getAddon_personal_belonging_loss_cover() != null) {

            entity.getAddon_personal_belonging_loss_cover().setSelected(bln);
        }

        if (entity.getAddon_road_assist_cover() != null) {

            entity.getAddon_road_assist_cover().setSelected(bln);
        }
        if (entity.getAddon_rodent_bite_cover() != null) {

            entity.getAddon_rodent_bite_cover().setSelected(bln);
        }

        if (entity.getAddon_tyre_coverage_cover() != null) {

            entity.getAddon_tyre_coverage_cover().setSelected(bln);
        }

        if (entity.getAddon_windshield_cover() != null) {

            entity.getAddon_windshield_cover().setSelected(bln);
        }

        if (entity.getAddon_zero_dep_cover() != null) {
            entity.getAddon_zero_dep_cover().setSelected(bln);
        }

    }

    public class QuoteSelected
    {
        private String addOns;
        private boolean isSelected;

        public String getAddOns() {
            return addOns;
        }

        public void setAddOns(String addOns) {
            this.addOns = addOns;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }





    }
}
