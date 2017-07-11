package com.android.policyboss.core.controller.bike;

import android.content.Context;
import android.content.SharedPreferences;
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
    public void getBikeQuote(BikeRequestEntity bikeRequestEntity, final IResponseSubcriber iResponseSubcriber) {

        bikeQuotesNetworkService.getBikeUniqueID(bikeRequestEntity).enqueue(new Callback<BikeUniqueResponse>() {
            @Override
            public void onResponse(Response<BikeUniqueResponse> response, Retrofit retrofit) {
                if (response.body() != null) {
                    String UNIQUE = response.body().getSummary().getRequest_Unique_Id();
                    SharedPreferences.Editor edit = Constants.getSharedPreferenceEditor(mContext);
                    edit.putString(Constants.BIKEQUOTE_UNIQUEID,
                            UNIQUE);
                    edit.commit();
                    iResponseSubcriber.OnSuccess(response.body(), "");

                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
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

    @Override
    public void getBikePremium(final IResponseSubcriber iResponseSubcriber) {
        BikePremiumRequestEntity entity = new BikePremiumRequestEntity();
        entity.setSecret_key(Constants.SECRET_KEY);
        entity.setClient_key(Constants.CLIENT_KEY);
        entity.setResponse_version(Constants.VERSION_CODE);
        //Log.d("UNIQUE_REQUEST", Constants.getSharedPreference(mContext).getString(Constants.BIKEQUOTE_UNIQUEID, ""));
        entity.setSearch_reference_number(Constants.getSharedPreference(mContext).getString(Constants.BIKEQUOTE_UNIQUEID, ""));
        bikeQuotesNetworkService.getBikePremiumList(entity).enqueue(new Callback<BikePremiumResponse>() {
            @Override
            public void onResponse(Response<BikePremiumResponse> response, Retrofit retrofit) {
                if (response.body() != null) {
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
                    Log.d("BIKE_PREMIUM", "" + "Error");
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
