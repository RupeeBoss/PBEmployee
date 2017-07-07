package com.android.policyboss.core.controller.bike;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.authentication.IAuthentication;
import com.android.policyboss.core.requestEntity.BikePremiumRequestEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.requestbuilders.AuthenticationRequestBuilder;
import com.android.policyboss.core.requestbuilders.BikeQuotesRequestBuilder;
import com.android.policyboss.core.response.BikePremiumResponse;
import com.android.policyboss.core.response.BikeUniqueResponse;
import com.android.policyboss.core.response.LoginResponse;
import com.android.policyboss.facade.LoginFacade;
import com.android.policyboss.utility.Constants;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class BikeController implements IBike {

    BikeQuotesRequestBuilder.BikeQuotesNetworkService bikeQuotesNetworkService;
    Context mContext;

    public BikeController(Context context) {
        bikeQuotesNetworkService = new BikeQuotesRequestBuilder().getService();
        mContext = context;
    }

    @Override
    public void getBikeQuote(BikeRequestEntity bikeRequestEntity) {

        bikeQuotesNetworkService.getBikeUniqueID(bikeRequestEntity).enqueue(new Callback<BikeUniqueResponse>() {
            @Override
            public void onResponse(Response<BikeUniqueResponse> response, Retrofit retrofit) {
                if (response.body() != null) {
                    Constants.getSharedPreferenceEditor(mContext)
                            .putString(Constants.BIKEQUOTE_UNIQUEID,
                                    response.body().getSummary().getRequest_Unique_Id());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void getBikePremium(final IResponseSubcriber iResponseSubcriber) {
        BikePremiumRequestEntity entity = new BikePremiumRequestEntity();
        entity.setSecret_key(Constants.SECRET_KEY);
        entity.setClient_key(Constants.CLIENT_KEY);
        entity.setResponse_version(Constants.VERSION_CODE);
        //entity.setSearch_reference_number(Constants.getSharedPreference(mContext).getString(Constants.BIKEQUOTE_UNIQUEID, ""));
        entity.setSearch_reference_number("SRN-OF8ERANF-O1LI-09DQ-BWXQ-JTEX7QYWWSZZ");
        bikeQuotesNetworkService.getBikePremiumList(entity).enqueue(new Callback<BikePremiumResponse>() {
            @Override
            public void onResponse(Response<BikePremiumResponse> response, Retrofit retrofit) {
                if (response.body() != null) {

                    Log.d("BIKE_PREMIUM", response.body().getSummary().getStatus());
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                    if (!response.body().getSummary().getStatus().equals("complete")) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                new BikeController(mContext).getBikePremium(iResponseSubcriber);
                            }
                        }, 10000);
                    } else {

                    }

                } else {
                    Log.d("getBikePremium", "" + "Error");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }
}
