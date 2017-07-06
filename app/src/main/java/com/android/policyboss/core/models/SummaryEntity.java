package com.android.policyboss.core.models;

public class SummaryEntity {
    /**
     * _id : 595c87480b49c83c70da2099
     * Request_Id : 1057
     * Request_Unique_Id : SRN-MQEQEEY5-GXDT-SFTT-KWTT-ACOPJBG3FCQZ
     * Client_Id : 4
     * Request_Core : {"product_id":10,"vehicle_id":50783,"rto_id":100,"vehicle_insurance_type":"renew","vehicle_manf_date":"2015-07-05","vehicle_registration_date":"2015-07-20","policy_expiry_date":"2017-07-19","vehicle_registration_type":"individual","vehicle_ncb_current":"20","is_claim_exists":"no","birth_date":"1981-12-07","method_type":"Premium","execution_async":"yes","registration_no":"MH-01-PA-1234","electrical_accessory":"","non_electrical_accessory":"","voluntary_deductible":"","is_llpd":"no","is_external_bifuel":"no","first_name":"JP4IFQNU0X","last_name":"7VHV1TO8KC","middle_name":"GY44IHF9QI","external_bifuel_value":"0","pa_owner_driver_si":"","pa_named_passenger_si":"","pa_unnamed_passenger_si":"","pa_paid_driver_si":"","secret_key":"SECRET-ODARQ6JP-9V2Q-7BIM-0NNM-DNRTXRWMRTAL","client_key":"CLIENT-GLF2SRA5-CFIF-4X2T-HC1Z-CXV4ZWQTFQ3T","registration_no_1":"MH","registration_no_2":"01","registration_no_3":"PA","registration_no_4":"1234"}
     * Request_Product : {"vehicle_insurance_type":"renew","vehicle_registration_type":"individual","vehicle_registration_date":"2015-07-20","vehicle_id":50783,"rto_id":100,"prev_insurer_id":"","is_claim_exists":"no","vehicle_ncb_current":"20","vehicle_ncb_next":"25","vehicle_age_year":2,"vehicle_age_month":24,"vehicle_manf_year":"2015","vehicle_expected_idv":"","registration_no":"MH-01-PA-1234","registration_no_1":"MH","registration_no_2":"01","registration_no_3":"PA","registration_no_4":"1234","policy_expiry_date":"2017-07-19","policy_start_date":"2017-07-20","pre_policy_start_date":"2016-07-20","policy_end_date":"2018-07-19","vehicle_manf_date":"2015-07-05","is_financed":"","is_external_bifuel":"no","external_bifuel_type":"","external_bifuel_value":"0","electrical_accessory":"","non_electrical_accessory":"","voluntary_deductible":"","is_antitheft_fit":"","is_aai_member":"","is_llpd":"no","pa_owner_driver_si":"","pa_named_passenger_si":"","pa_unnamed_passenger_si":"","pa_paid_driver_si":"","addon_zero_dep_cover":"","addon_road_assist_cover":"","addon_ncb_protection_cover":"","addon_engine_protector_cover":"","addon_invoice_price_cover":"","addon_key_lock_cover":"","addon_consumable_cover":"","addon_passenger_assistance_cover":"","addon_flag":"","addon_package_name":"ENHANCEMENTCOVER"}
     * Created_On : 2017-07-05T06:29:28.638Z
     * Status : pending
     * Total : 13
     * Pending : 1
     * Complete : 12
     * Success : 10
     * Fail : 2
     * Total_Execution_Time : 4.12
     * vehicle_min_idv : 29512
     * vehicle_max_idv : 49555
     * Actual_Time : 21.719999999999995
     * Common_Addon : {"addon_zero_dep_cover":{"min":234,"max":239}}
     */

    private String _id;
    private int Request_Id;
    private String Request_Unique_Id;
    private int Client_Id;
    private RequestCoreEntity Request_Core;
    private RequestProductEntity Request_Product;
    private String Created_On;
    private int Total;
    private int Pending;
    private int Complete;
    private int Success;
    private int Fail;
    private double Total_Execution_Time;
    private int vehicle_min_idv;
    private int vehicle_max_idv;
    private double Actual_Time;
    private String Status;
    private CommonAddonEntity Common_Addon;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getRequest_Id() {
        return Request_Id;
    }

    public void setRequest_Id(int Request_Id) {
        this.Request_Id = Request_Id;
    }

    public String getRequest_Unique_Id() {
        return Request_Unique_Id;
    }

    public void setRequest_Unique_Id(String Request_Unique_Id) {
        this.Request_Unique_Id = Request_Unique_Id;
    }

    public int getClient_Id() {
        return Client_Id;
    }

    public void setClient_Id(int Client_Id) {
        this.Client_Id = Client_Id;
    }

    public RequestCoreEntity getRequest_Core() {
        return Request_Core;
    }

    public void setRequest_Core(RequestCoreEntity Request_Core) {
        this.Request_Core = Request_Core;
    }

    public RequestProductEntity getRequest_Product() {
        return Request_Product;
    }

    public void setRequest_Product(RequestProductEntity Request_Product) {
        this.Request_Product = Request_Product;
    }

    public String getCreated_On() {
        return Created_On;
    }

    public void setCreated_On(String Created_On) {
        this.Created_On = Created_On;
    }


    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getPending() {
        return Pending;
    }

    public void setPending(int Pending) {
        this.Pending = Pending;
    }

    public int getComplete() {
        return Complete;
    }

    public void setComplete(int Complete) {
        this.Complete = Complete;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public int getFail() {
        return Fail;
    }

    public void setFail(int Fail) {
        this.Fail = Fail;
    }

    public double getTotal_Execution_Time() {
        return Total_Execution_Time;
    }

    public void setTotal_Execution_Time(double Total_Execution_Time) {
        this.Total_Execution_Time = Total_Execution_Time;
    }

    public int getVehicle_min_idv() {
        return vehicle_min_idv;
    }

    public void setVehicle_min_idv(int vehicle_min_idv) {
        this.vehicle_min_idv = vehicle_min_idv;
    }

    public int getVehicle_max_idv() {
        return vehicle_max_idv;
    }

    public void setVehicle_max_idv(int vehicle_max_idv) {
        this.vehicle_max_idv = vehicle_max_idv;
    }

    public double getActual_Time() {
        return Actual_Time;
    }

    public void setActual_Time(double Actual_Time) {
        this.Actual_Time = Actual_Time;
    }

    public CommonAddonEntity getCommon_Addon() {
        return Common_Addon;
    }

    public void setCommon_Addon(CommonAddonEntity Common_Addon) {
        this.Common_Addon = Common_Addon;
    }


}
