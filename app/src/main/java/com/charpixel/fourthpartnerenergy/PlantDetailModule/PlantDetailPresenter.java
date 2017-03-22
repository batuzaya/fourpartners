package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import android.icu.text.DecimalFormat;
import android.util.Log;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.BaseView;
import com.charpixel.fourthpartnerenergy.Models.CurrentPlant;
import com.charpixel.fourthpartnerenergy.Models.PlantDetail;

import javax.inject.Inject;

/**
 * Created by ashu on 20-12-2016.
 */

public class PlantDetailPresenter implements PlantDetailContract.Presenter {


    String TAG = "PlantDetailPresenter";

    PlantDetailContract.View view;

    PlantDetailUsecase plantDetailUsecase;

    @Inject
    CurrentPlant currentPlant;


    @Inject
    public PlantDetailPresenter(PlantDetailUsecase plantDetailUsecase){
        this.plantDetailUsecase = plantDetailUsecase;


    }

    @Override
    public void getPlantDetail(String id) {
        plantDetailUsecase.setPlantId(id);
        plantDetailUsecase.execute().subscribe(this::onPlantDetailSuccess,this::OnError);

    }

    private void OnError(Throwable throwable) {
    }

    private void onPlantDetailSuccess(BaseResponseData<PlantDetail> plantDetailBaseResponseData)  {

        PlantDetail plant = plantDetailBaseResponseData.getData();

        Log.v(TAG,"validating "+","+Double.parseDouble(plant.getSlideDown().getInvertersData().get(0).getInverters().get(1).getPowerNow())+","+plant.getPlantCapacity());


        plant.setCommissioningDate(plant.getDate(plant.getCommissioningDate()));
        plant.setDataLoggingDate(plant.getDate(plant.getDataLoggingDate()));

        Double powerPercent = Double.parseDouble(plant.getSlideDown().getInvertersData().get(0).getInverters().get(1).getPowerNow()) / plant.getPlantCapacity();


try{
    plant.setPowerPercentString(String.format("%.2f",powerPercent*100)+" %");
}
catch (Exception e){
    e.printStackTrace();
}

        plant.setPowerPercentFraction(powerPercent.floatValue());



        Log.v(TAG,"validating2");
        currentPlant.setPlantDetail(plant);
        view.refresh();
    }


    @Override
    public void attachView(BaseView v) {
        this.view = (PlantDetailContract.View)v;

    }
}
