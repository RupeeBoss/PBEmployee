package com.android.policyboss.core.controller.motorquote;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestEntity.MotorQuotesReqEntity;

/**
 * Created by IN-RB on 31-05-2017.
 */

public interface IMotorquote {

    void getQuoteDetails(MotorQuotesReqEntity motorQuotesReqEntity, IResponseSubcriber iResponseSubcriber);
}
