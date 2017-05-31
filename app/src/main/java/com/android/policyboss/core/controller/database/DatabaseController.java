package com.android.policyboss.core.controller.database;

import android.content.Context;

import com.android.policyboss.core.models.MakeMasterEntity;
import com.android.policyboss.core.models.ModelMasterEntity;
import com.android.policyboss.core.models.VariantMasterEntity;
import com.android.policyboss.core.models.VehicleMasterEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Nilesh Birhade on 30-05-2017.
 */

public class DatabaseController implements IDBController {
    Realm realm;
    Context context;
    RealmDatabaseController dbController;

    public DatabaseController(Context context, Realm realm) {
        this.realm = realm;
        this.context = context;
        dbController = new RealmDatabaseController(this.realm);
    }

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

    public List<VariantMasterEntity> getFuelType(String modelID) {
        List<VariantMasterEntity> list = realm.where(VariantMasterEntity.class).equalTo("Model_ID", modelID).findAll();
        return list;
    }

    //region Model

    @Override
    public List<String> getModelList(int makeID) {
        List<String> listModel = new ArrayList<>();
        // List<ModelMasterEntity> listModelMaster = dbController.getMasterModel();
        List<ModelMasterEntity> list = realm.where(ModelMasterEntity.class).equalTo("Make_ID", "" + makeID).findAll();

        for (int i = 0; i < list.size(); i++) {
            listModel.add(list.get(i).getModel_Name());
        }
        return listModel;
    }

    @Override
    public int getModelID(int makeID, String modelName) {
        // List<ModelMasterEntity> listModelMaster = dbController.getMasterModel();

        List<ModelMasterEntity> list = realm.where(ModelMasterEntity.class).equalTo("Make_ID", "" + makeID).findAll();

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

        List<VariantMasterEntity> list = realm.where(VariantMasterEntity.class).equalTo("Model_ID", "" + modelID).findAll();

        for (int i = 0; i < list.size(); i++) {
            listFuelType.add(list.get(i).getFuel_Name());
        }
        return listFuelType;
    }

    @Override
    public int getFuelID(String fuelType, int modelID) {
        VariantMasterEntity list = realm.where(VariantMasterEntity.class)
                .equalTo("Fuel_Name", "" + fuelType)
                .equalTo("Model_ID", "" + modelID).findFirst();

        return list.getFuel_ID();
    }


    //endregion

    //region variant

    @Override
    public List<String> getVariantList(int modelID) {
        List<String> listVarient = new ArrayList<>();
        // List<VariantMasterEntity> listVariantMaster = dbController.getMasterVariant();

        List<VariantMasterEntity> list = realm.where(VariantMasterEntity.class).equalTo("Model_ID", "" + modelID).findAll();

        for (int i = 0; i < list.size(); i++) {
            listVarient.add(list.get(i).getVariant_Name());
        }
        return listVarient;
    }

    @Override
    public int getVariantID(String variantName) {
        List<VariantMasterEntity> listVariantMaster = dbController.getMasterVariant();
        for (int i = 0; i < listVariantMaster.size(); i++) {
            if (listVariantMaster.get(i).getVariant_Name().equals(variantName)) {
                return listVariantMaster.get(i).getVariant_ID();
            }
        }
        return 0;
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
}
