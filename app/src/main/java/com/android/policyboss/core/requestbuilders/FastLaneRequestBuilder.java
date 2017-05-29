package com.android.policyboss.core.requestbuilders;

import com.android.policyboss.core.RetroRequestBuilder;
import com.android.policyboss.core.response.FastLaneResponse;
import com.android.policyboss.core.response.VarientMasterResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class FastLaneRequestBuilder extends RetroRequestBuilder {

    public final static String SUB_URL = "/PBService.svc";

    public FastLaneRequestBuilder.FastLaneNetworkService getService() {

        return super.build().create(FastLaneRequestBuilder.FastLaneNetworkService.class);
    }

    public interface FastLaneNetworkService {

        @POST(SUB_URL + "/VariantMasterService")
        Call<FastLaneResponse> getVehicleDetails(@Body HashMap<String, String> body);


    }
}
