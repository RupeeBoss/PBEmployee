package com.android.policyboss.core.controller.fastlane;

import com.android.policyboss.core.IResponseSubcriber;

/**
 * Created by Nilesh Birhade on 29-05-2017.
 */

public interface IFastLane {
    void getCarDetails(String vehicleNumber, IResponseSubcriber iResponseSubcriber);
}
