package com.android.policyboss.core.models;

public class LeadDetailRequest {


    /**
     * EmployeeID : 253
     * CustomerMobile : 111111110
     * ProductID : 1
     * DrivingLicenceUploadImage : imageupload
     * VehicleRegistrationNumber : MH12AA1212
     * RCUploadImage : ds
     * PolicyUploadImage : sss
     * LeadID : 0
     * VehiclePhotoUploadImage :
     * PolicyExpiryDate : 2016-1-12
     * CustomerEmail : a@b.com
     * CampaignName :
     * CustomerName : String content
     * PushLeadID : 52
     */

    private int EmployeeID;
    private String CustomerMobile;
    private int ProductID;
    private String DrivingLicenceUploadImage;
    private String VehicleRegistrationNumber;
    private String RCUploadImage;
    private String PolicyUploadImage;
    private int LeadID;
    private String VehiclePhotoUploadImage;
    private String PolicyExpiryDate;
    private String CustomerEmail;
    private String CampaignName;
    private String CustomerName;
    private int PushLeadID;

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getDrivingLicenceUploadImage() {
        return DrivingLicenceUploadImage;
    }

    public void setDrivingLicenceUploadImage(String DrivingLicenceUploadImage) {
        this.DrivingLicenceUploadImage = DrivingLicenceUploadImage;
    }

    public String getVehicleRegistrationNumber() {
        return VehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String VehicleRegistrationNumber) {
        this.VehicleRegistrationNumber = VehicleRegistrationNumber;
    }

    public String getRCUploadImage() {
        return RCUploadImage;
    }

    public void setRCUploadImage(String RCUploadImage) {
        this.RCUploadImage = RCUploadImage;
    }

    public String getPolicyUploadImage() {
        return PolicyUploadImage;
    }

    public void setPolicyUploadImage(String PolicyUploadImage) {
        this.PolicyUploadImage = PolicyUploadImage;
    }

    public int getLeadID() {
        return LeadID;
    }

    public void setLeadID(int LeadID) {
        this.LeadID = LeadID;
    }

    public String getVehiclePhotoUploadImage() {
        return VehiclePhotoUploadImage;
    }

    public void setVehiclePhotoUploadImage(String VehiclePhotoUploadImage) {
        this.VehiclePhotoUploadImage = VehiclePhotoUploadImage;
    }

    public String getPolicyExpiryDate() {
        return PolicyExpiryDate;
    }

    public void setPolicyExpiryDate(String PolicyExpiryDate) {
        this.PolicyExpiryDate = PolicyExpiryDate;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String CustomerEmail) {
        this.CustomerEmail = CustomerEmail;
    }

    public String getCampaignName() {
        return CampaignName;
    }

    public void setCampaignName(String CampaignName) {
        this.CampaignName = CampaignName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public int getPushLeadID() {
        return PushLeadID;
    }

    public void setPushLeadID(int PushLeadID) {
        this.PushLeadID = PushLeadID;
    }
}