package com.android.policyboss.core.requestbuilders;

import com.android.policyboss.core.RetroRequestBuilder;
import com.android.policyboss.core.requestmodels.VarientMasterRequestEntity;
import com.android.policyboss.core.response.VarientMasterResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class VarientMasterRequestBuilder extends RetroRequestBuilder {
    public VarientMasterRequestBuilder.VarientMasterNetworkService getService() {

        return super.build().create(VarientMasterRequestBuilder.VarientMasterNetworkService.class);
    }

    public interface VarientMasterNetworkService {
        @Headers({
                "UserName: test",
                "Password: test@123"
        })
        @POST(RetroRequestBuilder.secondaryUrl + "/VariantMaster")
        Call<VarientMasterResponse> getVarient(@Body VarientMasterRequestEntity varientMasterRequestEntity);


    }
}
