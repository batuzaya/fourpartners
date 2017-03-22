package com.charpixel.baseandroidproject.Modules.RegistrationModule.Repositories;

import com.charpixel.baseandroidproject.Constants;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Facebook;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Google;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Login;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ApiModels.Register;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.Services.RegistrationModuleApiServices;
import com.charpixel.baseandroidproject.Modules.SplashScreen.AdminData;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by ashu on 10-03-2016.
 */

public class RegisterModuleRestDataSource implements RegistrationModuleApiRepository {
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private RegistrationModuleApiServices registrationModuleApiServices;

    @Inject
    public RegisterModuleRestDataSource(RegistrationModuleApiServices registrationModuleApiServices,
                              @Named("ui_thread") Scheduler uiThread,
                              @Named("executor_thread") Scheduler executorThread)
    {
        this.mUiThread = uiThread;
        this.mExecutorThread = executorThread;
        this.registrationModuleApiServices = registrationModuleApiServices;

    }

    @Override
    public Observable<BaseResponseData<Login.Response>> doLogin(Login.Request request) {

        return registrationModuleApiServices.login(request).subscribeOn(mExecutorThread)
                .observeOn(mUiThread);
    }

    @Override
    public Observable<BaseResponseData<Register.Response>> doRegisterFromEmail(Register.Request request) {

        return registrationModuleApiServices.register(request).subscribeOn(mExecutorThread)
                .observeOn(mUiThread);
    }

    @Override
    public Observable<BaseResponseData<Facebook.Response>> doFacebookLogin(Facebook.Request request) {
        return registrationModuleApiServices.facebookLogin(request).subscribeOn(mExecutorThread)
                .observeOn(mUiThread);
    }

    @Override
    public Observable<BaseResponseData<Google.Response>> doGoogleLogin(Google.Request request) {
        return registrationModuleApiServices.googleLogin(request).subscribeOn(mExecutorThread)
                .observeOn(mUiThread);
    }

    @Override
    public Observable<BaseResponseData<NoDataResponse>> forgotPassword(String email) {
        return registrationModuleApiServices.forgotPassword(email).subscribeOn(mExecutorThread)
                .observeOn(mUiThread);
    }

    @Override
    public Observable<BaseResponseData<AdminData>> getAdminData() {
        return registrationModuleApiServices.getAdminData(Constants.getAccessToken(),Constants.USER_ID_VALUE,"{}","{\"email\":\"1\",\"fullName\":\"1\",\"phone\":\"1\",\"designation\":\"1\",\"company\":\"1\"}","{}").subscribeOn(mExecutorThread)
                .observeOn(mUiThread);
    }
}
