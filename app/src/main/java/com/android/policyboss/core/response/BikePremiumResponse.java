package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.AddonAmbulanceChargeCoverEntity;
import com.android.policyboss.core.models.AddonConsumableCoverEntity;
import com.android.policyboss.core.models.AddonEngineProtectorCoverEntity;
import com.android.policyboss.core.models.AddonHospitalCashCoverEntity;
import com.android.policyboss.core.models.AddonInvoicePriceCoverEntity;
import com.android.policyboss.core.models.AddonKeyLockCoverEntity;
import com.android.policyboss.core.models.AddonMedicalExpenseCoverEntity;
import com.android.policyboss.core.models.AddonNcbProtectionCoverEntity;
import com.android.policyboss.core.models.AddonRoadAssistCoverEntity;
import com.android.policyboss.core.models.AddonWindshieldCoverEntity;
import com.android.policyboss.core.models.AddonZeroDepCoverEntity;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.models.SummaryEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nilesh Birhade on 03-07-2017.
 */

public class BikePremiumResponse extends APIResponse {


    /**
     * Summary : {"_id":"5a0ab49b1f4d2b19e098bf79","Request_Id":8946,"Request_Unique_Id":"SRN-XP0NTBTF-VH4C-33V4-7JVZ-Z9TCRILNPXFF","Client_Id":4,"PB_CRN":103419,"Request_Core":{"product_id":1,"vehicle_id":681,"rto_id":579,"vehicle_insurance_type":"renew","vehicle_manf_date":"2016-11-01","vehicle_registration_date":"2016-11-15","policy_expiry_date":"2017-11-14","prev_insurer_id":2,"vehicle_registration_type":"individual","vehicle_ncb_current":"0","is_claim_exists":"no","method_type":"Premium","execution_async":"no","electrical_accessory":"0","non_electrical_accessory":"0","registration_no":"MH-01-AA-1234","is_llpd":"no","is_antitheft_fit":"no","voluntary_deductible":0,"is_external_bifuel":"no","is_aai_member":"no","external_bifuel_type":null,"external_bifuel_value":"0","pa_owner_driver_si":100000,"pa_named_passenger_si":"0","pa_unnamed_passenger_si":"0","pa_paid_driver_si":"0","vehicle_expected_idv":0,"first_name":"Ajit","middle_name":"U3IBBVSZDV","last_name":"Kumar","mobile":"7377319978","email":"ajit.kumar@rupeeboss.com","crn":0,"ss_id":0,"secret_key":"SECRET-ODARQ6JP-9V2Q-7BIM-0NNM-DNRTXRWMRTAL","client_key":"CLIENT-GLF2SRA5-CFIF-4X2T-HC1Z-CXV4ZWQTFQ3T","birth_date":"1992-01-01","registration_no_1":"MH","registration_no_2":"01","registration_no_3":"AA","registration_no_4":"1234","posp_posp_id":0,"posp_fba_id":0,"posp_sm_posp_id":0,"posp_sm_posp_name":0,"posp_first_name":0,"posp_middle_name":0,"posp_last_name":0,"posp_email_id":0,"posp_agent_city":0,"posp_mobile_no":0,"posp_pan_no":0,"posp_aadhar":0,"posp_sources":0,"posp_ss_id":0,"posp_erp_id":0,"posp_gender":0,"posp_posp_category":0,"posp_reporting_agent_uid":0,"posp_reporting_agent_name":0,"posp_category":"PolicyBoss","erp_source":"FRESH-MTR"},"Request_Product":{"vehicle_insurance_type":"renew","vehicle_registration_type":"individual","vehicle_registration_date":"2016-11-15","vehicle_id":681,"rto_id":579,"prev_insurer_id":2,"is_claim_exists":"no","vehicle_ncb_current":"0","vehicle_manf_year":"2016","vehicle_ncb_next":"20","vehicle_age_year":1,"vehicle_age_month":12,"vehicle_expected_idv":0,"registration_no":"MH-01-AA-1234","registration_no_1":"MH","registration_no_2":"01","registration_no_3":"AA","registration_no_4":"1234","policy_expiry_date":"2017-11-14","policy_start_date":"2017-11-15","pre_policy_start_date":"2016-11-15","policy_end_date":"2018-11-14","vehicle_manf_date":"2016-11-01","is_financed":"","financial_institute_name":"","financial_institute_code":"","financial_institute_city":"","financial_agreement_type":"","previous_policy_number":"","is_external_bifuel":"no","external_bifuel_type":"","external_bifuel_value":"0","electrical_accessory":"0","non_electrical_accessory":"0","engine_number":"","chassis_number":"","vehicle_color":"","voluntary_deductible":"","is_antitheft_fit":"no","is_aai_member":"no","is_llpd":"no","pa_owner_driver_si":100000,"pa_named_passenger_si":"","pa_unnamed_passenger_si":"","pa_paid_driver_si":"","addon_zero_dep_cover":"","addon_road_assist_cover":"","addon_ncb_protection_cover":"","addon_engine_protector_cover":"","addon_invoice_price_cover":"","addon_key_lock_cover":"","addon_consumable_cover":"","addon_passenger_assistance_cover":"","addon_flag":"","addon_package_name":"Titanium","addon_daily_allowance_cover":"yes","addon_windshield_cover":"yes","addon_tyre_coverage_cover":"yes","addon_personal_belonging_loss_cover":"yes","addon_inconvenience_allowance_cover":"yes","addon_medical_expense_cover":"yes","addon_hospital_cash_cover":"yes","addon_ambulance_charge_cover":"yes","addon_rodent_bite_cover":"yes","addon_losstime_protection_cover":"yes","addon_hydrostatic_lock_cover":"yes"},"Created_On":"2017-11-14T09:17:15.052Z","Status":"complete","Total":23,"Pending":0,"Complete":23,"Success":15,"Fail":8,"Total_Execution_Time":88.37,"vehicle_min_idv":319432,"vehicle_max_idv":550820,"Actual_Time":216.67000000000002,"Common_Addon":{"addon_road_assist_cover":{"min":350,"max":500},"addon_zero_dep_cover":{"min":1336,"max":3380},"addon_engine_protector_cover":{"min":569,"max":599},"addon_invoice_price_cover":{"min":898,"max":949},"addon_key_lock_cover":{"min":108,"max":683},"addon_consumable_cover":{"min":435,"max":1597},"addon_medical_expense_cover":{"min":375,"max":375},"addon_hospital_cash_cover":{"min":500,"max":500},"addon_ambulance_charge_cover":{"min":100,"max":100},"addon_ncb_protection_cover":{"min":639,"max":1352},"addon_windshield_cover":{"min":270,"max":270}}}
     * Response : [{"Service_Log_Id":100404,"Service_Log_Unique_Id":"ARN-TB5DBPM1-2SO8-OC26-HCCN-PPWBFPCARHLC","Insurer_Transaction_Identifier":"","Error_Code":"","Created_On":"2017-11-14T09:17:15.368Z","Product_Id":1,"Insurer_Id":4,"Status":"complete","Plan_Id":5,"Plan_Name":"RoadSide","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":409341,"vehicle_expected_idv":349987,"vehicle_max_idv":427761,"vehicle_min_idv":349987,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":388874},"Premium_Breakup":{"own_damage":{"od_basic":11490.07,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":2298.01,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":9192.06},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":500,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0},"net_premium":12155.06,"service_tax":2187.91,"final_premium":14342.97},"Insurer":{"_id":"59b7d7ad63237647de1f2a83","Insurer_Code":"Future Generali","Insurer_ID":4,"Insurer_Logo_Name":"Future_Generali_General.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"Future Generali India Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":" 6th Floor, Tower - 3, Indiabulls Finance Center, Senapati Bapat Marg, Elphinstone Road, Mumbai","PreviousInsurer_Pincode":400013},"Call_Execution_Time":5.63},{"Service_Log_Id":100402,"Service_Log_Unique_Id":"ARN-4XI7OUNW-LUQR-LAZG-TBQC-LW6V0KVKTG1J","Insurer_Transaction_Identifier":"VDJC139402-QRN201711140000887","Error_Code":"","Created_On":"2017-11-14T09:17:15.355Z","Product_Id":1,"Insurer_Id":2,"Status":"complete","Plan_Id":3,"Plan_Name":"Basic","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":496252,"vehicle_expected_idv":379633,"vehicle_max_idv":463995,"vehicle_min_idv":379633,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":421814},"Premium_Breakup":{"own_damage":{"od_basic":12463.35139,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":2492.670278,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":5484.111111111111,"od_final_premium":4486.570000888889},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":50,"tp_cng_lpg":0,"tp_final_premium":3013},"addon":{"addon_zero_dep_cover":1708.3485000000003,"addon_road_assist_cover":375,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":569.4495000000001,"addon_invoice_price_cover":949.0825,"addon_key_lock_cover":683.3394,"addon_consumable_cover":569.4495000000001,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":375,"addon_hospital_cash_cover":500,"addon_ambulance_charge_cover":100,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":5829.6694},"net_premium":13329.239400888888,"service_tax":2399.26309216,"final_premium":15728.502493048887},"Insurer":{"_id":"59b7d7ad63237647de1f2a84","Insurer_Code":"Bharti Axa","Insurer_ID":2,"Insurer_Logo_Name":"Bharti_Axa_General.png","Insurer_Logo_Name_Mobile":"m-bharti.png","Insurer_Name":"Bharti Axa General Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"1st floor,Ferns icon,Survey No.28,Doddankundi,Bangalore","PreviousInsurer_Pincode":560037},"Call_Execution_Time":2.23},{"Service_Log_Id":100403,"Service_Log_Unique_Id":"ARN-U6TOR2RQ-VU6Y-CSON-WMJ0-VCHMT2MJWYRW","Insurer_Transaction_Identifier":"","Error_Code":"","Created_On":"2017-11-14T09:17:15.362Z","Product_Id":1,"Insurer_Id":4,"Status":"complete","Plan_Id":4,"Plan_Name":"Basic","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":409341,"vehicle_expected_idv":349987,"vehicle_max_idv":427761,"vehicle_min_idv":349987,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":388874},"Premium_Breakup":{"own_damage":{"od_basic":11490.07,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":2298.01,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":9192.06},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":0,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0},"net_premium":12155.06,"service_tax":2187.91,"final_premium":14342.97},"Insurer":{"_id":"59b7d7ad63237647de1f2a83","Insurer_Code":"Future Generali","Insurer_ID":4,"Insurer_Logo_Name":"Future_Generali_General.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"Future Generali India Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":" 6th Floor, Tower - 3, Indiabulls Finance Center, Senapati Bapat Marg, Elphinstone Road, Mumbai","PreviousInsurer_Pincode":400013},"Call_Execution_Time":4.22},{"Service_Log_Id":100405,"Service_Log_Unique_Id":"ARN-M9DFX8TX-0IE6-6SJP-UK4D-O16QEBCEXDMV","Insurer_Transaction_Identifier":"","Error_Code":"","Created_On":"2017-11-14T09:17:15.374Z","Product_Id":1,"Insurer_Id":4,"Status":"complete","Plan_Id":6,"Plan_Name":"Combine","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":409341,"vehicle_expected_idv":349987,"vehicle_max_idv":427761,"vehicle_min_idv":349987,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":388874},"Premium_Breakup":{"own_damage":{"od_basic":11490.07,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":2298.01,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":9192.06},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":1574.94,"addon_road_assist_cover":0,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0},"net_premium":12155.06,"service_tax":2187.91,"final_premium":14342.97},"Insurer":{"_id":"59b7d7ad63237647de1f2a83","Insurer_Code":"Future Generali","Insurer_ID":4,"Insurer_Logo_Name":"Future_Generali_General.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"Future Generali India Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":" 6th Floor, Tower - 3, Indiabulls Finance Center, Senapati Bapat Marg, Elphinstone Road, Mumbai","PreviousInsurer_Pincode":400013},"Call_Execution_Time":5.89},{"Service_Log_Id":100408,"Service_Log_Unique_Id":"ARN-UGA8O3AS-YKCL-JNY2-SFP8-ZRLK2XAXPX6E","Insurer_Transaction_Identifier":"550788f0-1eab-4d78-a625-fc3206751772","Error_Code":"","Created_On":"2017-11-14T09:17:15.503Z","Product_Id":1,"Insurer_Id":5,"Status":"complete","Plan_Id":14,"Plan_Name":"Basic","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":469753,"vehicle_expected_idv":319432,"vehicle_max_idv":432173,"vehicle_min_idv":319432,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":375802},"Premium_Breakup":{"own_damage":{"od_basic":6817,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1363,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":5454},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":0,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0},"net_premium":8417,"service_tax":1515,"final_premium":9932},"Insurer":{"_id":"59b7d7ad63237647de1f2a85","Insurer_Code":"HDFC ERGO","Insurer_ID":5,"Insurer_Logo_Name":"hdfc.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"HDFC ERGO General Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"","PreviousInsurer_Pincode":""},"Call_Execution_Time":0.54},{"Service_Log_Id":100409,"Service_Log_Unique_Id":"ARN-NWKBLVNQ-FKB2-B3BM-FKMQ-BOYAVRSRCOQE","Insurer_Transaction_Identifier":"186b7ea0-c9bb-484a-b8b2-8c805f9b57c3","Error_Code":"","Created_On":"2017-11-14T09:17:15.509Z","Product_Id":1,"Insurer_Id":5,"Status":"complete","Plan_Id":15,"Plan_Name":"Silver","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":469753,"vehicle_expected_idv":319432,"vehicle_max_idv":432173,"vehicle_min_idv":319432,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":375802},"Premium_Breakup":{"own_damage":{"od_basic":6817,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1363,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":5454},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":1917,"addon_road_assist_cover":350,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":2267},"net_premium":10684,"service_tax":1923,"final_premium":12607},"Insurer":{"_id":"59b7d7ad63237647de1f2a85","Insurer_Code":"HDFC ERGO","Insurer_ID":5,"Insurer_Logo_Name":"hdfc.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"HDFC ERGO General Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"","PreviousInsurer_Pincode":""},"Call_Execution_Time":0.81},{"Service_Log_Id":100411,"Service_Log_Unique_Id":"ARN-5T6KXY7W-DQEP-QK3V-EE3X-CNEI0J3XT2RU","Insurer_Transaction_Identifier":"130c8fb5-4b94-46a1-92d0-e56e0eacc7bf","Error_Code":"","Created_On":"2017-11-14T09:17:15.524Z","Product_Id":1,"Insurer_Id":5,"Status":"complete","Plan_Id":18,"Plan_Name":"Titanium","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":469753,"vehicle_expected_idv":319432,"vehicle_max_idv":432173,"vehicle_min_idv":319432,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":375802},"Premium_Breakup":{"own_damage":{"od_basic":6817,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1363,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":5454},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":1917,"addon_road_assist_cover":350,"addon_ncb_protection_cover":639,"addon_engine_protector_cover":575,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":1597,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":5078},"net_premium":13495,"service_tax":2429,"final_premium":15924},"Insurer":{"_id":"59b7d7ad63237647de1f2a85","Insurer_Code":"HDFC ERGO","Insurer_ID":5,"Insurer_Logo_Name":"hdfc.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"HDFC ERGO General Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"","PreviousInsurer_Pincode":""},"Call_Execution_Time":0.58},{"Service_Log_Id":100410,"Service_Log_Unique_Id":"ARN-YZ5XCCJH-FTYR-2CP6-AWEB-LJZQNYCXSX8O","Insurer_Transaction_Identifier":"8a8ab371-071a-464d-a555-a325510382e6","Error_Code":"","Created_On":"2017-11-14T09:17:15.514Z","Product_Id":1,"Insurer_Id":5,"Status":"complete","Plan_Id":16,"Plan_Name":"Gold","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":469753,"vehicle_expected_idv":319432,"vehicle_max_idv":432173,"vehicle_min_idv":319432,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":375802},"Premium_Breakup":{"own_damage":{"od_basic":6817,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1363,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":5454},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":350,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":350},"net_premium":8767,"service_tax":1578,"final_premium":10345},"Insurer":{"_id":"59b7d7ad63237647de1f2a85","Insurer_Code":"HDFC ERGO","Insurer_ID":5,"Insurer_Logo_Name":"hdfc.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"HDFC ERGO General Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"","PreviousInsurer_Pincode":""},"Call_Execution_Time":0.57},{"Service_Log_Id":100412,"Service_Log_Unique_Id":"ARN-LTTN3DYP-E04U-VXQ3-5MK9-16AGX3QSFZBI","Insurer_Transaction_Identifier":"715178a1-9276-4b63-b8d3-6bc6d964b259","Error_Code":"","Created_On":"2017-11-14T09:17:15.519Z","Product_Id":1,"Insurer_Id":5,"Status":"complete","Plan_Id":17,"Plan_Name":"Platinum","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":469753,"vehicle_expected_idv":319432,"vehicle_max_idv":432173,"vehicle_min_idv":319432,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":375802},"Premium_Breakup":{"own_damage":{"od_basic":6817,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1363,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":5454},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":1917,"addon_road_assist_cover":350,"addon_ncb_protection_cover":639,"addon_engine_protector_cover":575,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":3481},"net_premium":11898,"service_tax":2142,"final_premium":14040},"Insurer":{"_id":"59b7d7ad63237647de1f2a85","Insurer_Code":"HDFC ERGO","Insurer_ID":5,"Insurer_Logo_Name":"hdfc.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"HDFC ERGO General Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"","PreviousInsurer_Pincode":""},"Call_Execution_Time":0.66},{"Service_Log_Id":100413,"Service_Log_Unique_Id":"ARN-KKOJKJME-R3UZ-Y5U7-HRKL-M5RRJG6NHCO4","Insurer_Transaction_Identifier":"E2311M1117305200","Error_Code":"","Created_On":"2017-11-14T09:17:15.547Z","Product_Id":1,"Insurer_Id":9,"Status":"complete","Plan_Id":8,"Plan_Name":"Basic","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":0,"vehicle_expected_idv":323484,"vehicle_max_idv":395369,"vehicle_min_idv":323484,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":359427},"Premium_Breakup":{"own_damage":{"od_basic":10619.98,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1380.6,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":3716.99,"od_final_premium":5522.389999999999},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":0,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0},"net_premium":8485,"service_tax":1527.3,"final_premium":10012},"Insurer":{"_id":"59b7d7ad63237647de1f2a8a","Insurer_Code":"Reliance General","Insurer_ID":9,"Insurer_Logo_Name":"reliance.png","Insurer_Logo_Name_Mobile":"m-rel.png","Insurer_Name":"Reliance General Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"19, Reliance Centre, Walchand Hirachand Marg, Ballard Estate, Mumbai","PreviousInsurer_Pincode":400001},"Call_Execution_Time":1.46},{"Service_Log_Id":100414,"Service_Log_Unique_Id":"ARN-IFIGVMVQ-7YNT-W03D-RHKW-AH7L9LXJSV4Y","Insurer_Transaction_Identifier":"E2311M1117305199","Error_Code":"","Created_On":"2017-11-14T09:17:15.554Z","Product_Id":1,"Insurer_Id":9,"Status":"complete","Plan_Id":10,"Plan_Name":"All-Addon","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":0,"vehicle_expected_idv":323484,"vehicle_max_idv":395369,"vehicle_min_idv":323484,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":359427},"Premium_Breakup":{"own_damage":{"od_basic":10619.98,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1380.6,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":3716.99,"od_final_premium":5522.389999999999},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":2102.65,"addon_road_assist_cover":0,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":2102.65},"net_premium":10588,"service_tax":1905.84,"final_premium":12494},"Insurer":{"_id":"59b7d7ad63237647de1f2a8a","Insurer_Code":"Reliance General","Insurer_ID":9,"Insurer_Logo_Name":"reliance.png","Insurer_Logo_Name_Mobile":"m-rel.png","Insurer_Name":"Reliance General Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"19, Reliance Centre, Walchand Hirachand Marg, Ballard Estate, Mumbai","PreviousInsurer_Pincode":400001},"Call_Execution_Time":1.39},{"Service_Log_Id":100417,"Service_Log_Unique_Id":"ARN-K7XGDBWV-BRJX-XHWX-8NBH-IUFSEYHER3AH","Insurer_Transaction_Identifier":null,"Error_Code":"","Created_On":"2017-11-14T09:17:15.624Z","Product_Id":1,"Insurer_Id":30,"Status":"complete","Plan_Id":21,"Plan_Name":"Basic","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":0,"vehicle_expected_idv":352370,"vehicle_max_idv":430674,"vehicle_min_idv":352370,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":391522},"Premium_Breakup":{"own_damage":{"od_basic":5201,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1040,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":4161},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":0,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0},"net_premium":7124,"service_tax":1282,"final_premium":8407},"Insurer":{"_id":"59b7d7ad63237647de1f2a9d","Insurer_Code":"Kotak Mahindra","Insurer_ID":30,"Insurer_Logo_Name":"kotak.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"Kotak Mahindra Life Insurance Co. Ltd","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"2nd Floor, Plot # C-12, G-Block, BKC, Bandra (E), Mumbai","PreviousInsurer_Pincode":400051},"Call_Execution_Time":8.11},{"Service_Log_Id":100418,"Service_Log_Unique_Id":"ARN-QEYVQ8TH-72R9-DJRF-NUKE-UTRZZSX2D7PO","Insurer_Transaction_Identifier":null,"Error_Code":"","Created_On":"2017-11-14T09:17:15.630Z","Product_Id":1,"Insurer_Id":30,"Status":"complete","Plan_Id":22,"Plan_Name":"All-Addon","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":0,"vehicle_expected_idv":352370,"vehicle_max_idv":430674,"vehicle_min_idv":352370,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":391522},"Premium_Breakup":{"own_damage":{"od_basic":5201,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1040,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":4161},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":1336,"addon_road_assist_cover":500,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":599,"addon_invoice_price_cover":898,"addon_key_lock_cover":0,"addon_consumable_cover":435,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":3768},"net_premium":10894,"service_tax":1961,"final_premium":12856},"Insurer":{"_id":"59b7d7ad63237647de1f2a9d","Insurer_Code":"Kotak Mahindra","Insurer_ID":30,"Insurer_Logo_Name":"kotak.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"Kotak Mahindra Life Insurance Co. Ltd","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"2nd Floor, Plot # C-12, G-Block, BKC, Bandra (E), Mumbai","PreviousInsurer_Pincode":400051},"Call_Execution_Time":9.19},{"Service_Log_Id":100420,"Service_Log_Unique_Id":"ARN-NAAUCH6P-2IF0-IHBJ-WQRQ-APJKRCLGLRHK","Insurer_Transaction_Identifier":"QVBB0078094","Error_Code":"","Created_On":"2017-11-14T09:17:15.709Z","Product_Id":1,"Insurer_Id":10,"Status":"complete","Plan_Id":23,"Plan_Name":"Basic","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":527100,"vehicle_expected_idv":450671,"vehicle_max_idv":550820,"vehicle_min_idv":450671,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":500745},"Premium_Breakup":{"own_damage":{"od_basic":8138,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1627,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":6511},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":0,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0},"net_premium":9474,"service_tax":1705,"final_premium":11178},"Insurer":{"_id":"59b7d7ad63237647de1f2a8b","Insurer_Code":"Royal Sundaram","Insurer_ID":10,"Insurer_Logo_Name":"royal.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"Royal Sundaram Alliance Insurance Co. Ltd","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"No.21,Patullos Road,Chennai","PreviousInsurer_Pincode":600002},"Call_Execution_Time":87.68},{"Service_Log_Id":100421,"Service_Log_Unique_Id":"ARN-6KYXNUIB-VBLW-YFTS-PMKR-LRLIBOYZ4UOM","Insurer_Transaction_Identifier":"QVBB0078095","Error_Code":"","Created_On":"2017-11-14T09:17:15.715Z","Product_Id":1,"Insurer_Id":10,"Status":"complete","Plan_Id":24,"Plan_Name":"All-Addon","LM_Custom_Request":{"dbmaster_insurer_vehicle_exshowroom":527100,"vehicle_expected_idv":450671,"vehicle_max_idv":550820,"vehicle_min_idv":450671,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":500745},"Premium_Breakup":{"own_damage":{"od_basic":7958,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":1591,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":6367},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":3380,"addon_road_assist_cover":0,"addon_ncb_protection_cover":1352,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":108,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":270,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":5110},"net_premium":14440,"service_tax":2599,"final_premium":17038},"Insurer":{"_id":"59b7d7ad63237647de1f2a8b","Insurer_Code":"Royal Sundaram","Insurer_ID":10,"Insurer_Logo_Name":"royal.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"Royal Sundaram Alliance Insurance Co. Ltd","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"No.21,Patullos Road,Chennai","PreviousInsurer_Pincode":600002},"Call_Execution_Time":87.71}]
     */

    private SummaryEntity Summary;
    private List<ResponseEntity> Response;

    public SummaryEntity getSummary() {
        return Summary;
    }

    public void setSummary(SummaryEntity Summary) {
        this.Summary = Summary;
    }

    public List<ResponseEntity> getResponse() {
        return Response;
    }

    public void setResponse(List<ResponseEntity> Response) {
        this.Response = Response;
    }


}
