package com.android.policyboss.core.models;

import io.realm.RealmObject;

/**
 * Created by Nilesh Birhade on 29-05-2017.
 */

public class MakeEntity extends RealmObject {

    private int Make_ID;
    private String Make_Name;

    public int getMake_ID() {
        return Make_ID;
    }

    public void setMake_ID(int make_ID) {
        Make_ID = make_ID;
    }

    public String getMake_Name() {
        return Make_Name;
    }

    public void setMake_Name(String make_Name) {
        Make_Name = make_Name;
    }
}
