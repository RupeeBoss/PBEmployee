package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;

/**
 * Created by Nilesh Birhade on 27-07-2017.
 */

public class GarajRegisterResponse extends APIResponse {


    /**
     * GarageCode : StrJA75LG
     * MobNo : 9845667890
     */

    private String GarageCode;
    private String MobNo;

    public String getGarageCode() {
        return GarageCode;
    }

    public void setGarageCode(String GarageCode) {
        this.GarageCode = GarageCode;
    }

    public String getMobNo() {
        return MobNo;
    }

    public void setMobNo(String MobNo) {
        this.MobNo = MobNo;
    }
}
