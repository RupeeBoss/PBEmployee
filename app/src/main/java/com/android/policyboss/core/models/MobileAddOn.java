package com.android.policyboss.core.models;

/**
 * Created by Nilesh Birhade on 16-11-2017.
 */

public class MobileAddOn {

    public String AddonName;
    public int min;
    public int max;
    public String AddonKey;
    public boolean isSelected;

    public String getAddonName() {
        return AddonName;
    }

    public void setAddonName(String addonName) {
        AddonName = addonName;
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

    public String getAddonKey() {
        return AddonKey;
    }

    public void setAddonKey(String addonKey) {
        AddonKey = addonKey;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
