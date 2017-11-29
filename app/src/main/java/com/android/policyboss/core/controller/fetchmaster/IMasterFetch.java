package com.android.policyboss.core.controller.fetchmaster;

import com.android.policyboss.core.IResponseSubcriber;

import io.realm.Realm;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public interface IMasterFetch {

    public void getCarMaster(IResponseSubcriber iResponseSubcriber);

    public void getBikeMaster(IResponseSubcriber iResponseSubcriber);

}
