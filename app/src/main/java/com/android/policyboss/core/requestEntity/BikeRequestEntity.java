package com.android.policyboss.core.requestEntity;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.policyboss.utility.Constants;

/**
 * Created by Nilesh Birhade on 03-07-2017.
 */

public class BikeRequestEntity implements Parcelable {


    /**
     * product_id : 10
     * vehicle_id : 50783
     * rto_id : 100
     * vehicle_insurance_type : renew
     * vehicle_manf_date : 2015-07-05
     * vehicle_registration_date : 2015-07-20
     * policy_expiry_date : 2017-07-19
     * vehicle_registration_type : individual
     * vehicle_ncb_current : 20
     * is_claim_exists : no
     * birth_date : 1981-12-07
     * method_type : Premium
     * execution_async : yes
     * registration_no : MH-01-PA-1234
     * electrical_accessory :
     * non_electrical_accessory :
     * voluntary_deductible :
     * is_llpd : no
     * is_external_bifuel : no
     * first_name : JP4IFQNU0X
     * last_name : 7VHV1TO8KC
     * middle_name : GY44IHF9QI
     * external_bifuel_value :
     * pa_owner_driver_si :
     * pa_named_passenger_si :
     * pa_unnamed_passenger_si :
     * pa_paid_driver_si :
     * secret_key : SECRET-ODARQ6JP-9V2Q-7BIM-0NNM-DNRTXRWMRTAL
     * client_key : CLIENT-GLF2SRA5-CFIF-4X2T-HC1Z-CXV4ZWQTFQ3T
     */

    private int product_id;
    private int vehicle_id;
    private int rto_id;
    private String vehicle_insurance_type;
    private String vehicle_manf_date;
    private String vehicle_registration_date;
    private String policy_expiry_date;
    private String vehicle_registration_type;
    private String vehicle_ncb_current;
    private String is_claim_exists;
    private String birth_date;
    private String method_type;
    private String execution_async;
    private String registration_no;
    private String electrical_accessory;
    private String non_electrical_accessory;
    private String voluntary_deductible;
    private String is_llpd;
    private String is_external_bifuel;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String external_bifuel_value;
    private String pa_owner_driver_si;
    private String pa_named_passenger_si;
    private String pa_unnamed_passenger_si;
    private String pa_paid_driver_si;
    private String secret_key;
    private String client_key;
    private String prev_insurer_id;


    public BikeRequestEntity() {
        this.product_id = 10;
        this.vehicle_id = 0;
        this.rto_id = 0;
        this.vehicle_insurance_type = "";
        this.vehicle_manf_date = "";
        this.vehicle_registration_date = "";
        this.policy_expiry_date = "";
        this.vehicle_registration_type = "";
        this.vehicle_ncb_current = "";
        this.is_claim_exists = "";
        this.birth_date = "";
        this.method_type = "";
        this.execution_async = "yes";
        this.registration_no = "";
        this.electrical_accessory = "";
        this.non_electrical_accessory = "";
        this.voluntary_deductible = "";
        this.is_llpd = "";
        this.is_external_bifuel = "";
        this.first_name = "nilesh";
        this.last_name = "birhade";
        this.middle_name = "";
        this.external_bifuel_value = "";
        this.pa_owner_driver_si = "";
        this.pa_named_passenger_si = "";
        this.pa_unnamed_passenger_si = "";
        this.pa_paid_driver_si = "";
        this.secret_key = Constants.SECRET_KEY;
        this.client_key = Constants.CLIENT_KEY;
    }

    public String getPrev_insurer_id() {
        return prev_insurer_id;
    }

    public void setPrev_insurer_id(String prev_insurer_id) {
        this.prev_insurer_id = prev_insurer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getRto_id() {
        return rto_id;
    }

    public void setRto_id(int rto_id) {
        this.rto_id = rto_id;
    }

    public String getVehicle_insurance_type() {
        return vehicle_insurance_type;
    }

    public void setVehicle_insurance_type(String vehicle_insurance_type) {
        this.vehicle_insurance_type = vehicle_insurance_type;
    }

    public String getVehicle_manf_date() {
        return vehicle_manf_date;
    }

    public void setVehicle_manf_date(String vehicle_manf_date) {
        this.vehicle_manf_date = vehicle_manf_date;
    }

    public String getVehicle_registration_date() {
        return vehicle_registration_date;
    }

    public void setVehicle_registration_date(String vehicle_registration_date) {
        this.vehicle_registration_date = vehicle_registration_date;
    }

    public String getPolicy_expiry_date() {
        return policy_expiry_date;
    }

    public void setPolicy_expiry_date(String policy_expiry_date) {
        this.policy_expiry_date = policy_expiry_date;
    }

    public String getVehicle_registration_type() {
        return vehicle_registration_type;
    }

    public void setVehicle_registration_type(String vehicle_registration_type) {
        this.vehicle_registration_type = vehicle_registration_type;
    }

    public String getVehicle_ncb_current() {
        return vehicle_ncb_current;
    }

    public void setVehicle_ncb_current(String vehicle_ncb_current) {
        this.vehicle_ncb_current = vehicle_ncb_current;
    }

    public String getIs_claim_exists() {
        return is_claim_exists;
    }

    public void setIs_claim_exists(String is_claim_exists) {
        this.is_claim_exists = is_claim_exists;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getMethod_type() {
        return method_type;
    }

    public void setMethod_type(String method_type) {
        this.method_type = method_type;
    }

    public String getExecution_async() {
        return execution_async;
    }

    public void setExecution_async(String execution_async) {
        this.execution_async = execution_async;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public String getElectrical_accessory() {
        return electrical_accessory;
    }

    public void setElectrical_accessory(String electrical_accessory) {
        this.electrical_accessory = electrical_accessory;
    }

    public String getNon_electrical_accessory() {
        return non_electrical_accessory;
    }

    public void setNon_electrical_accessory(String non_electrical_accessory) {
        this.non_electrical_accessory = non_electrical_accessory;
    }

    public String getVoluntary_deductible() {
        return voluntary_deductible;
    }

    public void setVoluntary_deductible(String voluntary_deductible) {
        this.voluntary_deductible = voluntary_deductible;
    }

    public String getIs_llpd() {
        return is_llpd;
    }

    public void setIs_llpd(String is_llpd) {
        this.is_llpd = is_llpd;
    }

    public String getIs_external_bifuel() {
        return is_external_bifuel;
    }

    public void setIs_external_bifuel(String is_external_bifuel) {
        this.is_external_bifuel = is_external_bifuel;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getExternal_bifuel_value() {
        return external_bifuel_value;
    }

    public void setExternal_bifuel_value(String external_bifuel_value) {
        this.external_bifuel_value = external_bifuel_value;
    }

    public String getPa_owner_driver_si() {
        return pa_owner_driver_si;
    }

    public void setPa_owner_driver_si(String pa_owner_driver_si) {
        this.pa_owner_driver_si = pa_owner_driver_si;
    }

    public String getPa_named_passenger_si() {
        return pa_named_passenger_si;
    }

    public void setPa_named_passenger_si(String pa_named_passenger_si) {
        this.pa_named_passenger_si = pa_named_passenger_si;
    }

    public String getPa_unnamed_passenger_si() {
        return pa_unnamed_passenger_si;
    }

    public void setPa_unnamed_passenger_si(String pa_unnamed_passenger_si) {
        this.pa_unnamed_passenger_si = pa_unnamed_passenger_si;
    }

    public String getPa_paid_driver_si() {
        return pa_paid_driver_si;
    }

    public void setPa_paid_driver_si(String pa_paid_driver_si) {
        this.pa_paid_driver_si = pa_paid_driver_si;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = Constants.SECRET_KEY;
    }

    public String getClient_key() {
        return client_key;
    }

    public void setClient_key(String client_key) {
        this.client_key = Constants.CLIENT_KEY;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.product_id);
        dest.writeInt(this.vehicle_id);
        dest.writeInt(this.rto_id);
        dest.writeString(this.vehicle_insurance_type);
        dest.writeString(this.vehicle_manf_date);
        dest.writeString(this.vehicle_registration_date);
        dest.writeString(this.policy_expiry_date);
        dest.writeString(this.vehicle_registration_type);
        dest.writeString(this.vehicle_ncb_current);
        dest.writeString(this.is_claim_exists);
        dest.writeString(this.birth_date);
        dest.writeString(this.method_type);
        dest.writeString(this.execution_async);
        dest.writeString(this.registration_no);
        dest.writeString(this.electrical_accessory);
        dest.writeString(this.non_electrical_accessory);
        dest.writeString(this.voluntary_deductible);
        dest.writeString(this.is_llpd);
        dest.writeString(this.is_external_bifuel);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.middle_name);
        dest.writeString(this.external_bifuel_value);
        dest.writeString(this.pa_owner_driver_si);
        dest.writeString(this.pa_named_passenger_si);
        dest.writeString(this.pa_unnamed_passenger_si);
        dest.writeString(this.pa_paid_driver_si);
        dest.writeString(this.secret_key);
        dest.writeString(this.client_key);
    }

    protected BikeRequestEntity(Parcel in) {
        this.product_id = in.readInt();
        this.vehicle_id = in.readInt();
        this.rto_id = in.readInt();
        this.vehicle_insurance_type = in.readString();
        this.vehicle_manf_date = in.readString();
        this.vehicle_registration_date = in.readString();
        this.policy_expiry_date = in.readString();
        this.vehicle_registration_type = in.readString();
        this.vehicle_ncb_current = in.readString();
        this.is_claim_exists = in.readString();
        this.birth_date = in.readString();
        this.method_type = in.readString();
        this.execution_async = in.readString();
        this.registration_no = in.readString();
        this.electrical_accessory = in.readString();
        this.non_electrical_accessory = in.readString();
        this.voluntary_deductible = in.readString();
        this.is_llpd = in.readString();
        this.is_external_bifuel = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.middle_name = in.readString();
        this.external_bifuel_value = in.readString();
        this.pa_owner_driver_si = in.readString();
        this.pa_named_passenger_si = in.readString();
        this.pa_unnamed_passenger_si = in.readString();
        this.pa_paid_driver_si = in.readString();
        this.secret_key = in.readString();
        this.client_key = in.readString();
    }

    public static final Parcelable.Creator<BikeRequestEntity> CREATOR = new Parcelable.Creator<BikeRequestEntity>() {
        @Override
        public BikeRequestEntity createFromParcel(Parcel source) {
            return new BikeRequestEntity(source);
        }

        @Override
        public BikeRequestEntity[] newArray(int size) {
            return new BikeRequestEntity[size];
        }
    };
}
