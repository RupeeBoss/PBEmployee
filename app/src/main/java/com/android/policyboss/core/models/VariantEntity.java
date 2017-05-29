package com.android.policyboss.core.models;

import io.realm.RealmObject;

/**
 * Created by Nilesh Birhade on 29-05-2017.
 */

public class VariantEntity extends RealmObject {
    private int Variant_Id;
    private String Variant_Name;

    public int getVariant_Id() {
        return Variant_Id;
    }

    public void setVariant_Id(int variant_Id) {
        Variant_Id = variant_Id;
    }

    public String getVariant_Name() {
        return Variant_Name;
    }

    public void setVariant_Name(String variant_Name) {
        Variant_Name = variant_Name;
    }
}
