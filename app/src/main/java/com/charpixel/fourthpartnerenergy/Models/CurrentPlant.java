package com.charpixel.fourthpartnerenergy.Models;

import com.charpixel.fourthpartnerenergy.PlantListModule.Plant;

/**
 * Created by ashu on 20-12-2016.
 */

public class CurrentPlant   {

   private Plant plant;
   private   PlantDetail plantDetail;



    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public PlantDetail getPlantDetail() {
        return plantDetail;
    }

    public void setPlantDetail(PlantDetail plantDetail) {
        this.plantDetail = plantDetail;
    }
}
