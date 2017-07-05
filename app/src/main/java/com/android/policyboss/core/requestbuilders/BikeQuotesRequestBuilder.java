package com.android.policyboss.core.requestbuilders;

import com.android.policyboss.core.NodeRetroRequestBuilder;
import com.android.policyboss.core.models.QuoteRequestEntity;
import com.android.policyboss.core.requestEntity.BikePremiumRequestEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.response.BikePremiumResponse;
import com.android.policyboss.core.response.BikeUniqueResponse;
import com.android.policyboss.core.response.MotorQuotesResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 31-05-2017.
 */

public class BikeQuotesRequestBuilder extends NodeRetroRequestBuilder {


    public BikeQuotesRequestBuilder.BikeQuotesNetworkService getService() {

        return super.build().create(BikeQuotesRequestBuilder.BikeQuotesNetworkService.class);
    }

    public interface BikeQuotesNetworkService {

        @POST("/quote/premium_initiate")
        Call<BikeUniqueResponse> getBikeUniqueID(@Body BikeRequestEntity body);

        @POST("/quote/premium_list_db")
        Call<BikePremiumResponse> getBikePremiumList(@Body BikePremiumRequestEntity body);
    }
}
