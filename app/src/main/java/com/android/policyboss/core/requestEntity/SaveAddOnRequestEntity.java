package com.android.policyboss.core.requestEntity;

import com.android.policyboss.utility.Constants;

/**
 * Created by Rajeev Ranjan on 17/11/2017.
 */

public class SaveAddOnRequestEntity {

    public SaveAddOnRequestEntity() {
        this.addon_road_assist_cover = "no";
        this.addon_engine_protector_cover = "no";
        this.addon_key_lock_cover = "no";
        this.addon_consumable_cover = "no";
        this.addon_zero_dep_cover = "no";
        this.addon_medical_expense_cover = "no";
        this.addon_hospital_cash_cover = "no";
        this.addon_ambulance_charge_cover = "no";
        this.addon_ncb_protection_cover = "no";
        this.addon_windshield_cover = "no";
        this.data_type = "no";
        this.search_reference_number = "no";
        this.secret_key = Constants.SECRET_KEY;
        this.client_key = Constants.CLIENT_KEY;
    }

    /**
     * addon_road_assist_cover : yes
     * addon_engine_protector_cover : yes
     * addon_key_lock_cover : yes
     * addon_consumable_cover : yes
     * addon_zero_dep_cover : no
     * addon_medical_expense_cover : no
     * addon_hospital_cash_cover : no
     * addon_ambulance_charge_cover : no
     * addon_ncb_protection_cover : no
     * addon_windshield_cover : no
     * data_type : addon
     * search_reference_number : SRN-8MRLQIGW-BDKV-OB7A-S4BG-E2WMBXLRF6IO
     * secret_key : SECRET-HZ07QRWY-JIBT-XRMQ-ZP95-J0RWP3DYRACW
     * client_key : CLIENT-CNTP6NYE-CU9N-DUZW-CSPI-SH1IS4DOVHB9
     */


    private String addon_road_assist_cover;
    private String addon_engine_protector_cover;
    private String addon_key_lock_cover;
    private String addon_consumable_cover;
    private String addon_zero_dep_cover;
    private String addon_medical_expense_cover;
    private String addon_hospital_cash_cover;
    private String addon_ambulance_charge_cover;
    private String addon_ncb_protection_cover;
    private String addon_windshield_cover;
    private String data_type;
    private String search_reference_number;
    private String secret_key;
    private String client_key;

    public String getAddon_road_assist_cover() {
        return addon_road_assist_cover;
    }

    public void setAddon_road_assist_cover(String addon_road_assist_cover) {
        this.addon_road_assist_cover = addon_road_assist_cover;
    }

    public String getAddon_engine_protector_cover() {
        return addon_engine_protector_cover;
    }

    public void setAddon_engine_protector_cover(String addon_engine_protector_cover) {
        this.addon_engine_protector_cover = addon_engine_protector_cover;
    }

    public String getAddon_key_lock_cover() {
        return addon_key_lock_cover;
    }

    public void setAddon_key_lock_cover(String addon_key_lock_cover) {
        this.addon_key_lock_cover = addon_key_lock_cover;
    }

    public String getAddon_consumable_cover() {
        return addon_consumable_cover;
    }

    public void setAddon_consumable_cover(String addon_consumable_cover) {
        this.addon_consumable_cover = addon_consumable_cover;
    }

    public String getAddon_zero_dep_cover() {
        return addon_zero_dep_cover;
    }

    public void setAddon_zero_dep_cover(String addon_zero_dep_cover) {
        this.addon_zero_dep_cover = addon_zero_dep_cover;
    }

    public String getAddon_medical_expense_cover() {
        return addon_medical_expense_cover;
    }

    public void setAddon_medical_expense_cover(String addon_medical_expense_cover) {
        this.addon_medical_expense_cover = addon_medical_expense_cover;
    }

    public String getAddon_hospital_cash_cover() {
        return addon_hospital_cash_cover;
    }

    public void setAddon_hospital_cash_cover(String addon_hospital_cash_cover) {
        this.addon_hospital_cash_cover = addon_hospital_cash_cover;
    }

    public String getAddon_ambulance_charge_cover() {
        return addon_ambulance_charge_cover;
    }

    public void setAddon_ambulance_charge_cover(String addon_ambulance_charge_cover) {
        this.addon_ambulance_charge_cover = addon_ambulance_charge_cover;
    }

    public String getAddon_ncb_protection_cover() {
        return addon_ncb_protection_cover;
    }

    public void setAddon_ncb_protection_cover(String addon_ncb_protection_cover) {
        this.addon_ncb_protection_cover = addon_ncb_protection_cover;
    }

    public String getAddon_windshield_cover() {
        return addon_windshield_cover;
    }

    public void setAddon_windshield_cover(String addon_windshield_cover) {
        this.addon_windshield_cover = addon_windshield_cover;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getSearch_reference_number() {
        return search_reference_number;
    }

    public void setSearch_reference_number(String search_reference_number) {
        this.search_reference_number = search_reference_number;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getClient_key() {
        return client_key;
    }

    public void setClient_key(String client_key) {
        this.client_key = client_key;
    }
}
