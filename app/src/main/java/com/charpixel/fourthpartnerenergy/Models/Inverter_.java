
package com.charpixel.fourthpartnerenergy.Models;

import com.charpixel.baseandroidproject.common.SortedListAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inverter_ implements SortedListAdapter.ViewModel {

    @SerializedName("field")
    @Expose
    private String field;
    @SerializedName("energyToday")
    @Expose
    private String energyToday;
    @SerializedName("powerNow")
    @Expose
    private String powerNow;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getEnergyToday() {
        return energyToday;
    }

    public void setEnergyToday(String energyToday) {
        this.energyToday = energyToday;
    }

    public String getPowerNow() {
        return powerNow;
    }

    public void setPowerNow(String powerNow) {
        this.powerNow = powerNow;
    }

}
