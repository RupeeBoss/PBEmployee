package com.android.policyboss.bikeinsurance;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.response.BikePremiumResponse;
import com.google.gson.Gson;

import java.util.List;

public class BikeQuoteActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    List<ResponseEntity> mListBikeQuotes;
    RecyclerView bikeQuoteRecycler;
    BikeQuoteAdapter mAdapter;
    BikeRequestEntity bikeRequestEntity;

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
        showDialog();
        new BikeController(BikeQuoteActivity.this).getBikePremium(this);
    }

    private void initialize() {
        bikeQuoteRecycler = (RecyclerView) findViewById(R.id.bikeQuoteRecycler);
        bikeQuoteRecycler.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        bikeQuoteRecycler.setLayoutManager(mLayoutManager);


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof BikePremiumResponse) {
            mAdapter = new BikeQuoteAdapter(BikeQuoteActivity.this, (BikePremiumResponse) response);
            bikeQuoteRecycler.setAdapter(mAdapter);

        }

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(BikeQuoteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

    }
}
