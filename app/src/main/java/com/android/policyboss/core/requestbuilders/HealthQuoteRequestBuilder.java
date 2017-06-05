package com.android.policyboss.core.requestbuilders;


import com.android.policyboss.core.RetroRequestBuilder;
import com.android.policyboss.core.requestEntity.HealthRequestEntity;
import com.android.policyboss.core.response.HealthQuoteResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 05/06/2017.
 */

public class HealthQuoteRequestBuilder extends RetroRequestBuilder {
    public final static String SUB_URL = "/PBService.svc";

    public HealthQuoteRequestBuilder.HealthQuotesNetworkService getService() {

        return super.build().create(HealthQuoteRequestBuilder.HealthQuotesNetworkService.class);
    }

    public interface HealthQuotesNetworkService {

        @POST(SUB_URL + "/GETHealthQuotes")
        Call<HealthQuoteResponse> getHealthQuoteDetails(@Body HealthRequestEntity healthRequestEntity);


    }
}
