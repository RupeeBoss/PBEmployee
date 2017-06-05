package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.HealthQuotesEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 05/06/2017.
 */

public class HealthQuoteResponse extends APIResponse {
    private List<HealthQuotesEntity> HealthQuotes;

    public List<HealthQuotesEntity> getHealthQuotes() {
        return HealthQuotes;
    }

    public void setHealthQuotes(List<HealthQuotesEntity> HealthQuotes) {
        this.HealthQuotes = HealthQuotes;
    }


}
