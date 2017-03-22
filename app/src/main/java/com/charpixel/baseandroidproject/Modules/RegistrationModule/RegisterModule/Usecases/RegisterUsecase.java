package com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.Usecases;

import android.util.Log;

import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ApiModels.Register;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.Repositories.RegistrationModuleApiRepository;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.UsecaseBase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ashu on 25-11-2016.
 */

public class RegisterUsecase extends UsecaseBase<BaseResponseData<Register.Response>> {

    private RegistrationModuleApiRepository registrationModuleApiRepository;



    private Register.Request request;




    @Inject
    public RegisterUsecase( RegistrationModuleApiRepository repository) {

        registrationModuleApiRepository = repository;

    }

    public Register.Request getRequest() {
        return request;
    }

    public void setRequest(Register.Request request) {
        this.request = request;
    }

    @Override
    public Observable<BaseResponseData<Register.Response>> buildObservable() {

        Log.v("build obsevable","observable");

        return registrationModuleApiRepository.doRegisterFromEmail(request);
    }
}
