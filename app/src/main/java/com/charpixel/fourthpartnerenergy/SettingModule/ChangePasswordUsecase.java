package com.charpixel.fourthpartnerenergy.SettingModule;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;
import com.charpixel.baseandroidproject.common.UsecaseBase;
import com.charpixel.fourthpartnerenergy.Models.ChangePasswordApi;
import com.charpixel.fourthpartnerenergy.Repositories.DataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ashu on 21-12-2016.
 */

public class ChangePasswordUsecase  extends UsecaseBase<BaseResponseData<NoDataResponse>> {

    private DataRepository dataRepository;

    private ChangePasswordApi.Request request = new ChangePasswordApi.Request();

    public ChangePasswordApi.Request getRequest() {
        return request;
    }

    public void setRequest(ChangePasswordApi.Request request) {
        this.request = request;
    }

    @Inject
    public ChangePasswordUsecase (DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @Override
    public Observable<BaseResponseData<NoDataResponse>> buildObservable() {
        return dataRepository.changePassword(request);
    }
}