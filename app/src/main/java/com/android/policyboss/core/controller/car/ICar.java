package com.android.policyboss.core.controller.car;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestEntity.CarRequestEntity;

/**
 * Created by Nilesh Birhade on 14-11-2017.
 */

public interface ICar {

    void getCarQuote(CarRequestEntity entity, IResponseSubcriber iResponseSubcriber);

    void getCarPremium(IResponseSubcriber iResponseSubcriber);
}
