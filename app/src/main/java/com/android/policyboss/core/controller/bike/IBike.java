package com.android.policyboss.core.controller.bike;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestEntity.BikePremiumRequestEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;

/**
 * Created by Nilesh Birhade on 03-07-2017.
 */

public interface IBike {

    void getBikeQuote(BikeRequestEntity entity);

    void getBikePremium(IResponseSubcriber iResponseSubcriber);
}
