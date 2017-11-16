package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.AddonAmbulanceChargeCoverEntity;
import com.android.policyboss.core.models.AddonConsumableCoverEntity;
import com.android.policyboss.core.models.AddonEngineProtectorCoverEntity;
import com.android.policyboss.core.models.AddonHospitalCashCoverEntity;
import com.android.policyboss.core.models.AddonInvoicePriceCoverEntity;
import com.android.policyboss.core.models.AddonKeyLockCoverEntity;
import com.android.policyboss.core.models.AddonMedicalExpenseCoverEntity;
import com.android.policyboss.core.models.AddonNcbProtectionCoverEntity;
import com.android.policyboss.core.models.AddonRoadAssistCoverEntity;
import com.android.policyboss.core.models.AddonWindshieldCoverEntity;
import com.android.policyboss.core.models.AddonZeroDepCoverEntity;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.models.SummaryEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nilesh Birhade on 15-11-2017.
 */

public class BikePremiumResponse extends APIResponse {

    private SummaryEntity Summary;
    private List<ResponseEntity> Response;

    public SummaryEntity getSummary() {
        return Summary;
    }

    public void setSummary(SummaryEntity Summary) {
        this.Summary = Summary;
    }

    public List<ResponseEntity> getResponse() {
        return Response;
    }

    public void setResponse(List<ResponseEntity> Response) {
        this.Response = Response;
    }


}
