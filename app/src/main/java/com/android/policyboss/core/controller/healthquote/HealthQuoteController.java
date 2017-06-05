package com.android.policyboss.core.controller.healthquote;

import android.content.Context;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestEntity.HealthRequestEntity;
import com.android.policyboss.core.requestbuilders.HealthQuoteRequestBuilder;
import com.android.policyboss.core.response.HealthQuoteResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rajeev Ranjan on 05/06/2017.
 */

public class HealthQuoteController implements IHealthQuote {
    HealthQuoteRequestBuilder.HealthQuotesNetworkService healthQuotesNetworkService;
    Context mContext;


    public HealthQuoteController(Context mContext) {
        healthQuotesNetworkService = new HealthQuoteRequestBuilder().getService();
        this.mContext = mContext;
    }

    @Override
    public void getCarQuotes(HealthRequestEntity healthRequestEntity, final IResponseSubcriber iResponseSubcriber) {

        healthQuotesNetworkService.getHealthQuoteDetails(healthRequestEntity).enqueue(new Callback<HealthQuoteResponse>() {
            @Override
            public void onResponse(Response<HealthQuoteResponse> response, Retrofit retrofit) {
                if (response.body().getStatusNo() == 0) {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }
}
