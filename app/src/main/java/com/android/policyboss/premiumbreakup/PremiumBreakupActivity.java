package com.android.policyboss.premiumbreakup;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.models.CommonAddonEntity;
import com.android.policyboss.core.models.ResponseEntity;

public class PremiumBreakupActivity extends BaseActivity {

    public static String PREMIUM_BREAKUP = "premium_breakup";
    public static String APPLY_ADDON = "addon_applied";
    ResponseEntity premiumBreakUp;
    CommonAddonEntity commonAddonEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_breakup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().getParcelableExtra(PREMIUM_BREAKUP) != null) {
            premiumBreakUp = (ResponseEntity) getIntent().getParcelableExtra(PREMIUM_BREAKUP);
        }

        if (getIntent().getParcelableExtra(APPLY_ADDON) != null) {
            commonAddonEntity = (CommonAddonEntity) getIntent().getParcelableExtra(APPLY_ADDON);
        }


    }

}
