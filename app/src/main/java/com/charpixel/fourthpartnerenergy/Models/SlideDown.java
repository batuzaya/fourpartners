
package com.charpixel.fourthpartnerenergy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SlideDown {

    @SerializedName("energyMeterNow")
    @Expose
    private List<EnergyMeterNow> energyMeterNow = null;
    @SerializedName("stat")
    @Expose
    private List<Stat> stat = null;
    @SerializedName("invertersData")
    @Expose
    private List<InvertersDatum> invertersData = null;

    public List<EnergyMeterNow> getEnergyMeterNow() {
        return energyMeterNow;
    }

    public void setEnergyMeterNow(List<EnergyMeterNow> energyMeterNow) {
        this.energyMeterNow = energyMeterNow;
    }

    public List<Stat> getStat() {
        return stat;
    }

    public void setStat(List<Stat> stat) {
        this.stat = stat;
    }

    public List<InvertersDatum> getInvertersData() {
        return invertersData;
    }

    public void setInvertersData(List<InvertersDatum> invertersData) {
        this.invertersData = invertersData;
    }

}
