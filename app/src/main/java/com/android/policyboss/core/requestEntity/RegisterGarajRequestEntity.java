package com.android.policyboss.core.requestEntity;

/**
 * Created by Nilesh Birhade on 27-07-2017.
 */

public class RegisterGarajRequestEntity {

    /**
     * Address : Rambo Garage, Madgaon
     * City : Mumbai
     * ContactPerson : Mahesh R
     * EmailId : string@gmail.com
     * EmpId : 277
     * ExistingCustomerBase : 12
     * MobNo : 9845667890
     * Name : String content Person
     * Remarks : String remarks
     * SegmentId : 2
     * TelNo : 0251-2434567
     */

    private String Address;
    private String City;
    private String ContactPerson;
    private String EmailId;
    private int EmpId;
    private String ExistingCustomerBase;
    private String MobNo;
    private String Name;
    private String Remarks;
    private int SegmentId;
    private String TelNo;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String ContactPerson) {
        this.ContactPerson = ContactPerson;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int EmpId) {
        this.EmpId = EmpId;
    }

    public String getExistingCustomerBase() {
        return ExistingCustomerBase;
    }

    public void setExistingCustomerBase(String ExistingCustomerBase) {
        this.ExistingCustomerBase = ExistingCustomerBase;
    }

    public String getMobNo() {
        return MobNo;
    }

    public void setMobNo(String MobNo) {
        this.MobNo = MobNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public int getSegmentId() {
        return SegmentId;
    }

    public void setSegmentId(int SegmentId) {
        this.SegmentId = SegmentId;
    }

    public String getTelNo() {
        return TelNo;
    }

    public void setTelNo(String TelNo) {
        this.TelNo = TelNo;
    }
}
