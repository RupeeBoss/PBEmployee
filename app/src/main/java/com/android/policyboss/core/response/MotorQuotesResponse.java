package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.MototrQuotesEntity;

import java.util.List;

/**
 * Created by IN-RB on 31-05-2017.
 */

public class MotorQuotesResponse extends APIResponse {


    private List<MototrQuotesEntity> MototrQuotes;

    public List<MototrQuotesEntity> getMototrQuotes() {
        return MototrQuotes;
    }

    public void setMototrQuotes(List<MototrQuotesEntity> MototrQuotes) {
        this.MototrQuotes = MototrQuotes;
    }


}
