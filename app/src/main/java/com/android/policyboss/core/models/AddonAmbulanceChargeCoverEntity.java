package com.android.policyboss.core.models;

public class AddonAmbulanceChargeCoverEntity {
    /**
     * min : 100
     * max : 100
     */

    private int min;
    private int max;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
