package com.android.policyboss.core.controller.garage;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestEntity.RegisterGarajRequestEntity;

/**
 * Created by Rajeev Ranjan on 12/06/2017.
 */

public interface IGarage {
    void registerGarage(RegisterGarajRequestEntity entity, IResponseSubcriber iResponseSubcriber);
}
