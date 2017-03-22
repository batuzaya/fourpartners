package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.UsecaseBase;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType1;
import com.charpixel.fourthpartnerenergy.Repositories.DataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ashu on 24-12-2016.
 */

public class GetPlantGraphType1UseCase extends UsecaseBase<BaseResponseData<GraphDataType1>> {


    DataRepository dataRepository;
    private String plantId, date, plantType;


    public void setData(String plantId, String date, String plantType) {


        this.plantId = plantId;
        this.date = date;
        this.plantType = plantType;

    }

    @Inject
    public GetPlantGraphType1UseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public Observable<BaseResponseData<GraphDataType1>> buildObservable() {
        return dataRepository.getEnergyPrGraph(plantId, date, plantType);
    }
}
