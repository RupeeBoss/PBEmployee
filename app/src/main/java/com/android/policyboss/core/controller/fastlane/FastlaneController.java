package com.android.policyboss.core.controller.fastlane;

import android.content.Context;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestbuilders.FastLaneRequestBuilder;
import com.android.policyboss.core.response.FastLaneResponse;

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

public class FastlaneController implements IFastLane {

    FastLaneRequestBuilder.FastLaneNetworkService fastLaneNetworkService;
    Context mContext;

    public FastlaneController(Context context) {
        fastLaneNetworkService = new FastLaneRequestBuilder().getService();
        mContext = context;
    }

    @Override
    public void getCarDetails(String vehicleNumber, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<String, String>();
        body.put("RegistrationNumber", vehicleNumber);

        fastLaneNetworkService.getVehicleDetails(body).enqueue(new Callback<FastLaneResponse>() {
            @Override
            public void onResponse(Response<FastLaneResponse> response, Retrofit retrofit) {
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
