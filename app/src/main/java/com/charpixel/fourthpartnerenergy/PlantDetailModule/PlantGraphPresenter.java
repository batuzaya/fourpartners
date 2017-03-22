package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.BaseView;
import com.charpixel.fourthpartnerenergy.Models.CurrentPlant;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType1;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType2;

import javax.inject.Inject;

/**
 * Created by ashu on 24-12-2016.
 */

public class PlantGraphPresenter implements PlantGraphContract.Presenter {

    PlantGraphContract.View view;

    GetPlantGraphType1UseCase getPlantGraphType1UseCase;
    GetPlantGraphType2UseCase getPlantGraphType2UseCase;

    @Inject
    CurrentPlant currentPant;



    private String TAG = this.getClass().getSimpleName();

    @Inject
    PlantGraphPresenter(GetPlantGraphType1UseCase getPlantGraphType1UseCase ,GetPlantGraphType2UseCase getPlantGraphType2UseCase ){

        this.getPlantGraphType1UseCase = getPlantGraphType1UseCase;
        this.getPlantGraphType2UseCase = getPlantGraphType2UseCase;

    }


    @Override
    public void getPlantEnergyData(String date, String type) {

        getPlantGraphType1UseCase.setData(currentPant.getPlant().getId(),date,type);

        getPlantGraphType1UseCase.execute().subscribe(this::onPlantEnergyDataSuccess ,this::onError);
    }

    private void onPlantEnergyDataSuccess(BaseResponseData<GraphDataType1> graphDataType1BaseResponseData) {

        view.refreshPlantEnergyData(graphDataType1BaseResponseData.getData());
    }

    private void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void getPlantIrridationData(String date) {

        getPlantGraphType2UseCase.setData(currentPant.getPlant().getId(),date);

        getPlantGraphType2UseCase.execute().subscribe(this::onPlantIrridationSuccess,this::onError);
    }

    private void onPlantIrridationSuccess(BaseResponseData<GraphDataType2> graphDataType2BaseResponseData) {
        view.refreshPlantIrridationData(graphDataType2BaseResponseData.getData());
    }

    @Override
    public void attachView(BaseView v) {

        this.view = (PlantGraphContract.View)v;

    }
}
