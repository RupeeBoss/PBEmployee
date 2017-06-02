package com.android.policyboss.core.controller.database;

import android.content.Context;
import android.util.Log;

import com.android.policyboss.R;
import com.android.policyboss.core.models.MakeMasterEntity;
import com.android.policyboss.core.models.ModelMasterEntity;
import com.android.policyboss.core.models.VariantMasterEntity;
import com.android.policyboss.core.models.VehicleMasterEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Nilesh Birhade on 30-05-2017.
 */

public class DatabaseController implements IDBController {
    Realm realm;
    Context context;
    RealmDatabaseController dbController;
    HashMap<String, Integer> hashMapInsurence;
    HashMap<String, Integer> hashMapProfession;

    static HashMap<Integer, Integer> hasMapCarInsuranceImage;

    public DatabaseController(Context context, Realm realm) {
        this.realm = realm;
        this.context = context;
        dbController = new RealmDatabaseController(this.realm);

    }

    //region mapping Insurence and Profession

    public void MapInsurence() {

        hashMapInsurence.put("Bajaj Allianz General Insurance Co. Ltd.", 1);
        hashMapInsurence.put("Bharti Axa General Insurance Co.Ltd.", 2);
        hashMapInsurence.put("Cholamandalam MS General Insurance Co.Ltd.", 3);
        hashMapInsurence.put("Future Generali India Insurance Co.Ltd.", 4);
        hashMapInsurence.put("HDFC ERGO General Insurance Co.Ltd.", 5);
        hashMapInsurence.put("ICICI Lombard General Insurance Co.Ltd.", 6);
        hashMapInsurence.put("IFFCO Tokio General Insurance Co.Ltd.", 7);
        hashMapInsurence.put("L & T General Insurance Co.Ltd.", 15);
        hashMapInsurence.put("Liberty Videocon General Insurance Co.Ltd.", 33);
        hashMapInsurence.put("Magma HDI General Insurance Co.Ltd", 35);
        hashMapInsurence.put("National Insurance Co.Ltd.", 8);
        hashMapInsurence.put("Raheja QBE General Insurance Co.Ltd.", 16);
        hashMapInsurence.put("Reliance General Insurance Co.Ltd.", 9);
        hashMapInsurence.put("Royal Sundaram Alliance Insurance Co.Ltd", 10);
        hashMapInsurence.put("SBI General Insurance Co.Ltd.", 17);
        hashMapInsurence.put("Shriram General Insurance Co.Ltd.", 18);
        hashMapInsurence.put("Tata AIG General Insurance Co.Ltd.", 11);
        hashMapInsurence.put("The New India Assurance Co.Ltd.", 12);
        hashMapInsurence.put("The Oriental Insurance Co.Ltd.", 13);
        hashMapInsurence.put("United India Insurance Co.Ltd.", 14);
        hashMapInsurence.put("Universal Sompo General Insurance Co.Ltd.", 19);

    }

    public int getInsurenceID(String insurenceName) {
        hashMapInsurence = new HashMap<String, Integer>();
        MapInsurence();
        return hashMapInsurence.get(insurenceName);
    }

    public List<String> getInsurerList() {
        hashMapInsurence = new HashMap<String, Integer>();
        MapInsurence();
        return new ArrayList<String>(hashMapInsurence.keySet());

    }

    public void MapProfession() {

        hashMapProfession.put("Practicing Chartered Accountant", 1);
        hashMapProfession.put("Teacher in Govt.recognized Institutions", 2);
        hashMapProfession.put("Doctors registered with Government", 3);
        hashMapProfession.put("Defense and Para Military Service", 4);
        hashMapProfession.put("Central / State Government Employees", 5);
        hashMapProfession.put("Other", 6);

    }

    public int getProfessionalID(String professionName) {
        hashMapProfession = new HashMap<String, Integer>();
        MapProfession();
        return hashMapProfession.get(professionName);
    }


    //endregion

    //region Makedata

    @Override
    public List<String> getMakeList() {
        List<String> listMake = new ArrayList<>();
        List<MakeMasterEntity> listMakeMaster = dbController.getMasterMake();

        for (int i = 0; i < listMakeMaster.size(); i++) {
            listMake.add(listMakeMaster.get(i).getMake_Name());
        }
        return listMake;
    }

    @Override
    public int getMakeID(String makeName) {

        List<MakeMasterEntity> listMakeMaster = dbController.getMasterMake();
        for (int i = 0; i < listMakeMaster.size(); i++) {
            if (listMakeMaster.get(i).getMake_Name().equals(makeName)) {
                return listMakeMaster.get(i).getMake_ID();
            }
        }
        return 0;
    }

    //endregion

    //region Model

    @Override
    public List<String> getModelList(int makeID) {
        List<String> listModel = new ArrayList<>();
        // List<ModelMasterEntity> listModelMaster = dbController.getMasterModel();
        List<ModelMasterEntity> list = realm.where(ModelMasterEntity.class).equalTo("Make_ID", makeID).findAll();

        for (int i = 0; i < list.size(); i++) {
            listModel.add(list.get(i).getModel_Name());
        }
        return listModel;
    }

    @Override
    public int getModelID(int makeID, String modelName) {
        // List<ModelMasterEntity> listModelMaster = dbController.getMasterModel();

        List<ModelMasterEntity> list = realm.where(ModelMasterEntity.class).equalTo("Make_ID", makeID).findAll();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModel_Name().equals(modelName)) {
                return list.get(i).getModel_ID();
            }
        }
        return 0;
    }

    //endregion


    //region fuel types
    @Override
    public List<String> getFuelType(int modelID) {
        List<String> listFuelType = new ArrayList<>();
        // List<VariantMasterEntity> listVariantMaster = dbController.getMasterVariant();

        List<VariantMasterEntity> list = realm.where(VariantMasterEntity.class).equalTo("Model_ID", modelID).distinct("Fuel_Name");

        for (int i = 0; i < list.size(); i++) {
            listFuelType.add(list.get(i).getFuel_Name());
        }
        return listFuelType;
    }

    @Override
    public int getFuelID(String fuelType, int modelID) {
        VariantMasterEntity list = realm.where(VariantMasterEntity.class)
                .equalTo("Fuel_Name", "" + fuelType)
                .equalTo("Model_ID", modelID).findFirst();

        return list.getFuel_ID();
    }


    //endregion

    //region variant

    @Override
    public List<String> getVariantList(int modelID) {
        List<String> listVarient = new ArrayList<>();
        // List<VariantMasterEntity> listVariantMaster = dbController.getMasterVariant();

        List<VariantMasterEntity> list = realm.where(VariantMasterEntity.class).equalTo("Model_ID", modelID).findAll();

        for (int i = 0; i < list.size(); i++) {
            listVarient.add(list.get(i).getVariant_Name());
        }
        return listVarient;
    }

    @Override
    public int getVariantID(String variantName) {

        Log.d("SS", variantName);
        VariantMasterEntity entity = realm.where(VariantMasterEntity.class).equalTo("Variant_Name", variantName)
                .findFirst();

        Log.d("SSs", entity.getVariant_Name() + " " + entity.getVariant_ID());
        return entity.getVariant_ID();
    }


    //endregion

    //region city

    @Override
    public List<String> getCity() {
        List<String> listCity = new ArrayList<>();
        List<VehicleMasterEntity> list = dbController.getMasterVehicle();

        for (int i = 0; i < list.size(); i++) {
            listCity.add(list.get(i).getRTO_CodeDiscription());
        }

        return listCity;
    }

    @Override
    public int getCityID(String cityName) {
        List<VehicleMasterEntity> listVehicleMaster = dbController.getMasterVehicle();
        for (int i = 0; i < listVehicleMaster.size(); i++) {
            if (listVehicleMaster.get(i).getRTO_CodeDiscription().equals(cityName)) {
                return listVehicleMaster.get(i).getVehicleCity_Id();
            }
        }
        return 0;
    }

    //endregion

    //region Car Insurance

    public static void MapCarInsuranceImage() {

        hasMapCarInsuranceImage.put(1, R.drawable.carins1);
        hasMapCarInsuranceImage.put(2, R.drawable.carins2);
        hasMapCarInsuranceImage.put(3, R.drawable.carins3);
        hasMapCarInsuranceImage.put(4, R.drawable.carins4);

        hasMapCarInsuranceImage.put(5, R.drawable.carins5);
        hasMapCarInsuranceImage.put(6, R.drawable.carins6);
        hasMapCarInsuranceImage.put(7, R.drawable.carins7);
        hasMapCarInsuranceImage.put(8, R.drawable.carins8);
        hasMapCarInsuranceImage.put(9, R.drawable.carins9);
        hasMapCarInsuranceImage.put(10, R.drawable.carins10);
        hasMapCarInsuranceImage.put(11, R.drawable.carins11);
        hasMapCarInsuranceImage.put(12, R.drawable.carins12);
        hasMapCarInsuranceImage.put(14, R.drawable.carins14);
        hasMapCarInsuranceImage.put(15, R.drawable.carins15);
        hasMapCarInsuranceImage.put(16, R.drawable.carins16);
        hasMapCarInsuranceImage.put(17, R.drawable.carins17);
        hasMapCarInsuranceImage.put(18, R.drawable.carins18);
        hasMapCarInsuranceImage.put(19, R.drawable.carins19);
        hasMapCarInsuranceImage.put(33, R.drawable.carins33);
        hasMapCarInsuranceImage.put(35, R.drawable.carins35);

    }

    public static int getProfessionalID1(int pic) {

        hasMapCarInsuranceImage = new HashMap<Integer, Integer>();
        MapCarInsuranceImage();
        return hasMapCarInsuranceImage.get(pic);
    }

    //endregion
}
