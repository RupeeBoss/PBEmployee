package com.android.policyboss.core.models;

import android.os.Parcel;
import android.os.Parcelable;

public class HealthQuotesEntity implements Parcelable {
    public static final Parcelable.Creator<HealthQuotesEntity> CREATOR = new Parcelable.Creator<HealthQuotesEntity>() {
        @Override
        public HealthQuotesEntity createFromParcel(Parcel source) {
            return new HealthQuotesEntity(source);
        }

        @Override
        public HealthQuotesEntity[] newArray(int size) {
            return new HealthQuotesEntity[size];
        }
    };
    /**
     * BroucherDownloadLink : /
     * CustomerReferenceID : 53313
     * Deductible_Amount : 0
     * Discount : 0
     * DiscountPercent : null
     * GrossPremium : 5789
     * Group_name : null
     * InsurerId : 2
     * InsurerLogoName : Bharti_Axa_General.png
     * InsurerName : Bharti Axa
     * IsOnlinePayment : true
     * KeyFeatures :
     * NetPremium : 6657
     * OtherPlanID : IHS
     * PlanID : 95
     * PlanName : Smart Health Premium
     * PolicyTermYear : 1
     * Premium : null
     * ProposerPageUrl : http://uat.policyboss.com/HealthInsuranceIndia/ContactDetails?CustomerReferenceNumber=53313&SelectedQuoteId=20&SupportsAgentID=2&CallingSource=POSPAPP&IsCustomer=0
     * QuoteId : 20
     * QuoteStatus : Success
     * ServiceTax : 868.35
     * SumInsured : 300000
     */

    private String BroucherDownloadLink;
    private int CustomerReferenceID;
    private String Deductible_Amount;
    private double Discount;
    private String DiscountPercent;
    private double GrossPremium;
    private String Group_name;
    private int InsurerId;
    private String InsurerLogoName;
    private String InsurerName;
    private boolean IsOnlinePayment;
    private String KeyFeatures;
    private double NetPremium;
    private String OtherPlanID;
    private int PlanID;
    private String PlanName;
    private int PolicyTermYear;
    private String Premium;
    private String ProposerPageUrl;
    private int QuoteId;
    private String QuoteStatus;
    private double ServiceTax;
    private double SumInsured;

    public HealthQuotesEntity() {
    }

    protected HealthQuotesEntity(Parcel in) {
        this.BroucherDownloadLink = in.readString();
        this.CustomerReferenceID = in.readInt();
        this.Deductible_Amount = in.readString();
        this.Discount = in.readDouble();
        this.DiscountPercent = in.readString();
        this.GrossPremium = in.readDouble();
        this.Group_name = in.readString();
        this.InsurerId = in.readInt();
        this.InsurerLogoName = in.readString();
        this.InsurerName = in.readString();
        this.IsOnlinePayment = in.readByte() != 0;
        this.KeyFeatures = in.readString();
        this.NetPremium = in.readDouble();
        this.OtherPlanID = in.readString();
        this.PlanID = in.readInt();
        this.PlanName = in.readString();
        this.PolicyTermYear = in.readInt();
        this.Premium = in.readString();
        this.ProposerPageUrl = in.readString();
        this.QuoteId = in.readInt();
        this.QuoteStatus = in.readString();
        this.ServiceTax = in.readDouble();
        this.SumInsured = in.readDouble();
    }

    public String getBroucherDownloadLink() {
        return BroucherDownloadLink;
    }

    public void setBroucherDownloadLink(String BroucherDownloadLink) {
        this.BroucherDownloadLink = BroucherDownloadLink;
    }

    public int getCustomerReferenceID() {
        return CustomerReferenceID;
    }

    public void setCustomerReferenceID(int CustomerReferenceID) {
        this.CustomerReferenceID = CustomerReferenceID;
    }

    public String getDeductible_Amount() {
        return Deductible_Amount;
    }

    public void setDeductible_Amount(String Deductible_Amount) {
        this.Deductible_Amount = Deductible_Amount;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public String getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(String DiscountPercent) {
        this.DiscountPercent = DiscountPercent;
    }

    public double getGrossPremium() {
        return GrossPremium;
    }

    public void setGrossPremium(double GrossPremium) {
        this.GrossPremium = GrossPremium;
    }

    public Object getGroup_name() {
        return Group_name;
    }

    public void setGroup_name(String Group_name) {
        this.Group_name = Group_name;
    }

    public int getInsurerId() {
        return InsurerId;
    }

    public void setInsurerId(int InsurerId) {
        this.InsurerId = InsurerId;
    }

    public String getInsurerLogoName() {
        return InsurerLogoName;
    }

    public void setInsurerLogoName(String InsurerLogoName) {
        this.InsurerLogoName = InsurerLogoName;
    }

    public String getInsurerName() {
        return InsurerName;
    }

    public void setInsurerName(String InsurerName) {
        this.InsurerName = InsurerName;
    }

    public boolean isIsOnlinePayment() {
        return IsOnlinePayment;
    }

    public void setIsOnlinePayment(boolean IsOnlinePayment) {
        this.IsOnlinePayment = IsOnlinePayment;
    }

    public String getKeyFeatures() {
        return KeyFeatures;
    }

    public void setKeyFeatures(String KeyFeatures) {
        this.KeyFeatures = KeyFeatures;
    }

    public double getNetPremium() {
        return NetPremium;
    }

    public void setNetPremium(double NetPremium) {
        this.NetPremium = NetPremium;
    }

    public String getOtherPlanID() {
        return OtherPlanID;
    }

    public void setOtherPlanID(String OtherPlanID) {
        this.OtherPlanID = OtherPlanID;
    }

    public int getPlanID() {
        return PlanID;
    }

    public void setPlanID(int PlanID) {
        this.PlanID = PlanID;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String PlanName) {
        this.PlanName = PlanName;
    }

    public int getPolicyTermYear() {
        return PolicyTermYear;
    }

    public void setPolicyTermYear(int PolicyTermYear) {
        this.PolicyTermYear = PolicyTermYear;
    }

    public Object getPremium() {
        return Premium;
    }

    public void setPremium(String Premium) {
        this.Premium = Premium;
    }

    public String getProposerPageUrl() {
        return ProposerPageUrl;
    }

    public void setProposerPageUrl(String ProposerPageUrl) {
        this.ProposerPageUrl = ProposerPageUrl;
    }

    public int getQuoteId() {
        return QuoteId;
    }

    public void setQuoteId(int QuoteId) {
        this.QuoteId = QuoteId;
    }

    public String getQuoteStatus() {
        return QuoteStatus;
    }

    public void setQuoteStatus(String QuoteStatus) {
        this.QuoteStatus = QuoteStatus;
    }

    public double getServiceTax() {
        return ServiceTax;
    }

    public void setServiceTax(double ServiceTax) {
        this.ServiceTax = ServiceTax;
    }

    public double getSumInsured() {
        return SumInsured;
    }

    public void setSumInsured(double SumInsured) {
        this.SumInsured = SumInsured;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.BroucherDownloadLink);
        dest.writeInt(this.CustomerReferenceID);
        dest.writeString(this.Deductible_Amount);
        dest.writeDouble(this.Discount);
        dest.writeString(this.DiscountPercent);
        dest.writeDouble(this.GrossPremium);
        dest.writeString(this.Group_name);
        dest.writeInt(this.InsurerId);
        dest.writeString(this.InsurerLogoName);
        dest.writeString(this.InsurerName);
        dest.writeByte(this.IsOnlinePayment ? (byte) 1 : (byte) 0);
        dest.writeString(this.KeyFeatures);
        dest.writeDouble(this.NetPremium);
        dest.writeString(this.OtherPlanID);
        dest.writeInt(this.PlanID);
        dest.writeString(this.PlanName);
        dest.writeInt(this.PolicyTermYear);
        dest.writeString(this.Premium);
        dest.writeString(this.ProposerPageUrl);
        dest.writeInt(this.QuoteId);
        dest.writeString(this.QuoteStatus);
        dest.writeDouble(this.ServiceTax);
        dest.writeDouble(this.SumInsured);
    }
}