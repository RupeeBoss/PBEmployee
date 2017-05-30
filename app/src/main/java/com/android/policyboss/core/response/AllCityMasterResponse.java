package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.VehicleMasterEntity;

import java.util.List;

/**
 * Created by Nilesh Birhade on 30-05-2017.
 */

public class AllCityMasterResponse extends APIResponse {

    private List<VehicleMasterEntity> lstvehicle;

    public List<VehicleMasterEntity> getListvehicle() {
        return lstvehicle;
    }

    public void setListvehicle(List<VehicleMasterEntity> lstvehicle) {
        this.lstvehicle = lstvehicle;
    }


}
