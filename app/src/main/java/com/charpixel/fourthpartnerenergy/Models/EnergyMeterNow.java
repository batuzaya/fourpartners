
package com.charpixel.fourthpartnerenergy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EnergyMeterNow {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("macAddress")
    @Expose
    private String macAddress;
    @SerializedName("meters")
    @Expose
    private ArrayList<Meter_> meters = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public ArrayList<Meter_> getMeters() {
        return meters;
    }

    public void setMeters(ArrayList<Meter_> meters) {
        this.meters = meters;
    }

}
