package com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.Usecases;

import android.util.Log;

import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Login;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.Repositories.RegistrationModuleApiRepository;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.UsecaseBase;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by ashu on 09-03-2016.
 */
public class LoginUserUsecase extends UsecaseBase<BaseResponseData<Login.Response>> {

    private RegistrationModuleApiRepository registrationModuleApiRepository;
    @Inject
    Login.Request request;



    @Inject
    public LoginUserUsecase(RegistrationModuleApiRepository repository) {

        registrationModuleApiRepository = repository;


    }

    @Override
    public Observable<BaseResponseData<Login.Response>> buildObservable() {

        Log.v("build obsevable","observable");

        return registrationModuleApiRepository.doLogin(request);
    }
}
