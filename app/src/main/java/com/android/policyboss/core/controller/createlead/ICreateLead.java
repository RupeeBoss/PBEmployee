package com.android.policyboss.core.controller.createlead;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.models.LeadDetailRequest;

/**
 * Created by Rajeev Ranjan on 12/06/2017.
 */

public interface ICreateLead {
     void getLead(LeadDetailRequest leadDetailRequest, IResponseSubcriber iResponseSubcriber);
}
