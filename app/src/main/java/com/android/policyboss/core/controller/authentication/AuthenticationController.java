package com.android.policyboss.core.controller.authentication;

import android.content.Context;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.fastlane.IFastLane;
import com.android.policyboss.core.requestbuilders.AuthenticationRequestBuilder;
import com.android.policyboss.core.requestbuilders.FastLaneRequestBuilder;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.core.response.LoginResponse;
import com.android.policyboss.facade.LoginFacade;

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

public class AuthenticationController implements IAuthentication {

    AuthenticationRequestBuilder.AuthenticationNetworkService authenticationNetworkService;
    Context mContext;

    public AuthenticationController(Context context) {
        authenticationNetworkService = new AuthenticationRequestBuilder().getService();
        mContext = context;
    }


    @Override
    public void login(String empCode, String pass, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Username", empCode);
        hashMap.put("Password", pass);
        authenticationNetworkService.login(hashMap).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                if (response.body() != null) {

                    if (response.body().getStatusNo() == 0) {
                        new LoginFacade(mContext).storeUserCredentials(response.body().getUserResponse());
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
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }
}
