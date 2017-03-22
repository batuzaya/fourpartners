package com.charpixel.fourthpartnerenergy.SettingModule;

import android.util.Log;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.BaseView;
import com.charpixel.baseandroidproject.common.BlankData;
import com.charpixel.baseandroidproject.common.NoDataResponse;
import com.charpixel.fourthpartnerenergy.Models.ChangePasswordApi;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by ashu on 21-12-2016.
 */

public class SettingPresenter implements SettingContract.Presenter {


    SettingContract.View view;

    ChangePasswordUsecase changePasswordUsecase;
    LogoutUsecase logoutUsecase;


    @Inject
    public SettingPresenter(ChangePasswordUsecase changePasswordUsecase , LogoutUsecase logoutUsecase){
        this.changePasswordUsecase = changePasswordUsecase;
        this.logoutUsecase = logoutUsecase;

    }

    @Override
    public void logout() {

        logoutUsecase.execute().subscribe(this::onLogoutSuccess,this::onError );

    }

    @Override
    public void changePassword(String newPassword, String oldPassword) {

        view.showLoader(true);

        ChangePasswordApi.Request request = new ChangePasswordApi.Request();
        request.newPassword = newPassword;
        request.password = oldPassword;

        changePasswordUsecase.setRequest(request);

        changePasswordUsecase.execute().subscribe(this::onChangePasswordSuccess,this::onError);

    }

    private void onChangePasswordSuccess(BaseResponseData<NoDataResponse> noDataResponseBaseResponseData) {

        view.showLoader(false);

        view.showSnackbar("Your password has been updated...");



    }

    private void onError(Throwable e) {

        Log.e("nw error",e.toString());

        view.showLoader(false);

        if (e instanceof HttpException) {



            Gson gson = new Gson();
            try {
                BaseResponseData<BlankData> rideRequestGcmModel = gson.fromJson(((HttpException) e).response().errorBody().string(), BaseResponseData.class);


                if(rideRequestGcmModel.getStatusCode() == 401)
                {
                    view.logout();
                }

                Log.v("err",rideRequestGcmModel.getError());
                view.showSnackbar(rideRequestGcmModel.getMessage());

            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }



    }

    private void onLogoutSuccess(BaseResponseData<NoDataResponse> noDataResponseBaseResponseData) {

        view.logout();
    }



    @Override
    public void attachView(BaseView v) {

        this.view = (SettingContract.View)v;
    }
}
