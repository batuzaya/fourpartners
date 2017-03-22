package com.charpixel.baseandroidproject.Modules.SplashScreen;

import com.charpixel.baseandroidproject.AppSession;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.BaseView;

import javax.inject.Inject;

/**
 * Created by ashu on 13-12-2016.
 */

public class SplashScreenPesenter implements SplashScreenContract.Presenter  {

    GetAdminDataUsecase getAdminDataUsecase;

    @Inject
    AppSession appSession;

    @Inject
    SplashScreenPesenter(GetAdminDataUsecase getAdminDataUsecase){

        this.getAdminDataUsecase = getAdminDataUsecase;
    }



    @Override
    public void attachView(BaseView v) {

    }

    @Override
    public void getAdminDetail() {

        getAdminDataUsecase.execute().subscribe(this::onGetAdminDataSuccessful, this::onError );

    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();

    }

    private void onGetAdminDataSuccessful(BaseResponseData<AdminData> adminDataBaseResponseData) {

        appSession.setAdminData(adminDataBaseResponseData.getData());

    }
}
