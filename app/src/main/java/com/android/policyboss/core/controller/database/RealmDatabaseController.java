package com.android.policyboss.core.controller.database;

import com.android.policyboss.core.models.MakeEntity;
import com.android.policyboss.core.models.ModelEntity;
import com.android.policyboss.core.models.VariantEntity;
import com.android.policyboss.core.models.VariantMasterEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Nilesh Birhade on 29-05-2017.
 */

public class RealmDatabaseController {
    private static final String MAKE_TABLE = "make_entity";
    private static final String MODEL_TABLE = "model_entity";
    private static final String VARIANT_TABLE = "variant_entity";

    Realm realm;
    HashMap<String, Object> weakReference;

    public RealmDatabaseController(Realm realm) {
        this.realm = realm;
        weakReference = new HashMap<String, Object>();
    }

    public void insertVariantMaster(final List<VariantMasterEntity> list) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(list);
            }
        });
    }


    public List<ModelEntity> getModelMaster() {
        if ((List<ModelEntity>) weakReference.get(MODEL_TABLE) != null) {
            return (List<ModelEntity>) weakReference.get(MODEL_TABLE);
        }

        List<ModelEntity> list = new ArrayList<>();
        RealmResults<VariantMasterEntity> modelList = realm.where(VariantMasterEntity.class)
                .distinct("Model_ID").sort("Model_Name");

        if (modelList.size() != 0) {
            for (int i = 0; i < modelList.size(); i++) {
                ModelEntity modelEntity = new ModelEntity();
                modelEntity.setModel_ID(modelList.get(i).getModel_ID());
                modelEntity.setModel_Name(modelList.get(i).getModel_Name());
                list.add(modelEntity);
            }
            weakReference.put(MODEL_TABLE, list);
        }

        return list;

    }


    public List<VariantEntity> getVariantMaster() {
        if ((List<VariantEntity>) weakReference.get(VARIANT_TABLE) != null) {
            return (List<VariantEntity>) weakReference.get(VARIANT_TABLE);
        }

        List<VariantEntity> list = new ArrayList<>();
        RealmResults<VariantMasterEntity> variantList = realm.where(VariantMasterEntity.class)
                .distinct("Variant_Id").sort("Variant_Name");

        if (variantList.size() != 0) {
            for (int i = 0; i < variantList.size(); i++) {
                VariantEntity variantEntity = new VariantEntity();
                variantEntity.setVariant_Id(variantList.get(i).getVariant_Id());
                variantEntity.setVariant_Name(variantList.get(i).getVariant_Name());
                list.add(variantEntity);
            }
            weakReference.put(VARIANT_TABLE, list);
        }

        return list;

    }


    public List<MakeEntity> getMakeMaster() {

        if ((List<MakeEntity>) weakReference.get(MAKE_TABLE) != null) {
            return (List<MakeEntity>) weakReference.get(MAKE_TABLE);
        }

        List<MakeEntity> list = new ArrayList<>();
        RealmResults<VariantMasterEntity> makeList = realm.where(VariantMasterEntity.class)
                .distinct("Make_ID").sort("Make_Name");

        if (makeList.size() != 0) {
            for (int i = 0; i < makeList.size(); i++) {
                MakeEntity makeEntity = new MakeEntity();
                makeEntity.setMake_ID(makeList.get(i).getMake_ID());
                makeEntity.setMake_Name(makeList.get(i).getMake_Name());
                list.add(makeEntity);
            }
            weakReference.put(MAKE_TABLE, list);
        }

        return list;
    }

}
