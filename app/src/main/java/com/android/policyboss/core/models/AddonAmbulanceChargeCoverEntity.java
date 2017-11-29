package com.android.policyboss.core.models;

public class AddonAmbulanceChargeCoverEntity {
    /**
     * min : 100
     * max : 100
     */

    private double min;
    private double max;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
