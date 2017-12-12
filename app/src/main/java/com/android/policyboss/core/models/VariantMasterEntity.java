package com.android.policyboss.core.models;

import io.realm.RealmObject;

public class VariantMasterEntity extends RealmObject {

    /**
     * Cubic_Capacity : 150
     * ExShoroomPrice : 40000
     * Fuel_Code : PTL
     * Fuel_ID : 1
     * Fuel_Name : Petrol
     * Model_ID : 349
     * Seating_Capacity : 2
     * Variant_ID : 1628
     * Variant_Name :
     */

    private String Cubic_Capacity;
    private String ExShoroomPrice;
    private String Fuel_Code;
    private int Fuel_ID;
    private String Fuel_Name;
    private int Model_ID;
    private String Seating_Capacity;
    private int Variant_ID;
    private String Variant_Name;

    public String getCubic_Capacity() {
        return Cubic_Capacity;
    }

    public void setCubic_Capacity(String Cubic_Capacity) {
        this.Cubic_Capacity = Cubic_Capacity;
    }

    public String getExShoroomPrice() {
        return ExShoroomPrice;
    }

    public void setExShoroomPrice(String ExShoroomPrice) {
        this.ExShoroomPrice = ExShoroomPrice;
    }

    public String getFuel_Code() {
        return Fuel_Code;
    }

    public void setFuel_Code(String Fuel_Code) {
        this.Fuel_Code = Fuel_Code;
    }

    public int getFuel_ID() {
        return Fuel_ID;
    }

    public void setFuel_ID(int Fuel_ID) {
        this.Fuel_ID = Fuel_ID;
    }

    public String getFuel_Name() {
        return Fuel_Name;
    }

    public void setFuel_Name(String Fuel_Name) {
        this.Fuel_Name = Fuel_Name;
    }

    public int getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(int Model_ID) {
        this.Model_ID = Model_ID;
    }

    public String getSeating_Capacity() {
        return Seating_Capacity;
    }

    public void setSeating_Capacity(String Seating_Capacity) {
        this.Seating_Capacity = Seating_Capacity;
    }

    public int getVariant_ID() {
        return Variant_ID;
    }

    public void setVariant_ID(int Variant_ID) {
        this.Variant_ID = Variant_ID;
    }

    public String getVariant_Name() {
        return Variant_Name;
    }

    public void setVariant_Name(String Variant_Name) {
        this.Variant_Name = Variant_Name;
    }
}