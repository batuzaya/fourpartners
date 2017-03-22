
package com.charpixel.fourthpartnerenergy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InvertersDatum {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("inverters")
    @Expose
    private ArrayList<Inverter_> inverters = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("macAddress")
    @Expose
    private String macAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Inverter_> getInverters() {
        return inverters;
    }

    public void setInverters(ArrayList<Inverter_> inverters) {
        this.inverters = inverters;
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

}
