package com.charpixel.fourthpartnerenergy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ashu on 24-12-2016.
 */

public class GraphDataType1 {

    private String id;
    private String dataLoggingDate;
    private Double plantCapacity;
    private String inverterEnergyCalculation;
    private String gridDeviceMAC;
    private List<DeviceMac> deviceMac = null;
    private List<Double> meters = null;


    @SerializedName("meter_kwh_import")
    @Expose
    private List<Integer> meterKwhImport = null;
    @SerializedName("meter_kwh_total")
    @Expose
    private List<Integer> meterKwhTotal = null;
    private List<Double> inverters = null;
    private List<Double> pr = null;
    private List<String> date = null;
    private List<InverterArray> inverterArray = null;

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

    public List<Double> getMeters() {
        return meters;
    }

    public void setMeters(List<Double> meters) {
        this.meters = meters;
    }

    public List<Integer> getMeterKwhImport() {
        return meterKwhImport;
    }

    public void setMeterKwhImport(List<Integer> meterKwhImport) {
        this.meterKwhImport = meterKwhImport;
    }

    public List<Integer> getMeterKwhTotal() {
        return meterKwhTotal;
    }

    public void setMeterKwhTotal(List<Integer> meterKwhTotal) {
        this.meterKwhTotal = meterKwhTotal;
    }

    public List<Double> getInverters() {
        return inverters;
    }

    public void setInverters(List<Double> inverters) {
        this.inverters = inverters;
    }

    public List<Double> getPr() {
        return pr;
    }

    public void setPr(List<Double> pr) {
        this.pr = pr;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<InverterArray> getInverterArray() {
        return inverterArray;
    }

    public void setInverterArray(List<InverterArray> inverterArray) {
        this.inverterArray = inverterArray;
    }

}