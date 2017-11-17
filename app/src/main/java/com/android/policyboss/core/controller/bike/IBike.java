package com.android.policyboss.core.controller.bike;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestEntity.BikePremiumRequestEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.requestEntity.SaveAddOnRequestEntity;

/**
 * Created by Nilesh Birhade on 03-07-2017.
 */

public interface IBike {

    void getBikeQuote(BikeRequestEntity entity, IResponseSubcriber iResponseSubcriber);

    void getBikePremium(IResponseSubcriber iResponseSubcriber);

    void saveAddOn(SaveAddOnRequestEntity saveAddOnRequestEntity,IResponseSubcriber iResponseSubcriber);
}
