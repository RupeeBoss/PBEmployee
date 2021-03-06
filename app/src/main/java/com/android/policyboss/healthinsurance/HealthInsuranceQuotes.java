package com.android.policyboss.healthinsurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.models.HealthQuotesEntity;
import com.android.policyboss.core.response.HealthQuoteResponse;
import com.android.policyboss.webview.CommonWebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class HealthInsuranceQuotes extends BaseActivity {

    public static final String HEALTH_BUYNOW = "health_buy_now";

    HealthQuoteResponse healthQuoteResponse;
    List<HealthQuotesEntity> listHealthQuoteEntity;

    RecyclerView rvHealthQuotes;
    HealthQuotesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_insurance_quotes);
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

    public void BuyHealth(HealthQuotesEntity entity) {
     /*   Intent intent = new Intent(this, WebViewBuyInsurenceActivity.class);
        intent.putExtra(HEALTH_BUYNOW, entity.getProposerPageUrl());
        startActivity(intent);
*/
        startActivity(new Intent(this, CommonWebViewActivity.class)
                .putExtra("URL", entity.getProposerPageUrl())
                .putExtra("NAME", "Health")
                .putExtra("TITLE", "Health Insurance"));
    }

    private void initialise_widget() {

        rvHealthQuotes = (RecyclerView) findViewById(R.id.rvHealthQuotes);
        rvHealthQuotes.setLayoutManager(new LinearLayoutManager(this));
    }
}
