package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.models.SummaryEntity;

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
