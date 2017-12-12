package com.android.policyboss.core.controller.fetchmaster;

import android.content.Context;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestbuilders.MotorQuotesRequestBuilder;
import com.android.policyboss.core.response.BikeMasterResponse;
import com.android.policyboss.core.response.CarMasterResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public class MasterController implements IMasterFetch {

    MotorQuotesRequestBuilder.MotorQuotesNetworkService motorQuotesNetworkService;
    Context mContext;

    public MasterController(Context context) {
        mContext = context;
        motorQuotesNetworkService = new MotorQuotesRequestBuilder().getService();
    }

    @Override
    public void getCarMaster(final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("ProductId", "1");

        motorQuotesNetworkService.getCarMaster(hashMap).enqueue(new Callback<CarMasterResponse>() {
            @Override
            public void onResponse(Response<CarMasterResponse> response, Retrofit retrofit) {
                if (response.body() != null) {

                    if (response.body().getStatusNo() == 0) {

                        new AsyncStoreCarMaster(mContext, response.body().getMasterData()).execute();

                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }

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
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }

            }
        });

    }

    @Override
    public void getBikeMaster(final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("ProductId", "10");

        motorQuotesNetworkService.getBikeMaster(hashMap).enqueue(new Callback<BikeMasterResponse>() {
            @Override
            public void onResponse(Response<BikeMasterResponse> response, Retrofit retrofit) {
                if (response.body() != null) {

                    if (response.body().getStatusNo() == 0) {
                        new AsyncStoreBikeMaster(mContext, response.body().getMasterData()).execute();
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }

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
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }

            }
        });
    }
}
