package com.android.policyboss.core.controller.database;

import java.util.List;

/**
 * Created by Nilesh Birhade on 30-05-2017.
 */

public interface IDBController {

    List<String> getMakeList();

    int getMakeID(String makeName);

    List<String> getModelList(int makeID);

    int getModelID(int makeID, String modelName);


    List<String> getVariantList(int modelID);

    int getVariantID(String variantName);

    List<String> getCity();

    int getCityID(String cityName);

    List<String> getFuelType(int modelID);

    int getFuelID(String fuelID, int modelID);

}
