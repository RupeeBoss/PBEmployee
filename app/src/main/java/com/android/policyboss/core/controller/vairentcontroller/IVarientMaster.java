package com.android.policyboss.core.controller.vairentcontroller;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestmodels.VarientMasterRequestEntity;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public interface IVarientMaster {
    void getVarientMaster(VarientMasterRequestEntity varientMasterRequestEntity, IResponseSubcriber iResponseSubcriber);
}
