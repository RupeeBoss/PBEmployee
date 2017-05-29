package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.VariantMasterEntity;


import java.util.List;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class VarientMasterResponse extends APIResponse {

    private List<VariantMasterEntity> VariantList;

    public List<VariantMasterEntity> getVariantList() {
        return VariantList;
    }

    public void setVariantList(List<VariantMasterEntity> VariantList) {
        this.VariantList = VariantList;
    }


}
