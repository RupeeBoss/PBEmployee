package com.android.policyboss.core.models;

import java.util.List;

public class ResponseEntity {
        /**
         * Insurer_Id : 12
         * Premium_Breakup : {"own_damage":{"od_basic":1127.16,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":211.34,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":281.79,"od_final_premium":634.03},"liability":{"tp_basic":2055,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2155},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":0,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_final_premium":0},"net_premium":null,"service_tax":null,"final_premium":null}
         * Addon_List : {"addon_zero_dep_cover":234}
         * Plan_List : [{"Plan_Id":10,"Plan_Name":"All-Addon","Service_Log_Id":9514,"Service_Log_Unique_Id":"ARN-DHADUL2V-P646-YQMD-DVAV-ZW60SO885OFU","Insurer_Transaction_Identifier":"","Plan_Addon_Breakup":{"addon_zero_dep_cover":234}},{"Plan_Id":9,"Plan_Name":"Basic","Service_Log_Id":9513,"Service_Log_Unique_Id":"ARN-UANRLGBW-3ICS-CSAU-0NWR-EPHGOCESHDBO","Insurer_Transaction_Identifier":"","Plan_Addon_Breakup":{}}]
         * Insurer : {"_id":"58be94835f761783caf9408d","Insurer_ID":12,"Insurer_Name":"The New India Assurance Co. Ltd.","IsActive":1,"CreatedOn":"2012-02-09","Insurer_Logo_Name":"new_india.png","IsInternal":null,"Insurer_Code":"New India Assurance","Insurer_Logo_Name_Mobile":null}
         */

        private int Insurer_Id;
        private PremiumBreakupEntity Premium_Breakup;
        private AddonListEntity Addon_List;
        private InsurerEntity Insurer;
        private List<PlanListEntity> Plan_List;

        public int getInsurer_Id() {
            return Insurer_Id;
        }

        public void setInsurer_Id(int Insurer_Id) {
            this.Insurer_Id = Insurer_Id;
        }

        public PremiumBreakupEntity getPremium_Breakup() {
            return Premium_Breakup;
        }

        public void setPremium_Breakup(PremiumBreakupEntity Premium_Breakup) {
            this.Premium_Breakup = Premium_Breakup;
        }

        public AddonListEntity getAddon_List() {
            return Addon_List;
        }

        public void setAddon_List(AddonListEntity Addon_List) {
            this.Addon_List = Addon_List;
        }

        public InsurerEntity getInsurer() {
            return Insurer;
        }

        public void setInsurer(InsurerEntity Insurer) {
            this.Insurer = Insurer;
        }

        public List<PlanListEntity> getPlan_List() {
            return Plan_List;
        }

        public void setPlan_List(List<PlanListEntity> Plan_List) {
            this.Plan_List = Plan_List;
        }





    }
