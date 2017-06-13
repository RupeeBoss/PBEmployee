package com.android.policyboss.core.requestbuilders;

import com.android.policyboss.core.RetroRequestBuilder;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.core.response.LoginResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class AuthenticationRequestBuilder extends RetroRequestBuilder {

    public final static String SUB_URL = "/PBService.svc";

    public AuthenticationRequestBuilder.AuthenticationNetworkService getService() {

        return super.build().create(AuthenticationRequestBuilder.AuthenticationNetworkService.class);
    }

    public interface AuthenticationNetworkService {

        @POST(SUB_URL + "/Login")
        Call<LoginResponse> login(@Body HashMap<String, String> body);


    }
}
