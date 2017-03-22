
package com.charpixel.fourthpartnerenergy.Models;


import android.util.Log;

import com.charpixel.baseandroidproject.dataBindingUtils.BaseObservable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PlantDetail extends BaseObservable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("generationGuaranteed")
    @Expose
    private String generationGuaranteed;
    @SerializedName("siteInchargeInternal")
    @Expose
    private String siteInchargeInternal;
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
    private String contactPersonEmail;
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
    private Integer plantCapacity;
    @SerializedName("deviceMacLength")
    @Expose
    private Integer deviceMacLength;
    @SerializedName("location")
    @Expose
    private ArrayList<String> location = null;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("tariff")
    @Expose
    private String tariff;
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
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("inverterEnergyCalculation")
    @Expose
    private String inverterEnergyCalculation;
    @SerializedName("deviceMac")
    @Expose
    private ArrayList<DeviceMac> deviceMac = null;
    @SerializedName("plantStatus")
    @Expose
    private Integer plantStatus;
    @SerializedName("slideDown")
    @Expose
    private SlideDown slideDown;
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
    private String kWhKWp;


    private String powerPercentString;
    private Float powerPercentFraction;


    public String getPowerPercentString() {



        return powerPercentString;
    }

    public void setPowerPercentString(String powerPercentString) {
        this.powerPercentString = powerPercentString;
    }

    public Float getPowerPercentFraction() {
        return powerPercentFraction;
    }

    public void setPowerPercentFraction(Float powerPercentFraction) {
        this.powerPercentFraction = powerPercentFraction;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getGenerationGuaranteed() {
        return generationGuaranteed;
    }

    public void setGenerationGuaranteed(String generationGuaranteed) {
        this.generationGuaranteed = generationGuaranteed;
    }

    public String getSiteInchargeInternal() {
        return siteInchargeInternal;
    }

    public void setSiteInchargeInternal(String siteInchargeInternal) {
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

    public String getkWhKWp() {
        return kWhKWp;
    }

    public void setkWhKWp(String kWhKWp) {
        this.kWhKWp = kWhKWp;
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

    public void setDataLoggingDate(String dataLoggingDate)  {

        this.dataLoggingDate = dataLoggingDate;
    }


     public  String getDateString(String newDate) throws ParseException {


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.SSSZ",Locale.ENGLISH);

            Date date = (Date) dateFormat.parse(newDate);

            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm", Locale.ENGLISH);

            String newDate3 = dateFormat2.format(date);

            Log.v("str",newDate3);

            return newDate3;

    }

    public String getDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date value = null;
        try {
            value = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yy");
        dateFormatter.setTimeZone(TimeZone.getDefault());
        String dt = dateFormatter.format(value);

        return dt;
    }

    public String getCommissioningDate() {
        return commissioningDate;
    }

    public void setCommissioningDate(String commissioningDate) {

        this.commissioningDate = commissioningDate;
    }

    public Integer getPlantCapacity() {
        return plantCapacity;
    }

    public void setPlantCapacity(Integer plantCapacity) {
        this.plantCapacity = plantCapacity;
    }

    public Integer getDeviceMacLength() {
        return deviceMacLength;
    }

    public void setDeviceMacLength(Integer deviceMacLength) {
        this.deviceMacLength = deviceMacLength;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
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

    public ArrayList<DeviceMac> getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(ArrayList<DeviceMac> deviceMac) {
        this.deviceMac = deviceMac;
    }

    public Integer getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(Integer plantStatus) {
        this.plantStatus = plantStatus;
    }

    public SlideDown getSlideDown() {
        return slideDown;
    }

    public void setSlideDown(SlideDown slideDown) {
        this.slideDown = slideDown;
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

    public String getKWhKWp() {
        return kWhKWp;
    }

    public void setKWhKWp(String kWhKWp) {
        this.kWhKWp = kWhKWp;
    }

}
