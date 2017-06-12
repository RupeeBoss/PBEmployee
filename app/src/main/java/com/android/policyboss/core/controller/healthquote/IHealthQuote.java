package com.android.policyboss.core.controller.healthquote;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestEntity.HealthRequestEntity;

/**
 * Created by Rajeev Ranjan on 05/06/2017.
 */

public interface IHealthQuote {
    void getHealthQuotes(HealthRequestEntity healthRequestEntity, IResponseSubcriber iResponseSubcriber);
}
