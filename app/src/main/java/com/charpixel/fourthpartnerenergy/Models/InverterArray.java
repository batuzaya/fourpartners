
package com.charpixel.fourthpartnerenergy.Models;

import java.util.List;

public class InverterArray {

    private String name;
    private List<Double> data = null;
    private String stacking;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public String getStacking() {
        return stacking;
    }

    public void setStacking(String stacking) {
        this.stacking = stacking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
