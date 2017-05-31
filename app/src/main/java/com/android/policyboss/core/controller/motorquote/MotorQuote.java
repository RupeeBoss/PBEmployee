package com.android.policyboss.core.controller.motorquote;

import android.content.Context;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestEntity.MotorQuotesReqEntity;
import com.android.policyboss.core.requestbuilders.FastLaneRequestBuilder;
import com.android.policyboss.core.requestbuilders.MotorQuotesRequestBuilder;
import com.android.policyboss.core.response.FastLaneResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by IN-RB on 31-05-2017.
 */

public class MotorQuote implements Imotorquote {



    MotorQuotesRequestBuilder.MotorQuotesNetworkService motorQuotesNetworkService;
    Context mContext;


    public MotorQuote(Context mContext) {
        motorQuotesNetworkService = new  MotorQuotesRequestBuilder().getService();
        this.mContext = mContext;
    }

    @Override
    public void getQuoteDetails(MotorQuotesReqEntity motorQuotesReqEntity,final IResponseSubcriber iResponseSubcriber) {


        motorQuotesNetworkService.getQuoteDetails(motorQuotesReqEntity).enqueue(new Callback<FastLaneResponse>() {
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