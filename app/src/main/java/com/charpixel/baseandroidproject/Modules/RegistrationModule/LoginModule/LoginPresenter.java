package com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule;

import android.util.Log;

import com.charpixel.baseandroidproject.Constants;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Facebook;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Google;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Login;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.Usecases.FacebookLoginUsecase;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.Usecases.GoogleLoginUsecase;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ApiModels.Register;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.Usecases.RegisterUsecase;
import com.charpixel.baseandroidproject.Utilities.PrefUtils;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.BaseView;
import com.charpixel.baseandroidproject.common.BlankData;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;

/**
 * Created by ashu on 08-03-2016.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginUserUsecase mLoginUserUsecase;

    private Subscription mLoginSubscription;

    private LoginContract.View view;


    @Inject
    PrefUtils utils;

    @Inject
    GoogleLoginUsecase googleLoginUsecase;

    @Inject
    FacebookLoginUsecase facebookLoginUsecase;

    @Inject
    RegisterUsecase registerUsecase;

    @Inject
    public LoginPresenter(LoginUserUsecase loginUserUsecase  ){
        mLoginUserUsecase = loginUserUsecase;


    }




    public void requestForLogin(){

        Log.v("login Request","here");

        mLoginSubscription = mLoginUserUsecase.execute().subscribe(this::onLoginSuccessful, this::onLoginError);

    }


    @Override
    public void doFacebookLogin() {
        facebookLoginUsecase.execute().subscribe(this::onFacebookLoginSuccessful, this::onLoginError);
    }

    @Override
    public void doGoogleLogin() {
        googleLoginUsecase.execute().subscribe(this::onGoogleLoginSuccessful, this::onLoginError);
    }

    @Override
    public void requestForRegister(Register.Request map) {


        registerUsecase.setRequest(map);
        registerUsecase.execute().subscribe(this::onRegisterSuccessful,this::onLoginError);

    }

    private void onRegisterSuccessful(BaseResponseData<Register.Response> response) {

        saveLoginData(response.getData().getToken() , response.getData().getId() );

    }

    private void onGoogleLoginSuccessful(BaseResponseData<Google.Response> response) {
        Log.e("Response message", response.getMessage());

        utils.saveAccessToken(view.getContext(),response.getData().getAccessToken());

        Constants.ACCESS_TOKEN_VALUE = response.getData().getAccessToken();
        if( response.getFlag() == 210){



            view.navigateToOtpVerification();
        }else if( response.getFlag() == 200){
            view.navigateToDashboard();
        } else if (response.getFlag() == 240){

            view.navigateToGoogleDetails();

        }

    }

    private void onFacebookLoginSuccessful(BaseResponseData<Facebook.Response> response) {
        Log.e("Response message", response.getMessage());

        utils.saveAccessToken(view.getContext(),response.getData().getAccessToken());

        Constants.ACCESS_TOKEN_VALUE = response.getData().getAccessToken();
        if( response.getFlag() == 210){



            view.navigateToOtpVerification();
        }else if( response.getFlag() == 200){
            view.navigateToDashboard();
        } else if (response.getFlag() == 240){

            view.navigateToFacebookDetails();

        }
    }



    private void onLoginError(Throwable e){
        if (e instanceof HttpException) {



            Gson gson = new Gson();
            try {
                BaseResponseData<BlankData> rideRequestGcmModel = gson.fromJson(((HttpException) e).response().errorBody().string(), BaseResponseData.class);


                Log.v("err",rideRequestGcmModel.getError());
                view.showSnackbar(rideRequestGcmModel.getMessage());

            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }

    }



    private void onLoginSuccessful(BaseResponseData<Login.Response> response){
        Log.e("Response message", response.getMessage());
        view.showSnackbar(response.getMessage());

        saveLoginData(response.getData().getToken() , response.getData().getId() );
        view.navigateToDashboard();
    }

    @Override
    public void attemptLogin(String username, String password) {

    }


    void saveLoginData(String token , String userId ){
        utils.saveAccessToken(view.getContext(),token);
        utils.saveUserId(view.getContext(),userId);


        Constants.USER_ID_VALUE = userId;

        Constants.ACCESS_TOKEN_VALUE = token;


    }



    @Override
    public void attachView(BaseView v) {
        view = (LoginContract.View) v;

    }


}
