package com.android.policyboss.core.responsemodels;

import io.realm.RealmObject;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class VarientEntity extends RealmObject {
    /**
     * Variant_Id : 50010
     * Variant_Name : DTSI ESUGIII 180
     * Make_ID : 38
     * Model_ID : 943
     * Make_Name : BAJAJ
     * Model_Name : PULSAR
     * Fuel_ID : 1
     * Cubic_Capacity : 180
     * Seating_Capacity : 2
     * ExShoroomPrice : 62540
     * ModifyOn : 4/29/2017 4:24:36 PM
     */

    private int Variant_Id;
    private String Variant_Name;
    private int Make_ID;
    private int Model_ID;
    private String Make_Name;
    private String Model_Name;
    private int Fuel_ID;
    private int Cubic_Capacity;
    private int Seating_Capacity;
    private int ExShoroomPrice;
    private String ModifyOn;

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

    public String getMake_Name() {
        return Make_Name;
    }

    public void setMake_Name(String Make_Name) {
        this.Make_Name = Make_Name;
    }

    public String getModel_Name() {
        return Model_Name;
    }

    public void setModel_Name(String Model_Name) {
        this.Model_Name = Model_Name;
    }

    public int getFuel_ID() {
        return Fuel_ID;
    }

    public void setFuel_ID(int Fuel_ID) {
        this.Fuel_ID = Fuel_ID;
    }

    public int getCubic_Capacity() {
        return Cubic_Capacity;
    }

    public void setCubic_Capacity(int Cubic_Capacity) {
        this.Cubic_Capacity = Cubic_Capacity;
    }

    public int getSeating_Capacity() {
        return Seating_Capacity;
    }

    public void setSeating_Capacity(int Seating_Capacity) {
        this.Seating_Capacity = Seating_Capacity;
    }

    public int getExShoroomPrice() {
        return ExShoroomPrice;
    }

    public void setExShoroomPrice(int ExShoroomPrice) {
        this.ExShoroomPrice = ExShoroomPrice;
    }

    public String getModifyOn() {
        return ModifyOn;
    }

    public void setModifyOn(String ModifyOn) {
        this.ModifyOn = ModifyOn;
    }
}
