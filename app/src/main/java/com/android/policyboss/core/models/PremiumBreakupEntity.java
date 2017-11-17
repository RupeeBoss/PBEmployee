package com.android.policyboss.core.models;

import java.util.List;

public class PremiumBreakupEntity {
    /**
     * own_damage : {"od_basic":11490.07,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":2298.01,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":9192.06}
     * liability : {"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963}
     * addon : {"addon_zero_dep_cover":0,"addon_road_assist_cover":500,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0}
     * net_premium : 12155.06
     * service_tax : 2187.91
     * final_premium : 14342.97
     */

    private OwnDamageEntity own_damage;
    private LiabilityEntity liability;
    private AddonEntity addon;
    private String net_premium;
    private String service_tax;
    private String final_premium;

    private List<AppliedAddonsPremiumBreakup> listAppliedAddons;

    public List<AppliedAddonsPremiumBreakup> getListAppliedAddons() {
        return listAppliedAddons;
    }

    public void setListAppliedAddons(List<AppliedAddonsPremiumBreakup> listAppliedAddons) {
        this.listAppliedAddons = listAppliedAddons;
    }

    public OwnDamageEntity getOwn_damage() {
        return own_damage;
    }

    public void setOwn_damage(OwnDamageEntity own_damage) {
        this.own_damage = own_damage;
    }

    public LiabilityEntity getLiability() {
        return liability;
    }

    public void setLiability(LiabilityEntity liability) {
        this.liability = liability;
    }

    public AddonEntity getAddon() {
        return addon;
    }

    public void setAddon(AddonEntity addon) {
        this.addon = addon;
    }

    public String getNet_premium() {
        return net_premium;
    }

    public void setNet_premium(String net_premium) {
        this.net_premium = net_premium;
    }

    public String getService_tax() {
        return service_tax;
    }

    public void setService_tax(String service_tax) {
        this.service_tax = service_tax;
    }

    public String getFinal_premium() {
        return final_premium;
    }

    public void setFinal_premium(String final_premium) {
        this.final_premium = final_premium;
    }

}
