
package com.charpixel.fourthpartnerenergy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GraphDataType2 {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("dataLoggingDate")
    @Expose
    private String dataLoggingDate;
    @SerializedName("plantCapacity")
    @Expose
    private Double plantCapacity;
    @SerializedName("inverterEnergyCalculation")
    @Expose
    private String inverterEnergyCalculation;
    @SerializedName("gridDeviceMAC")
    @Expose
    private String gridDeviceMAC;
    @SerializedName("deviceMac")
    @Expose
    private List<DeviceMac> deviceMac = null;
    @SerializedName("irradiation_kwh")
    @Expose
    private List<Double> irradiationKwh = null;
    @SerializedName("inverters")
    @Expose
    private List<InverterGraph> inverters = null;
    @SerializedName("date")
    @Expose
    private List<String> date = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataLoggingDate() {
        return dataLoggingDate;
    }

    public void setDataLoggingDate(String dataLoggingDate) {
        this.dataLoggingDate = dataLoggingDate;
    }

    public Double getPlantCapacity() {
        return plantCapacity;
    }

    public void setPlantCapacity(Double plantCapacity) {
        this.plantCapacity = plantCapacity;
    }

    public String getInverterEnergyCalculation() {
        return inverterEnergyCalculation;
    }

    public void setInverterEnergyCalculation(String inverterEnergyCalculation) {
        this.inverterEnergyCalculation = inverterEnergyCalculation;
    }

    public String getGridDeviceMAC() {
        return gridDeviceMAC;
    }

    public void setGridDeviceMAC(String gridDeviceMAC) {
        this.gridDeviceMAC = gridDeviceMAC;
    }

    public List<DeviceMac> getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(List<DeviceMac> deviceMac) {
        this.deviceMac = deviceMac;
    }

    public List<Double> getIrradiationKwh() {
        return irradiationKwh;
    }

    public void setIrradiationKwh(List<Double> irradiationKwh) {
        this.irradiationKwh = irradiationKwh;
    }

    public List<InverterGraph> getInverters() {
        return inverters;
    }

    public void setInverters(List<InverterGraph> inverters) {
        this.inverters = inverters;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

}
