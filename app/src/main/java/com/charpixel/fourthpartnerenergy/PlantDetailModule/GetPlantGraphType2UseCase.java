package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.UsecaseBase;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType2;
import com.charpixel.fourthpartnerenergy.Repositories.DataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ashu on 24-12-2016.
 */

public class GetPlantGraphType2UseCase  extends UsecaseBase<BaseResponseData<GraphDataType2>> {


    DataRepository dataRepository;
    private String plantId, date;


    public void setData(String plantId, String date) {


        this.plantId = plantId;
        this.date = date;


    }

    @Inject
    public GetPlantGraphType2UseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public Observable<BaseResponseData<GraphDataType2>> buildObservable() {
        return dataRepository.getInverterIrridationGraph(plantId, date);
    }
}

