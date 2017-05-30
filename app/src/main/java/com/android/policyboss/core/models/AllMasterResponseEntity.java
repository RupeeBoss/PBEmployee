package com.android.policyboss.core.models;

import java.util.List;

public class AllMasterResponseEntity {
    private List<MakeMasterEntity> lstmake;
    private List<ModelMasterEntity> lstmodel;
    private List<VariantMasterEntity> lstvariant;

    public List<MakeMasterEntity> getLstmake() {
        return lstmake;
    }

    public void setLstmake(List<MakeMasterEntity> lstmake) {
        this.lstmake = lstmake;
    }

    public List<ModelMasterEntity> getLstmodel() {
        return lstmodel;
    }

    public void setLstmodel(List<ModelMasterEntity> lstmodel) {
        this.lstmodel = lstmodel;
    }

    public List<VariantMasterEntity> getLstvariant() {
        return lstvariant;
    }

    public void setLstvariant(List<VariantMasterEntity> lstvariant) {
        this.lstvariant = lstvariant;
    }


}
