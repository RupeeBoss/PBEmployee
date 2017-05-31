package com.android.policyboss.core.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FastLaneResponseEntity implements Parcelable {
        /**
         * Chassis_Number : MA3EHKD1S00829016
         * Color : GRANITE GR
         * Cubic_Capacity : 1197
         * Engin_Number : K12MN1533889
         * ErrorMessage :
         * FastLaneId : 968
         * FastLaneResponseVariable : {"chasi_no":"MA3EHKD1S00829016","color":"GRANITEGR","cubic_cap":"1197","eng_no":"K12MN1533889","fla_cubic_cap":"1197","fla_fuel_type_desc":"PETROL","fla_maker_desc":"MARUTISUZUKI","fla_model_desc":"SWIFT","fla_seat_cap":"5","fla_variant":"VXI","fla_vh_class_desc":"LMV","fuel_type_desc":"PETROL","maker_desc":"MARSILMARUTISUZUKIIND.LTD","maker_model":"NEWSWIFTVXI","manu_yr":"2015","purchase_dt":"23/2/2015","regn_dt":"27/2/2015","regn_no":"GJ01RJ3447","rto_cd":"1","rto_name":"AHMEDABAD","seat_cap":"5","state_cd":"GJ","vehicle_cd":"123019060114","vh_class_desc":"L.M.V.(CAR)"}
         * Fuel_ID : 1
         * Fuel_Type : Petrol
         * Make_ID : 16
         * Make_Name : Maruti
         * Manufacture_Year : 2015
         * Model_ID : 134
         * Model_Name : Swift
         * Purchase_Date : 23/2/2015
         * RTO_Code : 1
         * RTO_Name : AHMEDABAD
         * Registration_Date : 27/2/2015
         * Registration_Number : GJ01RJ3447
         * Seating_Capacity : 5
         * Variant_Id : 684
         * Variant_Name : VXI
         * VehicleCity_Id : 0
         */

        private String Chassis_Number;
        private String Color;
        private int Cubic_Capacity;
        private String Engin_Number;
        private String ErrorMessage;
        private int FastLaneId;
        private FastLaneResponseVariableEntity FastLaneResponseVariable;
        private int Fuel_ID;
        private String Fuel_Type;
        private int Make_ID;
        private String Make_Name;
        private String Manufacture_Year;
        private int Model_ID;
        private String Model_Name;
        private String Purchase_Date;
        private int RTO_Code;
        private String RTO_Name;
        private String Registration_Date;
        private String Registration_Number;
        private int Seating_Capacity;
        private int Variant_Id;
        private String Variant_Name;
        private int VehicleCity_Id;

        public String getChassis_Number() {
            return Chassis_Number;
        }

        public void setChassis_Number(String Chassis_Number) {
            this.Chassis_Number = Chassis_Number;
        }

        public String getColor() {
            return Color;
        }

        public void setColor(String Color) {
            this.Color = Color;
        }

        public int getCubic_Capacity() {
            return Cubic_Capacity;
        }

        public void setCubic_Capacity(int Cubic_Capacity) {
            this.Cubic_Capacity = Cubic_Capacity;
        }

        public String getEngin_Number() {
            return Engin_Number;
        }

        public void setEngin_Number(String Engin_Number) {
            this.Engin_Number = Engin_Number;
        }

        public String getErrorMessage() {
            return ErrorMessage;
        }

        public void setErrorMessage(String ErrorMessage) {
            this.ErrorMessage = ErrorMessage;
        }

        public int getFastLaneId() {
            return FastLaneId;
        }

        public void setFastLaneId(int FastLaneId) {
            this.FastLaneId = FastLaneId;
        }

        public FastLaneResponseVariableEntity getFastLaneResponseVariable() {
            return FastLaneResponseVariable;
        }

        public void setFastLaneResponseVariable(FastLaneResponseVariableEntity FastLaneResponseVariable) {
            this.FastLaneResponseVariable = FastLaneResponseVariable;
        }

        public int getFuel_ID() {
            return Fuel_ID;
        }

        public void setFuel_ID(int Fuel_ID) {
            this.Fuel_ID = Fuel_ID;
        }

        public String getFuel_Type() {
            return Fuel_Type;
        }

        public void setFuel_Type(String Fuel_Type) {
            this.Fuel_Type = Fuel_Type;
        }

        public int getMake_ID() {
            return Make_ID;
        }

        public void setMake_ID(int Make_ID) {
            this.Make_ID = Make_ID;
        }

        public String getMake_Name() {
            return Make_Name;
        }

        public void setMake_Name(String Make_Name) {
            this.Make_Name = Make_Name;
        }

        public String getManufacture_Year() {
            return Manufacture_Year;
        }

        public void setManufacture_Year(String Manufacture_Year) {
            this.Manufacture_Year = Manufacture_Year;
        }

        public int getModel_ID() {
            return Model_ID;
        }

        public void setModel_ID(int Model_ID) {
            this.Model_ID = Model_ID;
        }

        public String getModel_Name() {
            return Model_Name;
        }

        public void setModel_Name(String Model_Name) {
            this.Model_Name = Model_Name;
        }

        public String getPurchase_Date() {
            return Purchase_Date;
        }

        public void setPurchase_Date(String Purchase_Date) {
            this.Purchase_Date = Purchase_Date;
        }

        public int getRTO_Code() {
            return RTO_Code;
        }

        public void setRTO_Code(int RTO_Code) {
            this.RTO_Code = RTO_Code;
        }

        public String getRTO_Name() {
            return RTO_Name;
        }

        public void setRTO_Name(String RTO_Name) {
            this.RTO_Name = RTO_Name;
        }

        public String getRegistration_Date() {
            return Registration_Date;
        }

        public void setRegistration_Date(String Registration_Date) {
            this.Registration_Date = Registration_Date;
        }

        public String getRegistration_Number() {
            return Registration_Number;
        }

        public void setRegistration_Number(String Registration_Number) {
            this.Registration_Number = Registration_Number;
        }

        public int getSeating_Capacity() {
            return Seating_Capacity;
        }

        public void setSeating_Capacity(int Seating_Capacity) {
            this.Seating_Capacity = Seating_Capacity;
        }

        public int getVariant_Id() {
            return Variant_Id;
        }

        public void setVariant_Id(int Variant_Id) {
            this.Variant_Id = Variant_Id;
        }

        public String getVariant_Name() {
            return Variant_Name;
        }

        public void setVariant_Name(String Variant_Name) {
            this.Variant_Name = Variant_Name;
        }

        public int getVehicleCity_Id() {
            return VehicleCity_Id;
        }

        public void setVehicleCity_Id(int VehicleCity_Id) {
            this.VehicleCity_Id = VehicleCity_Id;
        }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Chassis_Number);
        dest.writeString(this.Color);
        dest.writeInt(this.Cubic_Capacity);
        dest.writeString(this.Engin_Number);
        dest.writeString(this.ErrorMessage);
        dest.writeInt(this.FastLaneId);
        dest.writeParcelable(this.FastLaneResponseVariable, flags);
        dest.writeInt(this.Fuel_ID);
        dest.writeString(this.Fuel_Type);
        dest.writeInt(this.Make_ID);
        dest.writeString(this.Make_Name);
        dest.writeString(this.Manufacture_Year);
        dest.writeInt(this.Model_ID);
        dest.writeString(this.Model_Name);
        dest.writeString(this.Purchase_Date);
        dest.writeInt(this.RTO_Code);
        dest.writeString(this.RTO_Name);
        dest.writeString(this.Registration_Date);
        dest.writeString(this.Registration_Number);
        dest.writeInt(this.Seating_Capacity);
        dest.writeInt(this.Variant_Id);
        dest.writeString(this.Variant_Name);
        dest.writeInt(this.VehicleCity_Id);
    }

    public FastLaneResponseEntity() {
    }

    protected FastLaneResponseEntity(Parcel in) {
        this.Chassis_Number = in.readString();
        this.Color = in.readString();
        this.Cubic_Capacity = in.readInt();
        this.Engin_Number = in.readString();
        this.ErrorMessage = in.readString();
        this.FastLaneId = in.readInt();
        this.FastLaneResponseVariable = in.readParcelable(FastLaneResponseVariableEntity.class.getClassLoader());
        this.Fuel_ID = in.readInt();
        this.Fuel_Type = in.readString();
        this.Make_ID = in.readInt();
        this.Make_Name = in.readString();
        this.Manufacture_Year = in.readString();
        this.Model_ID = in.readInt();
        this.Model_Name = in.readString();
        this.Purchase_Date = in.readString();
        this.RTO_Code = in.readInt();
        this.RTO_Name = in.readString();
        this.Registration_Date = in.readString();
        this.Registration_Number = in.readString();
        this.Seating_Capacity = in.readInt();
        this.Variant_Id = in.readInt();
        this.Variant_Name = in.readString();
        this.VehicleCity_Id = in.readInt();
    }

    public static final Parcelable.Creator<FastLaneResponseEntity> CREATOR = new Parcelable.Creator<FastLaneResponseEntity>() {
        @Override
        public FastLaneResponseEntity createFromParcel(Parcel source) {
            return new FastLaneResponseEntity(source);
        }

        @Override
        public FastLaneResponseEntity[] newArray(int size) {
            return new FastLaneResponseEntity[size];
        }
    };
}
