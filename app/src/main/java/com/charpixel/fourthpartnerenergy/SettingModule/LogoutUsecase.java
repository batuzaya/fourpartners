package com.charpixel.fourthpartnerenergy.SettingModule;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;
import com.charpixel.baseandroidproject.common.UsecaseBase;
import com.charpixel.fourthpartnerenergy.Repositories.DataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ashu on 21-12-2016.
 */

public class LogoutUsecase  extends UsecaseBase<BaseResponseData<NoDataResponse>> {

    private DataRepository dataRepository;


    @Inject
    public LogoutUsecase (DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @Override
    public Observable<BaseResponseData<NoDataResponse>> buildObservable() {
        return dataRepository.logout();
    }
}
