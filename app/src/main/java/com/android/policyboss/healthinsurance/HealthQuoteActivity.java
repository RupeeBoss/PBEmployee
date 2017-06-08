package com.android.policyboss.healthinsurance;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.policyboss.R;
import com.android.policyboss.carinsurance.CarQuoteGenerate;
import com.android.policyboss.core.models.HealthQuotesEntity;
import com.android.policyboss.core.response.HealthQuoteResponse;

import java.util.ArrayList;
import java.util.List;

public class HealthQuoteActivity extends AppCompatActivity {

    HealthQuoteResponse healthQuoteResponse;
    List<HealthQuotesEntity> listHealthQuoteEntity;

    RecyclerView rvHealthQuotes;
    HealthQuotesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_quote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialise_widget();

        listHealthQuoteEntity = new ArrayList<>();
        if (getIntent().getParcelableExtra(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE) != null) {
            healthQuoteResponse = getIntent().getParcelableExtra(HealthInsuranceAgeDetailActivity.HEALTH_QUOTE);
            listHealthQuoteEntity = healthQuoteResponse.getHealthQuotes();
            mAdapter = new HealthQuotesAdapter(this, listHealthQuoteEntity);
            rvHealthQuotes.setAdapter(mAdapter);
        }
    }

    private void initialise_widget() {

        rvHealthQuotes = (RecyclerView) findViewById(R.id.rvHealthQuotes);
        rvHealthQuotes.setLayoutManager(new LinearLayoutManager(this));
    }
}
