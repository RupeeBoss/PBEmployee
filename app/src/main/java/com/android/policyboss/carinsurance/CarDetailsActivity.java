package com.android.policyboss.carinsurance;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.utility.Constants;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class CarDetailsActivity extends BaseActivity {

    LinearLayout llWhenPolicyExpiring, llVarientDetails, llAdditionalDetails, llAdditionAcc, llNcb;
    QuoteRequestEntity quoteRequestEntity;
    DatabaseController databaseController;

    List<String> cityList;
    List<String> makeList;
    List<String> modelList;
    List<String> variantList;

    ArrayAdapter<String> makeAdapter;
    ArrayAdapter cityAdapter;
    ArrayAdapter modelAdapter;
    ArrayAdapter varientAdapter;

    Spinner spCarMake, spCarFuelType, spCarVarient;
    AutoCompleteTextView autoCarMake;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed())
            realm.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        realm = Realm.getDefaultInstance();
        databaseController = new DatabaseController(this, realm);
        initialise_list();
        fetchMasterFromDatabase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quoteRequestEntity = getIntent().getParcelableExtra(Constants.QUOTE);
        init_widgets();
        setListeners();

        bindingAdapters();


    }

    private void bindingAdapters() {

        makeAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, makeList);
        autoCarMake.setAdapter(makeAdapter);
        autoCarMake.setThreshold(1);

        modelAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, modelList);
        varientAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, variantList);
        cityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showOrHideLayout();
    }

    private void fetchMasterFromDatabase() {
        cityList = databaseController.getCity();
        makeList = databaseController.getMakeList();

    }

    private void initialise_list() {
        cityList = new ArrayList<>();
        makeList = new ArrayList<>();
        modelList = new ArrayList<>();
        variantList = new ArrayList<>();
    }

    private void showOrHideLayout() {
        if (quoteRequestEntity.isNew()) {
            llVarientDetails.setVisibility(View.VISIBLE);
        } else if (quoteRequestEntity.isRenew()) {
            llWhenPolicyExpiring.setVisibility(View.VISIBLE);
        } else if (quoteRequestEntity.isDontRem()) {
            llWhenPolicyExpiring.setVisibility(View.VISIBLE);
            llVarientDetails.setVisibility(View.VISIBLE);
        }
    }

    private void setListeners() {

        autoCarMake.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(CarDetailsActivity.this, "" + makeAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init_widgets() {
        llWhenPolicyExpiring = (LinearLayout) findViewById(R.id.llWhenPolicyExpiring);
        llVarientDetails = (LinearLayout) findViewById(R.id.llVarientDetails);
        llAdditionalDetails = (LinearLayout) findViewById(R.id.llAdditionalDetails);
        llAdditionAcc = (LinearLayout) findViewById(R.id.llAdditionAcc);
        llNcb = (LinearLayout) findViewById(R.id.llNcb);
        spCarMake = (Spinner) findViewById(R.id.spCarMake);
        spCarFuelType = (Spinner) findViewById(R.id.spCarFuelType);
        spCarVarient = (Spinner) findViewById(R.id.spCarVarient);


        autoCarMake = (AutoCompleteTextView) findViewById(R.id.autoCarMake);
    }


}
