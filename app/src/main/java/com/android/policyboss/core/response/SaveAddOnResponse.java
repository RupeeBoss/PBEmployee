package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;

/**
 * Created by Rajeev Ranjan on 17/11/2017.
 */

public class SaveAddOnResponse extends APIResponse {
    /**
     * Msg : Data saved
     * Id : 4739
     */

    private String Msg;
    private int Id;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}
