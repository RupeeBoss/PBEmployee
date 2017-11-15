package com.android.policyboss.core.models;

public class InsurerEntity {
    /**
     * _id : 58be94835f761783caf9408d
     * Insurer_ID : 12
     * Insurer_Name : The New India Assurance Co. Ltd.
     * IsActive : 1
     * CreatedOn : 2012-02-09
     * Insurer_Logo_Name : new_india.png
     * IsInternal : ""
     * Insurer_Code : New India Assurance
     * Insurer_Logo_Name_Mobile : "m-rel.png"
     */

    private String _id;
    private int Insurer_ID;
    private String Insurer_Name;
    private int IsActive;
    private String CreatedOn;
    private String Insurer_Logo_Name;
    private String IsInternal;
    private String Insurer_Code;
    private String Insurer_Logo_Name_Mobile;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getInsurer_ID() {
        return Insurer_ID;
    }

    public void setInsurer_ID(int Insurer_ID) {
        this.Insurer_ID = Insurer_ID;
    }

    public String getInsurer_Name() {
        return Insurer_Name;
    }

    public void setInsurer_Name(String Insurer_Name) {
        this.Insurer_Name = Insurer_Name;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int IsActive) {
        this.IsActive = IsActive;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public String getInsurer_Logo_Name() {
        return Insurer_Logo_Name;
    }

    public void setInsurer_Logo_Name(String Insurer_Logo_Name) {
        this.Insurer_Logo_Name = Insurer_Logo_Name;
    }

    public String getIsInternal() {
        return IsInternal;
    }

    public void setIsInternal(String IsInternal) {
        this.IsInternal = IsInternal;
    }

    public String getInsurer_Code() {
        return Insurer_Code;
    }

    public void setInsurer_Code(String Insurer_Code) {
        this.Insurer_Code = Insurer_Code;
    }

    public String getInsurer_Logo_Name_Mobile() {
        return Insurer_Logo_Name_Mobile;
    }

    public void setInsurer_Logo_Name_Mobile(String Insurer_Logo_Name_Mobile) {
        this.Insurer_Logo_Name_Mobile = Insurer_Logo_Name_Mobile;
    }
}
