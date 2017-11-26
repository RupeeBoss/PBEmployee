package com.android.policyboss.core.controller.garage;

import android.content.Context;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.createlead.ICreateLead;
import com.android.policyboss.core.models.LeadDetailRequest;
import com.android.policyboss.core.requestEntity.RegisterGarajRequestEntity;
import com.android.policyboss.core.requestbuilders.CreateLeadRequestBuilder;
import com.android.policyboss.core.requestbuilders.GarageRequestBuilder;
import com.android.policyboss.core.response.GarajRegisterResponse;
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

public class RegisterGarageController implements IGarage {
    GarageRequestBuilder.GarageNetworkService garageNetworkService;
    Context mContext;


    public RegisterGarageController(Context mContext) {
        garageNetworkService = new GarageRequestBuilder().getService();
        this.mContext = mContext;
    }


    @Override
    public void registerGarage(RegisterGarajRequestEntity entity, final IResponseSubcriber iResponseSubcriber) {

        garageNetworkService.register(entity).enqueue(new Callback<GarajRegisterResponse>() {
            @Override
            public void onResponse(Response<GarajRegisterResponse> response, Retrofit retrofit) {
                if (response.body() != null) {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
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
