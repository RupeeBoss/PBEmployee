package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.FastLaneResponseEntity;

/**
 * Created by Nilesh Birhade on 29-05-2017.
 */

public class FastLaneResponse extends APIResponse {

    /**
     * FastLaneResponseEntity : {"Chassis_Number":"MA3EHKD1S00829016","Color":"GRANITE GR","Cubic_Capacity":1197,"Engin_Number":"K12MN1533889","ErrorMessage":"","FastLaneId":968,"FastLaneResponseVariable":{"chasi_no":"MA3EHKD1S00829016","color":"GRANITEGR","cubic_cap":"1197","eng_no":"K12MN1533889","fla_cubic_cap":"1197","fla_fuel_type_desc":"PETROL","fla_maker_desc":"MARUTISUZUKI","fla_model_desc":"SWIFT","fla_seat_cap":"5","fla_variant":"VXI","fla_vh_class_desc":"LMV","fuel_type_desc":"PETROL","maker_desc":"MARSILMARUTISUZUKIIND.LTD","maker_model":"NEWSWIFTVXI","manu_yr":"2015","purchase_dt":"23/2/2015","regn_dt":"27/2/2015","regn_no":"GJ01RJ3447","rto_cd":"1","rto_name":"AHMEDABAD","seat_cap":"5","state_cd":"GJ","vehicle_cd":"123019060114","vh_class_desc":"L.M.V.(CAR)"},"Fuel_ID":1,"Fuel_Type":"Petrol","Make_ID":16,"Make_Name":"Maruti","Manufacture_Year":"2015","Model_ID":134,"Model_Name":"Swift","Purchase_Date":"23/2/2015","RTO_Code":1,"RTO_Name":"AHMEDABAD","Registration_Date":"27/2/2015","Registration_Number":"GJ01RJ3447","Seating_Capacity":5,"Variant_Id":684,"Variant_Name":"VXI","VehicleCity_Id":0}
     */

    private FastLaneResponseEntity FastLaneEntity;

    public FastLaneResponseEntity getFastLaneEntity() {
        return FastLaneEntity;
    }

    public void setFastLaneEntity(FastLaneResponseEntity fastLaneEntity) {
        this.FastLaneEntity = fastLaneEntity;
    }
}
