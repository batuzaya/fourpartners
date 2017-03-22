
package com.charpixel.fourthpartnerenergy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meter {

    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("model")
    @Expose
    private Integer model;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

}
