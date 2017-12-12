package com.android.policyboss.core.models;

import io.realm.RealmObject;

public class MakeMasterEntity extends RealmObject {
    /**
     * Make_ID : 53
     * Make_Name : ACCURA
     */

    private int Make_ID;
    private String Make_Name;

    public int getMake_ID() {
        return Make_ID;
    }

    public void setMake_ID(int Make_ID) {
        this.Make_ID = Make_ID;
    }

    public String getMake_Name() {
        return Make_Name;
    }

    public void setMake_Name(String Make_Name) {
        this.Make_Name = Make_Name;
    }
}