package com.charpixel.fourthpartnerenergy.Models;

/**
 * Created by ashu on 20-12-2016.
 */
public class PlantDetailApi {
    private static PlantDetailApi ourInstance = new PlantDetailApi();

    public static PlantDetailApi getInstance() {
        return ourInstance;
    }

    private String plantId;


    private PlantDetailApi() {


    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

}
