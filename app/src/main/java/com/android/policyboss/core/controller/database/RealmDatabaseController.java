package com.android.policyboss.core.controller.database;

import com.android.policyboss.core.models.MakeMasterEntity;
import com.android.policyboss.core.models.ModelMasterEntity;
import com.android.policyboss.core.models.VariantMasterEntity;
import com.android.policyboss.core.models.VehicleMasterEntity;

import java.util.HashMap;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Nilesh Birhade on 29-05-2017.
 */

public class RealmDatabaseController {
    private static final String MAKE_TABLE = "make_entity";
    private static final String MODEL_TABLE = "model_entity";
    private static final String VARIANT_TABLE = "variant_entity";
    private static final String VEHICLE_TABLE = "vehicle_entity";

    Realm realm;
    HashMap<String, Object> weakReference;

    public RealmDatabaseController(Realm realm) {
        this.realm = realm;
        weakReference = new HashMap<String, Object>();
    }

    public void insertMasterTables(final List<MakeMasterEntity> listMake,
                                   final List<ModelMasterEntity> listModel,
                                   final List<VariantMasterEntity> listVariant) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(listMake);
                realm.copyToRealmOrUpdate(listVariant);
                realm.copyToRealmOrUpdate(listModel);
            }
        });

    }

    public void insertMasterVehicleTables(final List<VehicleMasterEntity> listVehicle) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(listVehicle);

            }
        });

    }

    public List<MakeMasterEntity> getMasterMake() {
        if ((List<MakeMasterEntity>) weakReference.get(MAKE_TABLE) != null) {
            return (List<MakeMasterEntity>) weakReference.get(MAKE_TABLE);
        }

        List<MakeMasterEntity> listMake = realm.where(MakeMasterEntity.class).findAll();
        if (listMake != null) {
            weakReference.put(MAKE_TABLE, listMake);
        } else {
            return (List<MakeMasterEntity>) weakReference.get(MAKE_TABLE);
        }
        return listMake;
    }

    public List<ModelMasterEntity> getMasterModel() {
        if ((List<ModelMasterEntity>) weakReference.get(MODEL_TABLE) != null) {
            return (List<ModelMasterEntity>) weakReference.get(MODEL_TABLE);
        }

        List<ModelMasterEntity> listModel = realm.where(ModelMasterEntity.class).findAll();
        if (listModel != null) {
            weakReference.put(MODEL_TABLE, listModel);
        } else {
            return (List<ModelMasterEntity>) weakReference.get(MODEL_TABLE);
        }
        return listModel;
    }

    public List<VariantMasterEntity> getMasterVariant() {
        if ((List<VariantMasterEntity>) weakReference.get(VARIANT_TABLE) != null) {
            return (List<VariantMasterEntity>) weakReference.get(VARIANT_TABLE);
        }

        List<VariantMasterEntity> listModel = realm.where(VariantMasterEntity.class).findAll();
        if (listModel != null) {
            weakReference.put(VARIANT_TABLE, listModel);
        } else {
            return (List<VariantMasterEntity>) weakReference.get(VARIANT_TABLE);
        }
        return listModel;
    }

    public List<VehicleMasterEntity> getMasterVehicle() {
        if ((List<VehicleMasterEntity>) weakReference.get(VEHICLE_TABLE) != null) {
            return (List<VehicleMasterEntity>) weakReference.get(VEHICLE_TABLE);
        }

        List<VehicleMasterEntity> list_Make = realm.where(VehicleMasterEntity.class).findAll();
        if (list_Make != null) {
            weakReference.put(VEHICLE_TABLE, list_Make);
        } else {
            return (List<VehicleMasterEntity>) weakReference.get(VEHICLE_TABLE);
        }
        return list_Make;
    }


}
