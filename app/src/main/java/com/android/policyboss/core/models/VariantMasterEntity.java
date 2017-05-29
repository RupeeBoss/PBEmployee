package com.android.policyboss.core.models;

import io.realm.RealmObject;

public class VariantMasterEntity extends RealmObject{
    /**
     * Cubic_Capacity : 1497
     * ExShoroomPrice : 675000
     * Fuel_ID : 1
     * Make_ID : 10
     * Make_Name : Honda
     * Model_ID : 778
     * Model_Name : Mobilio
     * ModifyOn : 5/2/2017 2:05:22 PM
     * Seating_Capacity : 7
     * Variant_Id : 3081
     * Variant_Name : E
     */

    private int Cubic_Capacity;
    private String ExShoroomPrice;
    private int Fuel_ID;
    private int Make_ID;
    private String Make_Name;
    private int Model_ID;
    private String Model_Name;
    private String ModifyOn;
    private int Seating_Capacity;
    private int Variant_Id;
    private String Variant_Name;

    public int getCubic_Capacity() {
        return Cubic_Capacity;
    }

    public void setCubic_Capacity(int Cubic_Capacity) {
        this.Cubic_Capacity = Cubic_Capacity;
    }

    public String getExShoroomPrice() {
        return ExShoroomPrice;
    }

    public void setExShoroomPrice(String ExShoroomPrice) {
        this.ExShoroomPrice = ExShoroomPrice;
    }

    public int getFuel_ID() {
        return Fuel_ID;
    }

    public void setFuel_ID(int Fuel_ID) {
        this.Fuel_ID = Fuel_ID;
    }

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

    public String getModifyOn() {
        return ModifyOn;
    }

    public void setModifyOn(String ModifyOn) {
        this.ModifyOn = ModifyOn;
    }

    public int getSeating_Capacity() {
        return Seating_Capacity;
    }

    public void setSeating_Capacity(int Seating_Capacity) {
        this.Seating_Capacity = Seating_Capacity;
    }

    public int getVariant_Id() {
        return Variant_Id;
    }

    public void setVariant_Id(int Variant_Id) {
        this.Variant_Id = Variant_Id;
    }

    public String getVariant_Name() {
        return Variant_Name;
    }

    public void setVariant_Name(String Variant_Name) {
        this.Variant_Name = Variant_Name;
    }
}