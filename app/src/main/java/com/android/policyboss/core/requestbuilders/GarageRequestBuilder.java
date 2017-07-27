package com.android.policyboss.core.requestbuilders;

import com.android.policyboss.core.GarageRetroRequestBuilder;
import com.android.policyboss.core.RetroRequestBuilder;
import com.android.policyboss.core.requestEntity.RegisterGarajRequestEntity;
import com.android.policyboss.core.response.GarajRegisterResponse;
import com.android.policyboss.core.response.LoginResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class GarageRequestBuilder extends GarageRetroRequestBuilder {

    //http://qa.policyboss.com/GarageAppService/PBService.svc/CreateGarage
    public final static String SUB_URL = "GarageAppService/PBService.svc";

    public GarageRequestBuilder.GarageNetworkService getService() {

        return super.build().create(GarageRequestBuilder.GarageNetworkService.class);
    }

    public interface GarageNetworkService {

        @POST(SUB_URL + "/CreateGarage")
        Call<GarajRegisterResponse> register(@Body RegisterGarajRequestEntity entity);


    }
}
