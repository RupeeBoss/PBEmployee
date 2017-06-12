package com.android.policyboss.webview;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.carinsurance.CarQuoteGenerate;
import com.android.policyboss.healthinsurance.HealthQuoteActivity;
import com.android.policyboss.utility.MyWebViewClient;

public class WebViewBuyInsurenceActivity extends BaseActivity {
    WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_insurence);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        webView = (WebView) findViewById(R.id.webView);

        if (getIntent().getStringExtra(CarQuoteGenerate.CAR_BUYNOW) != null) {
            url = getIntent().getStringExtra(CarQuoteGenerate.CAR_BUYNOW);
        } else if (getIntent().getStringExtra(CarQuoteGenerate.CAR_BUYNOW) != null) {
            url = getIntent().getStringExtra(HealthQuoteActivity.HEALTH_BUYNOW);
        }

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(false);

        settings.setLoadsImagesAutomatically(true);
        settings.setLightTouchEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);

        MyWebViewClient webViewClient = new MyWebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setBuiltInZoomControls(true);

        Log.d("PROPOSAL_URL", url);
        webView.loadUrl(url);
    }

}
