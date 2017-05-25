package com.android.policyboss.core.requestmodels;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class VarientMasterRequestEntity {


    public VarientMasterRequestEntity(String fromDate, String toDate) {
        FromDate = fromDate;
        ToDate = toDate;
    }

    /**
     * FromDate : 24-03-2017

     * ToDate : 04-05-2017
     */

    private String FromDate;
    private String ToDate;

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String FromDate) {
        this.FromDate = FromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String ToDate) {
        this.ToDate = ToDate;
    }
}
