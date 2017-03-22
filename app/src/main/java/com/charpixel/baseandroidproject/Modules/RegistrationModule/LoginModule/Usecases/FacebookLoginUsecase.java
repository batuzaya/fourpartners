package com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.Usecases;

import android.util.Log;

import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Facebook;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.Repositories.RegistrationModuleApiRepository;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.UsecaseBase;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by ashu on 24-07-2016.
 */
public class FacebookLoginUsecase extends UsecaseBase<BaseResponseData<Facebook.Response>> {

private RegistrationModuleApiRepository registrationModuleApiRepository;

        @Inject
        Facebook.Request request;



@Inject
public FacebookLoginUsecase( RegistrationModuleApiRepository repository) {

        registrationModuleApiRepository = repository;


        }

@Override
public Observable<BaseResponseData<Facebook.Response>> buildObservable() {

        Log.v("build obsevable","observable");

        return registrationModuleApiRepository.doFacebookLogin(request);
        }
        }
