package com.android.policyboss.core.models;

import io.realm.RealmObject;

public class ModelMasterEntity extends RealmObject {
    /**
     * Make_ID : 4
     * Model_ID : 270
     * Model_Name : 1 Series
     */

    private int Make_ID;
    private int Model_ID;
    private String Model_Name;

    public int getMake_ID() {
        return Make_ID;
    }

    public void setMake_ID(int Make_ID) {
        this.Make_ID = Make_ID;
    }

    public int getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(int Model_ID) {
        this.Model_ID = Model_ID;
    }

    public String getModel_Name() {
        return Model_Name;
    }

    public void setModel_Name(String Model_Name) {
        this.Model_Name = Model_Name;
    }
}