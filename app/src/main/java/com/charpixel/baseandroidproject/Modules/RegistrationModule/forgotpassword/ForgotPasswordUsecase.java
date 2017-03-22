package com.charpixel.baseandroidproject.Modules.RegistrationModule.forgotpassword;

import com.charpixel.baseandroidproject.Modules.RegistrationModule.Repositories.RegistrationModuleApiRepository;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;
import com.charpixel.baseandroidproject.common.UsecaseBase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ashu on 01-04-2016.
 */
public class ForgotPasswordUsecase extends UsecaseBase<BaseResponseData<NoDataResponse>> {

    private RegistrationModuleApiRepository registrationModuleApiRepository;

    @Inject
    ForgotPasswordRequest request;

    @Inject
    public ForgotPasswordUsecase( RegistrationModuleApiRepository repository) {

        registrationModuleApiRepository = repository;
    }


    @Override
    public Observable<BaseResponseData<NoDataResponse>> buildObservable() {
        return registrationModuleApiRepository.forgotPassword(request.getEmail());
    }
}
