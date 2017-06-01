package com.android.policyboss.core.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajeev Ranjan on 30/05/2017.
 */

public class QuoteRequestEntity implements Parcelable {

    /**
     * VehicleNo :
     * CustomerReferenceID :
     * ProductID : 1
     * ExShowRoomPrice : 0
     * ExpectedIDV : 0
     * IDVinExpiryPolicy : 0
     * DateofPurchaseofCar : 2017-05-30
     * VD_Amount : 0
     * PACoverValue : 0
     * VehicleCity_Id : 580
     * Profession_Id : 6
     * ValueOfElectricalAccessories : 0
     * ValueOfNonElectricalAccessories : 0
     * ValueOfBiFuelKit : 0
     * CurrentNCB : 0
     * IsClaimInExpiringPolicy : false
     * ApplyAntiTheftDiscount : false
     * ApplyAutomobileAssociationDiscount : false
     * AutomobileAssociationName :
     * AutomobileMembershipExpiryDate :
     * AutomobileAssociationMembershipNumber :
     * PaidDriverCover : false
     * OwnerDOB : null
     * Preveious_Insurer_Id : 0
     * ManufacturingYear : 2017
     * PolicyExpiryDate : null
     * VehicleRegisteredName : 1
     * Variant_ID : 690
     * RegistrationNumber :
     * PlaceofRegistration :
     * VehicleType : 1
     * Existing_CustomerReferenceID :
     * ContactName : sagar
     * ContactEmail :
     * ContactMobile :
     * LandmarkEmployeeCode :
     * SupportsAgentID : 123
     * SessionID : 59e979ed-dfc7-4d79-9f28-d427a554917e
     * SourceType : APP
     * InsurerIDArray :
     */

    private String VehicleNo;
    private String CustomerReferenceID;
    private int ProductID;
    private String ExShowRoomPrice;
    private String ExpectedIDV;
    private String IDVinExpiryPolicy;
    private String DateofPurchaseofCar;
    private String VD_Amount;
    private String PACoverValue;
    private int VehicleCity_Id;
    private int Profession_Id;
    private String ValueOfElectricalAccessories;
    private String ValueOfNonElectricalAccessories;
    private String ValueOfBiFuelKit;
    private String CurrentNCB;
    private boolean IsClaimInExpiringPolicy;
    private boolean ApplyAntiTheftDiscount;
    private boolean ApplyAutomobileAssociationDiscount;
    private String AutomobileAssociationName;
    private String AutomobileMembershipExpiryDate;
    private String AutomobileAssociationMembershipNumber;
    private boolean PaidDriverCover;
    private String OwnerDOB;
    private String Preveious_Insurer_Id;
    private int ManufacturingYear;
    private String PolicyExpiryDate;
    private int VehicleRegisteredName;
    private int Variant_ID;
    private String RegistrationNumber;
    private String PlaceofRegistration;
    private String VehicleType;
    private String Existing_CustomerReferenceID;
    private String ContactName;
    private String ContactEmail;
    private String ContactMobile;
    private String LandmarkEmployeeCode;
    private int SupportsAgentID;
    private String SessionID;
    private String SourceType;
    private String InsurerIDArray;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isRenew() {
        return isRenew;
    }

    public void setRenew(boolean renew) {
        isRenew = renew;
    }

    public boolean isInfoCorrect() {
        return isInfoCorrect;
    }

    public void setInfoCorrect(boolean infoCorrect) {
        isInfoCorrect = infoCorrect;
    }

    public QuoteRequestEntity() {
        VehicleNo = "";
        CustomerReferenceID = "";
        ProductID = 1;
        ExShowRoomPrice = "";
        ExpectedIDV = "";
        this.IDVinExpiryPolicy = IDVinExpiryPolicy;
        //DateofPurchaseofCar = dateofPurchaseofCar;
        this.VD_Amount = VD_Amount;
        this.PACoverValue = PACoverValue;

        Profession_Id = 6;
        ValueOfElectricalAccessories = "0";
        ValueOfNonElectricalAccessories = "0";
        ValueOfBiFuelKit = "0";
        CurrentNCB = "0";
        IsClaimInExpiringPolicy = false;
        ApplyAntiTheftDiscount = false;
        ApplyAutomobileAssociationDiscount = false;
        AutomobileAssociationName = "";
        AutomobileMembershipExpiryDate = "";
        AutomobileAssociationMembershipNumber = "";
        PaidDriverCover = false;
        OwnerDOB = null;
        Preveious_Insurer_Id = "0";
        //ManufacturingYear = "";
        PolicyExpiryDate = "";
        VehicleRegisteredName = 1;
        //Variant_ID = variant_ID;
        RegistrationNumber = "";
        PlaceofRegistration = "";
        VehicleType = "";
        Existing_CustomerReferenceID = "";
        ContactName = "";
        ContactEmail = "";
        ContactMobile = "";
        LandmarkEmployeeCode = "";
        SupportsAgentID = 2;
        SessionID = "";
        SourceType = "APP";
        InsurerIDArray = "";
    }

    /**
     * ProductID : 1
     * ExShowRoomPrice :
     * ExpectedIDV :
     * IDVinExpiryPolicy : 0
     * DateofPurchaseofCar : 2017-05-18
     * VD_Amount : 0
     * PACoverValue : 0
     * VehicleCity_Id : 579
     * Profession_Id : 6
     * ValueOfElectricalAccessories : 0
     * ValueOfNonElectricalAccessories : 0
     * ValueOfBiFuelKit : 0
     * CurrentNCB : 0
     * IsClaimInExpiringPolicy : false
     * ApplyAntiTheftDiscount : false
     * ApplyAutomobileAssociationDiscount : false
     * AutomobileAssociationName :
     * AutomobileMembershipExpiryDate :
     * AutomobileAssociationMembershipNumber :
     * PaidDriverCover : false
     * OwnerDOB : null
     * Preveious_Insurer_Id : 0
     * ManufacturingYear : 2017
     * VehicleRegisteredName : 1
     * Variant_ID : 688
     * RegistrationNumber :
     * PlaceofRegistration :
     * VehicleType :
     * Existing_CustomerReferenceID :
     * ContactName :
     * ContactEmail :
     * ContactMobile :
     * LandmarkEmployeeCode :
     * SessionID : 123456788
     * SupportsAgentID : 2
     * <p>
     * Policy_Expiry_Date : 2017-05-24
     * InsurerIDArray :
     */


    private boolean isNew;
    private boolean isRenew;
    private boolean isInfoCorrect;

    public boolean isDontRem() {
        return dontRem;
    }

    public void setDontRem(boolean dontRem) {
        this.dontRem = dontRem;
    }

    private boolean dontRem;


    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String VehicleNo) {
        this.VehicleNo = VehicleNo;
    }

    public String getCustomerReferenceID() {
        return CustomerReferenceID;
    }

    public void setCustomerReferenceID(String CustomerReferenceID) {
        this.CustomerReferenceID = CustomerReferenceID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getExShowRoomPrice() {
        return ExShowRoomPrice;
    }

    public void setExShowRoomPrice(String ExShowRoomPrice) {
        this.ExShowRoomPrice = ExShowRoomPrice;
    }

    public String getExpectedIDV() {
        return ExpectedIDV;
    }

    public void setExpectedIDV(String ExpectedIDV) {
        this.ExpectedIDV = ExpectedIDV;
    }

    public String getIDVinExpiryPolicy() {
        return IDVinExpiryPolicy;
    }

    public void setIDVinExpiryPolicy(String IDVinExpiryPolicy) {
        this.IDVinExpiryPolicy = IDVinExpiryPolicy;
    }

    public String getDateofPurchaseofCar() {
        return DateofPurchaseofCar;
    }

    public void setDateofPurchaseofCar(String DateofPurchaseofCar) {
        this.DateofPurchaseofCar = DateofPurchaseofCar;
    }

    public String getVD_Amount() {
        return VD_Amount;
    }

    public void setVD_Amount(String VD_Amount) {
        this.VD_Amount = VD_Amount;
    }

    public String getPACoverValue() {
        return PACoverValue;
    }

    public void setPACoverValue(String PACoverValue) {
        this.PACoverValue = PACoverValue;
    }

    public int getVehicleCity_Id() {
        return VehicleCity_Id;
    }

    public void setVehicleCity_Id(int VehicleCity_Id) {
        this.VehicleCity_Id = VehicleCity_Id;
    }

    public int getProfession_Id() {
        return Profession_Id;
    }

    public void setProfession_Id(int Profession_Id) {
        this.Profession_Id = Profession_Id;
    }

    public String getValueOfElectricalAccessories() {
        return ValueOfElectricalAccessories;
    }

    public void setValueOfElectricalAccessories(String ValueOfElectricalAccessories) {
        this.ValueOfElectricalAccessories = ValueOfElectricalAccessories;
    }

    public String getValueOfNonElectricalAccessories() {
        return ValueOfNonElectricalAccessories;
    }

    public void setValueOfNonElectricalAccessories(String ValueOfNonElectricalAccessories) {
        this.ValueOfNonElectricalAccessories = ValueOfNonElectricalAccessories;
    }

    public String getValueOfBiFuelKit() {
        return ValueOfBiFuelKit;
    }

    public void setValueOfBiFuelKit(String ValueOfBiFuelKit) {
        this.ValueOfBiFuelKit = ValueOfBiFuelKit;
    }

    public String getCurrentNCB() {
        return CurrentNCB;
    }

    public void setCurrentNCB(String CurrentNCB) {
        this.CurrentNCB = CurrentNCB;
    }

    public boolean isIsClaimInExpiringPolicy() {
        return IsClaimInExpiringPolicy;
    }

    public void setIsClaimInExpiringPolicy(boolean IsClaimInExpiringPolicy) {
        this.IsClaimInExpiringPolicy = IsClaimInExpiringPolicy;
    }

    public boolean isApplyAntiTheftDiscount() {
        return ApplyAntiTheftDiscount;
    }

    public void setApplyAntiTheftDiscount(boolean ApplyAntiTheftDiscount) {
        this.ApplyAntiTheftDiscount = ApplyAntiTheftDiscount;
    }

    public boolean isApplyAutomobileAssociationDiscount() {
        return ApplyAutomobileAssociationDiscount;
    }

    public void setApplyAutomobileAssociationDiscount(boolean ApplyAutomobileAssociationDiscount) {
        this.ApplyAutomobileAssociationDiscount = ApplyAutomobileAssociationDiscount;
    }

    public String getAutomobileAssociationName() {
        return AutomobileAssociationName;
    }

    public void setAutomobileAssociationName(String AutomobileAssociationName) {
        this.AutomobileAssociationName = AutomobileAssociationName;
    }

    public String getAutomobileMembershipExpiryDate() {
        return AutomobileMembershipExpiryDate;
    }

    public void setAutomobileMembershipExpiryDate(String AutomobileMembershipExpiryDate) {
        this.AutomobileMembershipExpiryDate = AutomobileMembershipExpiryDate;
    }

    public String getAutomobileAssociationMembershipNumber() {
        return AutomobileAssociationMembershipNumber;
    }

    public void setAutomobileAssociationMembershipNumber(String AutomobileAssociationMembershipNumber) {
        this.AutomobileAssociationMembershipNumber = AutomobileAssociationMembershipNumber;
    }

    public boolean isPaidDriverCover() {
        return PaidDriverCover;
    }

    public void setPaidDriverCover(boolean PaidDriverCover) {
        this.PaidDriverCover = PaidDriverCover;
    }

    public String getOwnerDOB() {
        return OwnerDOB;
    }

    public void setOwnerDOB(String OwnerDOB) {
        this.OwnerDOB = OwnerDOB;
    }

    public String getPreveious_Insurer_Id() {
        return Preveious_Insurer_Id;
    }

    public void setPreveious_Insurer_Id(String Preveious_Insurer_Id) {
        this.Preveious_Insurer_Id = Preveious_Insurer_Id;
    }

    public int getManufacturingYear() {
        return ManufacturingYear;
    }

    public void setManufacturingYear(int ManufacturingYear) {
        this.ManufacturingYear = ManufacturingYear;
    }

    public String getPolicyExpiryDate() {
        return PolicyExpiryDate;
    }

    public void setPolicyExpiryDate(String PolicyExpiryDate) {
        this.PolicyExpiryDate = PolicyExpiryDate;
    }

    public int getVehicleRegisteredName() {
        return VehicleRegisteredName;
    }

    public void setVehicleRegisteredName(int VehicleRegisteredName) {
        this.VehicleRegisteredName = VehicleRegisteredName;
    }

    public int getVariant_ID() {
        return Variant_ID;
    }

    public void setVariant_ID(int Variant_ID) {
        this.Variant_ID = Variant_ID;
    }

    public String getRegistrationNumber() {
        return RegistrationNumber;
    }

    public void setRegistrationNumber(String RegistrationNumber) {
        this.RegistrationNumber = RegistrationNumber;
    }

    public String getPlaceofRegistration() {
        return PlaceofRegistration;
    }

    public void setPlaceofRegistration(String PlaceofRegistration) {
        this.PlaceofRegistration = PlaceofRegistration;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String VehicleType) {
        this.VehicleType = VehicleType;
    }

    public String getExisting_CustomerReferenceID() {
        return Existing_CustomerReferenceID;
    }

    public void setExisting_CustomerReferenceID(String Existing_CustomerReferenceID) {
        this.Existing_CustomerReferenceID = Existing_CustomerReferenceID;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    public String getContactEmail() {
        return ContactEmail;
    }

    public void setContactEmail(String ContactEmail) {
        this.ContactEmail = ContactEmail;
    }

    public String getContactMobile() {
        return ContactMobile;
    }

    public void setContactMobile(String ContactMobile) {
        this.ContactMobile = ContactMobile;
    }

    public String getLandmarkEmployeeCode() {
        return LandmarkEmployeeCode;
    }

    public void setLandmarkEmployeeCode(String LandmarkEmployeeCode) {
        this.LandmarkEmployeeCode = LandmarkEmployeeCode;
    }

    public int getSupportsAgentID() {
        return SupportsAgentID;
    }

    public void setSupportsAgentID(int SupportsAgentID) {
        this.SupportsAgentID = SupportsAgentID;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String SessionID) {
        this.SessionID = SessionID;
    }

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String SourceType) {
        this.SourceType = SourceType;
    }

    public String getInsurerIDArray() {
        return InsurerIDArray;
    }

    public void setInsurerIDArray(String InsurerIDArray) {
        this.InsurerIDArray = InsurerIDArray;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.VehicleNo);
        dest.writeString(this.CustomerReferenceID);
        dest.writeInt(this.ProductID);
        dest.writeString(this.ExShowRoomPrice);
        dest.writeString(this.ExpectedIDV);
        dest.writeString(this.IDVinExpiryPolicy);
        dest.writeString(this.DateofPurchaseofCar);
        dest.writeString(this.VD_Amount);
        dest.writeString(this.PACoverValue);
        dest.writeInt(this.VehicleCity_Id);
        dest.writeInt(this.Profession_Id);
        dest.writeString(this.ValueOfElectricalAccessories);
        dest.writeString(this.ValueOfNonElectricalAccessories);
        dest.writeString(this.ValueOfBiFuelKit);
        dest.writeString(this.CurrentNCB);
        dest.writeByte(this.IsClaimInExpiringPolicy ? (byte) 1 : (byte) 0);
        dest.writeByte(this.ApplyAntiTheftDiscount ? (byte) 1 : (byte) 0);
        dest.writeByte(this.ApplyAutomobileAssociationDiscount ? (byte) 1 : (byte) 0);
        dest.writeString(this.AutomobileAssociationName);
        dest.writeString(this.AutomobileMembershipExpiryDate);
        dest.writeString(this.AutomobileAssociationMembershipNumber);
        dest.writeByte(this.PaidDriverCover ? (byte) 1 : (byte) 0);
        dest.writeString(this.OwnerDOB);
        dest.writeString(this.Preveious_Insurer_Id);
        dest.writeInt(this.ManufacturingYear);
        dest.writeString(this.PolicyExpiryDate);
        dest.writeInt(this.VehicleRegisteredName);
        dest.writeInt(this.Variant_ID);
        dest.writeString(this.RegistrationNumber);
        dest.writeString(this.PlaceofRegistration);
        dest.writeString(this.VehicleType);
        dest.writeString(this.Existing_CustomerReferenceID);
        dest.writeString(this.ContactName);
        dest.writeString(this.ContactEmail);
        dest.writeString(this.ContactMobile);
        dest.writeString(this.LandmarkEmployeeCode);
        dest.writeInt(this.SupportsAgentID);
        dest.writeString(this.SessionID);
        dest.writeString(this.SourceType);
        dest.writeString(this.InsurerIDArray);
        dest.writeByte(this.isNew ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isRenew ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isInfoCorrect ? (byte) 1 : (byte) 0);
        dest.writeByte(this.dontRem ? (byte) 1 : (byte) 0);
    }



    protected QuoteRequestEntity(Parcel in) {
        this.VehicleNo = in.readString();
        this.CustomerReferenceID = in.readString();
        this.ProductID = in.readInt();
        this.ExShowRoomPrice = in.readString();
        this.ExpectedIDV = in.readString();
        this.IDVinExpiryPolicy = in.readString();
        this.DateofPurchaseofCar = in.readString();
        this.VD_Amount = in.readString();
        this.PACoverValue = in.readString();
        this.VehicleCity_Id = in.readInt();
        this.Profession_Id = in.readInt();
        this.ValueOfElectricalAccessories = in.readString();
        this.ValueOfNonElectricalAccessories = in.readString();
        this.ValueOfBiFuelKit = in.readString();
        this.CurrentNCB = in.readString();
        this.IsClaimInExpiringPolicy = in.readByte() != 0;
        this.ApplyAntiTheftDiscount = in.readByte() != 0;
        this.ApplyAutomobileAssociationDiscount = in.readByte() != 0;
        this.AutomobileAssociationName = in.readString();
        this.AutomobileMembershipExpiryDate = in.readString();
        this.AutomobileAssociationMembershipNumber = in.readString();
        this.PaidDriverCover = in.readByte() != 0;
        this.OwnerDOB = in.readString();
        this.Preveious_Insurer_Id = in.readString();
        this.ManufacturingYear = in.readInt();
        this.PolicyExpiryDate = in.readString();
        this.VehicleRegisteredName = in.readInt();
        this.Variant_ID = in.readInt();
        this.RegistrationNumber = in.readString();
        this.PlaceofRegistration = in.readString();
        this.VehicleType = in.readString();
        this.Existing_CustomerReferenceID = in.readString();
        this.ContactName = in.readString();
        this.ContactEmail = in.readString();
        this.ContactMobile = in.readString();
        this.LandmarkEmployeeCode = in.readString();
        this.SupportsAgentID = in.readInt();
        this.SessionID = in.readString();
        this.SourceType = in.readString();
        this.InsurerIDArray = in.readString();
        this.isNew = in.readByte() != 0;
        this.isRenew = in.readByte() != 0;
        this.isInfoCorrect = in.readByte() != 0;
        this.dontRem = in.readByte() != 0;
    }

    public static final Parcelable.Creator<QuoteRequestEntity> CREATOR = new Parcelable.Creator<QuoteRequestEntity>() {
        @Override
        public QuoteRequestEntity createFromParcel(Parcel source) {
            return new QuoteRequestEntity(source);
        }

        @Override
        public QuoteRequestEntity[] newArray(int size) {
            return new QuoteRequestEntity[size];
        }
    };
}
