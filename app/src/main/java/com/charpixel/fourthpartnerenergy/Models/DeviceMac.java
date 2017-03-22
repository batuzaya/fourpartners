
package com.charpixel.fourthpartnerenergy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeviceMac {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("inverters")
    @Expose
    private ArrayList<Inverter> inverters = null;
    @SerializedName("irradiationSensorInstalled")
    @Expose
    private Boolean irradiationSensorInstalled;
    @SerializedName("meters")
    @Expose
    private ArrayList<Meter> meters = null;
    @SerializedName("deviceName")
    @Expose
    private String deviceName;
    @SerializedName("macAddress")
    @Expose
    private String macAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Inverter> getInverters() {
        return inverters;
    }

    public void setInverters(ArrayList<Inverter> inverters) {
        this.inverters = inverters;
    }

    public Boolean getIrradiationSensorInstalled() {
        return irradiationSensorInstalled;
    }

    public void setIrradiationSensorInstalled(Boolean irradiationSensorInstalled) {
        this.irradiationSensorInstalled = irradiationSensorInstalled;
    }

    public ArrayList<Meter> getMeters() {
        return meters;
    }

    public void setMeters(ArrayList<Meter> meters) {
        this.meters = meters;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

}
