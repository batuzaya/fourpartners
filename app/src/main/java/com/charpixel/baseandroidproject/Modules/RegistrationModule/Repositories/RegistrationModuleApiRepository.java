package com.charpixel.baseandroidproject.Modules.RegistrationModule.Repositories;


import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Facebook;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Google;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Login;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ApiModels.Register;
import com.charpixel.baseandroidproject.Modules.SplashScreen.AdminData;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;

import rx.Observable;


/**
 * Created by ashu on 10-03-2016.
 */
public interface RegistrationModuleApiRepository {

    Observable<BaseResponseData<Login.Response>> doLogin(Login.Request request);
    Observable<BaseResponseData<Register.Response>> doRegisterFromEmail(Register.Request params);
    Observable<BaseResponseData<Facebook.Response>> doFacebookLogin(Facebook.Request request);
    Observable<BaseResponseData<Google.Response>> doGoogleLogin(Google.Request request);
    Observable<BaseResponseData<NoDataResponse>> forgotPassword(String email);
    Observable<BaseResponseData<AdminData>> getAdminData();

   // Observable<BaseResponseData<OtpVerification.Response>> verifyOtp();
  //  Observable<BaseResponseData<AccessToken.Response>> accessTokenLogin();
   // Observable<BaseResponseData<EditProfile.Response>> updateProfile();

}
