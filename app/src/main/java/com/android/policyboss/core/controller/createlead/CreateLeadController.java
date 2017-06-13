package com.android.policyboss.core.controller.createlead;

import android.content.Context;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.models.LeadDetailRequest;
import com.android.policyboss.core.requestbuilders.CreateLeadRequestBuilder;
import com.android.policyboss.core.response.LeadCreateResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rajeev Ranjan on 12/06/2017.
 */

public class CreateLeadController implements ICreateLead {
    CreateLeadRequestBuilder.CreateLeadNetworkService createLeadNetworkService;
    Context mContext;


    public CreateLeadController(Context mContext) {
        createLeadNetworkService = new CreateLeadRequestBuilder().getService();
        this.mContext = mContext;
    }



    @Override
    public void getLead(LeadDetailRequest leadDetailRequest, final IResponseSubcriber iResponseSubcriber) {
        createLeadNetworkService.getLead(leadDetailRequest).enqueue(new Callback<LeadCreateResponse>() {
            @Override
            public void onResponse(Response<LeadCreateResponse> response, Retrofit retrofit) {
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
