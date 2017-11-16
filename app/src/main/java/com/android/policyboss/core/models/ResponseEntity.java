package com.android.policyboss.core.models;

import com.google.gson.annotations.SerializedName;

public class ResponseEntity {
        /**
         * Service_Log_Id : 100404
         * Service_Log_Unique_Id : ARN-TB5DBPM1-2SO8-OC26-HCCN-PPWBFPCARHLC
         * Insurer_Transaction_Identifier :
         * Error_Code :
         * Created_On : 2017-11-14T09:17:15.368Z
         * Product_Id : 1
         * Insurer_Id : 4
         * Status : complete
         * Plan_Id : 5
         * Plan_Name : RoadSide
         * LM_Custom_Request : {"dbmaster_insurer_vehicle_exshowroom":409341,"vehicle_expected_idv":349987,"vehicle_max_idv":427761,"vehicle_min_idv":349987,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":388874}
         * Premium_Breakup : {"own_damage":{"od_basic":11490.07,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":2298.01,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":9192.06},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":500,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0},"net_premium":12155.06,"service_tax":2187.91,"final_premium":14342.97}
         * Insurer : {"_id":"59b7d7ad63237647de1f2a83","Insurer_Code":"Future Generali","Insurer_ID":4,"Insurer_Logo_Name":"Future_Generali_General.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"Future Generali India Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":" 6th Floor, Tower - 3, Indiabulls Finance Center, Senapati Bapat Marg, Elphinstone Road, Mumbai","PreviousInsurer_Pincode":400013}
         * Call_Execution_Time : 5.63
         */

        private String Service_Log_Id;
        private String Service_Log_Unique_Id;
        private String Insurer_Transaction_Identifier;
        private String Error_Code;
        private String Created_On;
        private String Product_Id;
        private String Insurer_Id;
        @SerializedName("Status")
        private String StatusX;
        private String Plan_Id;
        private String Plan_Name;
        private LMCustomRequestEntity LM_Custom_Request;
        private PremiumBreakupEntity Premium_Breakup;
        private InsurerEntity Insurer;
        private String Call_Execution_Time;

        public String getService_Log_Id() {
            return Service_Log_Id;
        }

        public void setService_Log_Id(String Service_Log_Id) {
            this.Service_Log_Id = Service_Log_Id;
        }

        public String getService_Log_Unique_Id() {
            return Service_Log_Unique_Id;
        }

        public void setService_Log_Unique_Id(String Service_Log_Unique_Id) {
            this.Service_Log_Unique_Id = Service_Log_Unique_Id;
        }

        public String getInsurer_Transaction_Identifier() {
            return Insurer_Transaction_Identifier;
        }

        public void setInsurer_Transaction_Identifier(String Insurer_Transaction_Identifier) {
            this.Insurer_Transaction_Identifier = Insurer_Transaction_Identifier;
        }

        public String getError_Code() {
            return Error_Code;
        }

        public void setError_Code(String Error_Code) {
            this.Error_Code = Error_Code;
        }

        public String getCreated_On() {
            return Created_On;
        }

        public void setCreated_On(String Created_On) {
            this.Created_On = Created_On;
        }

        public String getProduct_Id() {
            return Product_Id;
        }

        public void setProduct_Id(String Product_Id) {
            this.Product_Id = Product_Id;
        }

        public String getInsurer_Id() {
            return Insurer_Id;
        }

        public void setInsurer_Id(String Insurer_Id) {
            this.Insurer_Id = Insurer_Id;
        }

        public String getStatusX() {
            return StatusX;
        }

        public void setStatusX(String StatusX) {
            this.StatusX = StatusX;
        }

        public String getPlan_Id() {
            return Plan_Id;
        }

        public void setPlan_Id(String Plan_Id) {
            this.Plan_Id = Plan_Id;
        }

        public String getPlan_Name() {
            return Plan_Name;
        }

        public void setPlan_Name(String Plan_Name) {
            this.Plan_Name = Plan_Name;
        }

        public LMCustomRequestEntity getLM_Custom_Request() {
            return LM_Custom_Request;
        }

        public void setLM_Custom_Request(LMCustomRequestEntity LM_Custom_Request) {
            this.LM_Custom_Request = LM_Custom_Request;
        }

        public PremiumBreakupEntity getPremium_Breakup() {
            return Premium_Breakup;
        }

        public void setPremium_Breakup(PremiumBreakupEntity Premium_Breakup) {
            this.Premium_Breakup = Premium_Breakup;
        }

        public InsurerEntity getInsurer() {
            return Insurer;
        }

        public void setInsurer(InsurerEntity Insurer) {
            this.Insurer = Insurer;
        }

        public String getCall_Execution_Time() {
            return Call_Execution_Time;
        }

        public void setCall_Execution_Time(String Call_Execution_Time) {
            this.Call_Execution_Time = Call_Execution_Time;
        }

    }