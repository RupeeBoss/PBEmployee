package com.android.policyboss.core.controller.car;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;

/**
 * Created by Nilesh Birhade on 14-11-2017.
 */

public interface ICar {

    void getCarQuote(BikeRequestEntity entity, IResponseSubcriber iResponseSubcriber);

    void getCarPremium(IResponseSubcriber iResponseSubcriber);
}
