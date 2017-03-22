package com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.Usecases;

import android.util.Log;

import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Google;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.Repositories.RegistrationModuleApiRepository;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.UsecaseBase;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by ashu on 24-07-2016.
 */
public class GoogleLoginUsecase extends UsecaseBase<BaseResponseData<Google.Response>> {

    private RegistrationModuleApiRepository registrationModuleApiRepository;
    @Inject
    Google.Request request;



    @Inject
    public GoogleLoginUsecase( RegistrationModuleApiRepository repository) {

        registrationModuleApiRepository = repository;

    }

    @Override
    public Observable<BaseResponseData<Google.Response>> buildObservable() {

        Log.v("build obsevable","observable");

        return registrationModuleApiRepository.doGoogleLogin(request);
    }
}