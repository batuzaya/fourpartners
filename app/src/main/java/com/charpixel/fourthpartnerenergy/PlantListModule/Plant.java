package com.charpixel.fourthpartnerenergy.PlantListModule;

import com.charpixel.baseandroidproject.common.SortedListAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ashu on 13-12-2016.
 */

public class Plant implements SortedListAdapter.ViewModel {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("inverterEnergyCalculation")
    @Expose
    private String inverterEnergyCalculation;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("generationGuaranteed")
    @Expose
    private Object generationGuaranteed;
    @SerializedName("siteInchargeInternal")
    @Expose
    private Object siteInchargeInternal;
    @SerializedName("panelModel")
    @Expose
    private String panelModel;
    @SerializedName("panelMake")
    @Expose
    private String panelMake;
    @SerializedName("contactPersonDesignation")
    @Expose
    private String contactPersonDesignation;
    @SerializedName("contactPersonEmail")
    @Expose
    private String  contactPersonEmail;
    @SerializedName("contactPersonNumberTwo")
    @Expose
    private String contactPersonNumberTwo;
    @SerializedName("contactPersonNumberOne")
    @Expose
    private String contactPersonNumberOne;
    @SerializedName("contactPersonName")
    @Expose
    private String contactPersonName;
    @SerializedName("dataLoggingDate")
    @Expose
    private String dataLoggingDate;
    @SerializedName("commissioningDate")
    @Expose
    private String commissioningDate;
    @SerializedName("plantCapacity")
    @Expose
    private Double plantCapacity;
    @SerializedName("deviceMacLength")
    @Expose
    private Integer deviceMacLength;
    @SerializedName("location")
    @Expose
    private List<Double> location = null;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("tariff")
    @Expose
    private Double tariff;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("addressLineTwo")
    @Expose
    private String addressLineTwo;
    @SerializedName("addressLineOne")
    @Expose
    private String addressLineOne;
    @SerializedName("plantName")
    @Expose
    private String plantName;


    @SerializedName("plantStatus")
    @Expose
    private Integer plantStatus;
    @SerializedName("energyToday")
    @Expose
    private Integer energyToday;
    @SerializedName("energyYesterday")
    @Expose
    private Integer energyYesterday;
    @SerializedName("energyLifetime")
    @Expose
    private Integer energyLifetime;
    @SerializedName("commStatus")
    @Expose
    private Integer commStatus;
    @SerializedName("pr")
    @Expose
    private String pr;
    @SerializedName("kWh/kWp")
    @Expose
    private String kwhkwp;

    public String getPowerNow() {
        return powerNow;
    }

    public void setPowerNow(String powerNow) {
        this.powerNow = powerNow;
    }

    private String powerNow;

    public String getKwhkwp() {
        return kwhkwp;
    }

    public void setKwhkwp(String kwhkwp) {
        this.kwhkwp = kwhkwp;
    }

    public Integer getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(Integer plantStatus) {
        this.plantStatus = plantStatus;
    }

    public Integer getEnergyToday() {
        return energyToday;
    }

    public void setEnergyToday(Integer energyToday) {
        this.energyToday = energyToday;
    }

    public Integer getEnergyYesterday() {
        return energyYesterday;
    }

    public void setEnergyYesterday(Integer energyYesterday) {
        this.energyYesterday = energyYesterday;
    }

    public Integer getEnergyLifetime() {
        return energyLifetime;
    }

    public void setEnergyLifetime(Integer energyLifetime) {
        this.energyLifetime = energyLifetime;
    }

    public Integer getCommStatus() {
        return commStatus;
    }

    public void setCommStatus(Integer commStatus) {
        this.commStatus = commStatus;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getInverterEnergyCalculation() {
        return inverterEnergyCalculation;
    }

    public void setInverterEnergyCalculation(String inverterEnergyCalculation) {
        this.inverterEnergyCalculation = inverterEnergyCalculation;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Object getGenerationGuaranteed() {
        return generationGuaranteed;
    }

    public void setGenerationGuaranteed(Object generationGuaranteed) {
        this.generationGuaranteed = generationGuaranteed;
    }

    public Object getSiteInchargeInternal() {
        return siteInchargeInternal;
    }

    public void setSiteInchargeInternal(Object siteInchargeInternal) {
        this.siteInchargeInternal = siteInchargeInternal;
    }

    public String getPanelModel() {
        return panelModel;
    }

    public void setPanelModel(String panelModel) {
        this.panelModel = panelModel;
    }

    public String getPanelMake() {
        return panelMake;
    }

    public void setPanelMake(String panelMake) {
        this.panelMake = panelMake;
    }

    public String getContactPersonDesignation() {
        return contactPersonDesignation;
    }

    public void setContactPersonDesignation(String contactPersonDesignation) {
        this.contactPersonDesignation = contactPersonDesignation;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getContactPersonNumberTwo() {
        return contactPersonNumberTwo;
    }

    public void setContactPersonNumberTwo(String contactPersonNumberTwo) {
        this.contactPersonNumberTwo = contactPersonNumberTwo;
    }

    public String getContactPersonNumberOne() {
        return contactPersonNumberOne;
    }

    public void setContactPersonNumberOne(String contactPersonNumberOne) {
        this.contactPersonNumberOne = contactPersonNumberOne;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getDataLoggingDate() {
        return dataLoggingDate;
    }

    public void setDataLoggingDate(String dataLoggingDate) {
        this.dataLoggingDate = dataLoggingDate;
    }

    public String getCommissioningDate() {
        return commissioningDate;
    }

    public void setCommissioningDate(String commissioningDate) {
        this.commissioningDate = commissioningDate;
    }

    public Double getPlantCapacity() {
        return plantCapacity;
    }

    public void setPlantCapacity(Double plantCapacity) {
        this.plantCapacity = plantCapacity;
    }

    public Integer getDeviceMacLength() {
        return deviceMacLength;
    }

    public void setDeviceMacLength(Integer deviceMacLength) {
        this.deviceMacLength = deviceMacLength;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Double getTariff() {
        return tariff;
    }

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
}
