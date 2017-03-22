package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.UsecaseBase;
import com.charpixel.fourthpartnerenergy.Models.PlantDetail;
import com.charpixel.fourthpartnerenergy.Repositories.DataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ashu on 20-12-2016.
 */
public class PlantDetailUsecase extends UsecaseBase<BaseResponseData<PlantDetail>> {


    DataRepository dataRepository;
    private String plantId;

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    @Inject
    public PlantDetailUsecase(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @Override
    public Observable<BaseResponseData<PlantDetail>> buildObservable() {
        return dataRepository.getPlantDetail(plantId);
    }
}
