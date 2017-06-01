package com.android.policyboss.core.controller.motorquote;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.models.QuoteRequestEntity;

/**
 * Created by IN-RB on 31-05-2017.
 */

public interface IMotorquote {

    void getQuoteDetails(QuoteRequestEntity quoteRequestEntity, IResponseSubcriber iResponseSubcriber);
}
