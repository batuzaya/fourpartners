
package com.charpixel.fourthpartnerenergy.Models;

import com.charpixel.baseandroidproject.common.SortedListAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inverter implements SortedListAdapter.ViewModel {

    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("capacity")
    @Expose
    private Double capacity;
    @SerializedName("MPPT1")
    @Expose
    private Integer mPPT1;
    @SerializedName("MPPT2")
    @Expose
    private Integer mPPT2;
    @SerializedName("serialNumber")
    @Expose
    private String serialNumber;
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Integer getMPPT1() {
        return mPPT1;
    }

    public void setMPPT1(Integer mPPT1) {
        this.mPPT1 = mPPT1;
    }

    public Integer getMPPT2() {
        return mPPT2;
    }

    public void setMPPT2(Integer mPPT2) {
        this.mPPT2 = mPPT2;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
