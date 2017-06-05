package com.android.policyboss.core.requestEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 05/06/2017.
 */

public class HealthRequestEntity {
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


}
