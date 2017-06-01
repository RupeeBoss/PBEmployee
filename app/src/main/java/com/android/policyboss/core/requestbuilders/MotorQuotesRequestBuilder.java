package com.android.policyboss.core.requestbuilders;

import com.android.policyboss.core.RetroRequestBuilder;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.core.requestEntity.MotorQuotesReqEntity;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.core.response.MotorQuotesResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 31-05-2017.
 */

public class MotorQuotesRequestBuilder extends RetroRequestBuilder {


    public final static String SUB_URL = "/PBService.svc";

    public MotorQuotesRequestBuilder.MotorQuotesNetworkService getService() {

        return super.build().create(MotorQuotesRequestBuilder.MotorQuotesNetworkService.class);
    }

    public interface MotorQuotesNetworkService {

        @POST(SUB_URL + "/GETMotorQuotes")
        Call<MotorQuotesResponse> getQuoteDetails(@Body QuoteRequestEntity body);


    }
}
