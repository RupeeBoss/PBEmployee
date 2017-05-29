package com.android.policyboss.core.models;

import io.realm.RealmObject;

/**
 * Created by Nilesh Birhade on 29-05-2017.
 */

public class ModelEntity extends RealmObject {
    private int Model_ID;
    private String Model_Name;

    public int getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(int model_ID) {
        Model_ID = model_ID;
    }

    public String getModel_Name() {
        return Model_Name;
    }

    public void setModel_Name(String model_Name) {
        Model_Name = model_Name;
    }
}
