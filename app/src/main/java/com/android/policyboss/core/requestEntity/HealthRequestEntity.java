package com.android.policyboss.core.requestEntity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajeev Ranjan on 05/06/2017.
 */

public class HealthRequestEntity implements Parcelable {
    public HealthRequestEntity() {
        CityID = 0;
        ContactEmail = "pramod.parit@rupeeboss.com";
        ContactMobile = "9930089092";
        ContactName = "pramod parit";
        DeductibleAmount = 0;
        ExistingCustomerReferenceID = 0;
        HealthType = "Health";
        MaritalStatusID = 1;
        PolicyFor = "";
        PolicyTermYear = 1;
        ProductID = 2;
        SessionID = "";
        SourceType = "APP";
        SumInsured = "";
        SupportsAgentID = 2;
        MemberList = null;
    }

    /**
     * CityID : 579
     * ContactEmail : pramod.parit@policyboss.com
     * ContactMobile : 8093793198
     * ContactName : Rajeev Ranjan
     * DeductibleAmount : 0
     * ExistingCustomerReferenceID : 0
     * HealthType : Health
     * MaritalStatusID : 1
     * MemberList : [{"MemberDOB":"01-01-1989","MemberGender":"M","MemberNumber":"1","MemberType":"Adult","MemberTypeID":"1"}]
     * PolicyFor : Self
     * PolicyTermYear : 1
     * ProductID : 2
     * SessionID : 123456788
     * SourceType : APP
     * SumInsured : 300000
     * SupportsAgentID : 2
     */


    private int CityID;
    private String ContactEmail;
    private String ContactMobile;
    private String ContactName;
    private int DeductibleAmount;
    private int ExistingCustomerReferenceID;
    private String HealthType;
    private int MaritalStatusID;
    private String PolicyFor;
    private int PolicyTermYear;
    private int ProductID;
    private String SessionID;
    private String SourceType;
    private String SumInsured;
    private int SupportsAgentID;
    private List<MemberListEntity> MemberList;

    public int getCityID() {
        return CityID;
    }

    public void setCityID(int CityID) {
        this.CityID = CityID;
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

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    public int getDeductibleAmount() {
        return DeductibleAmount;
    }

    public void setDeductibleAmount(int DeductibleAmount) {
        this.DeductibleAmount = DeductibleAmount;
    }

    public int getExistingCustomerReferenceID() {
        return ExistingCustomerReferenceID;
    }

    public void setExistingCustomerReferenceID(int ExistingCustomerReferenceID) {
        this.ExistingCustomerReferenceID = ExistingCustomerReferenceID;
    }

    public String getHealthType() {
        return HealthType;
    }

    public void setHealthType(String HealthType) {
        this.HealthType = HealthType;
    }

    public int getMaritalStatusID() {
        return MaritalStatusID;
    }

    public void setMaritalStatusID(int MaritalStatusID) {
        this.MaritalStatusID = MaritalStatusID;
    }

    public String getPolicyFor() {
        return PolicyFor;
    }

    public void setPolicyFor(String PolicyFor) {
        this.PolicyFor = PolicyFor;
    }

    public int getPolicyTermYear() {
        return PolicyTermYear;
    }

    public void setPolicyTermYear(int PolicyTermYear) {
        this.PolicyTermYear = PolicyTermYear;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
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

    public String getSumInsured() {
        return SumInsured;
    }

    public void setSumInsured(String SumInsured) {
        this.SumInsured = SumInsured;
    }

    public int getSupportsAgentID() {
        return SupportsAgentID;
    }

    public void setSupportsAgentID(int SupportsAgentID) {
        this.SupportsAgentID = SupportsAgentID;
    }

    public List<MemberListEntity> getMemberList() {
        return MemberList;
    }

    public void setMemberList(List<MemberListEntity> MemberList) {
        this.MemberList = MemberList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.CityID);
        dest.writeString(this.ContactEmail);
        dest.writeString(this.ContactMobile);
        dest.writeString(this.ContactName);
        dest.writeInt(this.DeductibleAmount);
        dest.writeInt(this.ExistingCustomerReferenceID);
        dest.writeString(this.HealthType);
        dest.writeInt(this.MaritalStatusID);
        dest.writeString(this.PolicyFor);
        dest.writeInt(this.PolicyTermYear);
        dest.writeInt(this.ProductID);
        dest.writeString(this.SessionID);
        dest.writeString(this.SourceType);
        dest.writeString(this.SumInsured);
        dest.writeInt(this.SupportsAgentID);
        dest.writeList(this.MemberList);
    }



    protected HealthRequestEntity(Parcel in) {
        this.CityID = in.readInt();
        this.ContactEmail = in.readString();
        this.ContactMobile = in.readString();
        this.ContactName = in.readString();
        this.DeductibleAmount = in.readInt();
        this.ExistingCustomerReferenceID = in.readInt();
        this.HealthType = in.readString();
        this.MaritalStatusID = in.readInt();
        this.PolicyFor = in.readString();
        this.PolicyTermYear = in.readInt();
        this.ProductID = in.readInt();
        this.SessionID = in.readString();
        this.SourceType = in.readString();
        this.SumInsured = in.readString();
        this.SupportsAgentID = in.readInt();
        this.MemberList = new ArrayList<MemberListEntity>();
        in.readList(this.MemberList, MemberListEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<HealthRequestEntity> CREATOR = new Parcelable.Creator<HealthRequestEntity>() {
        @Override
        public HealthRequestEntity createFromParcel(Parcel source) {
            return new HealthRequestEntity(source);
        }

        @Override
        public HealthRequestEntity[] newArray(int size) {
            return new HealthRequestEntity[size];
        }
    };
}
