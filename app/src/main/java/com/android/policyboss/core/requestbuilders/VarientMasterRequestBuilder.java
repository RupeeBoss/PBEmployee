package com.android.policyboss.core.requestbuilders;

import com.android.policyboss.core.RetroRequestBuilder;
import com.android.policyboss.core.response.AllCityMasterResponse;
import com.android.policyboss.core.response.AllMastersResponse;
import com.android.policyboss.core.response.VarientMasterResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class VarientMasterRequestBuilder extends RetroRequestBuilder {

    public final static String SUB_URL = "/PBService.svc";

    public VarientMasterRequestBuilder.VarientMasterNetworkService getService() {

        return super.build().create(VarientMasterRequestBuilder.VarientMasterNetworkService.class);
    }

    public interface VarientMasterNetworkService {
        @Headers({
                "UserName: test",
                "Password: test@123"
        })


        @POST(SUB_URL + "/VariantMasterService")
        Call<VarientMasterResponse> getVarient();

        @POST(SUB_URL + "/GetAllMasterService")
        Call<AllMastersResponse> getAllMasters(@Body HashMap<String, String> body);

        @POST(SUB_URL + "/GetVechicalCityService")
        Call<AllCityMasterResponse> getAllCity();
    }
}
