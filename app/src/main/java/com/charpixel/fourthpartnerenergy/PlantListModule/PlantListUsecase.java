package com.charpixel.fourthpartnerenergy.PlantListModule;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.UsecaseBase;
import com.charpixel.fourthpartnerenergy.Models.PlantListApi;
import com.charpixel.fourthpartnerenergy.Repositories.DataRepository;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ashu on 13-12-2016.
 */

public class PlantListUsecase  extends UsecaseBase<BaseResponseData<ArrayList<Plant>>> {

    private DataRepository dataRepository;

    private PlantListApi.Request request = new PlantListApi.Request();

    public PlantListApi.Request getRequest() {
        return request;
    }

    Date today = new Date();

    public void setRequest(PlantListApi.Request request) {
        this.request = request;
    }

    @Inject
    public PlantListUsecase (DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @Override
    public Observable<BaseResponseData<ArrayList<Plant>>> buildObservable() {

        if(request.getDate() == null){
            request.setDate(PlantListPresenter.formatter.format(today));
        }
        return dataRepository.getPlants(request.getSearch(),request.getPage(),request.getCount(),request.getDate(),request.getSortKey(),request.getSortOrder());
    }
}
