package com.android.policyboss.core.requestbuilders;

import com.android.policyboss.core.RetroRequestBuilder;
import com.android.policyboss.core.models.LeadDetailRequest;
import com.android.policyboss.core.response.LeadCreateResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 12/06/2017.
 */

public class CreateLeadRequestBuilder extends RetroRequestBuilder {
    public final static String SUB_URL = "/PBService.svc";

    public CreateLeadRequestBuilder.CreateLeadNetworkService getService() {

        return super.build().create(CreateLeadRequestBuilder.CreateLeadNetworkService.class);
    }

    public interface CreateLeadNetworkService {

        @POST(SUB_URL + "/FastLaneService")
        Call<LeadCreateResponse> getLead(@Body LeadDetailRequest leadDetailRequest);


    }
}
