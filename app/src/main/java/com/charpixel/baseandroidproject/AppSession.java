package com.charpixel.baseandroidproject;

import com.charpixel.baseandroidproject.Modules.SplashScreen.AdminData;
import com.charpixel.fourthpartnerenergy.PlantListModule.Plant;

import java.util.ArrayList;

/**
 * Created by ashu on 22-12-2016.
 */

public class AppSession {

    private AdminData adminData;

    private ArrayList<Plant> plantArrayList;

    public ArrayList<Plant> getPlantArrayList() {
        return plantArrayList;
    }

    public void setPlantArrayList(ArrayList<Plant> plantArrayList) {
        this.plantArrayList = plantArrayList;
    }

    public AdminData getAdminData() {
        return adminData;
    }

    public void setAdminData(AdminData adminData) {
        this.adminData = adminData;
    }
}
