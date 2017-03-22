package com.charpixel.baseandroidproject.Modules.RegistrationModule.forgotpassword;

import android.util.Log;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.BaseView;
import com.charpixel.baseandroidproject.common.BlankData;
import com.charpixel.baseandroidproject.common.NoDataResponse;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;

/**
 * Created by ashu on 01-04-2016.
 */
public class ForgotPasswordPresenter implements IForgotPassword {

    private ForgotPasswordUsecase mForgotPasswordUsecase;
    private Subscription mForgotPasswordSubscription;
    private IForgotPasswordBaseView iForgotPasswordView;


@Inject
ForgotPasswordPresenter(ForgotPasswordUsecase forgotPasswordUsecase){
mForgotPasswordUsecase = forgotPasswordUsecase;
    }

    public  void forgotPassword(){
        Log.v("forgot password request", "here");
        mForgotPasswordSubscription = mForgotPasswordUsecase.execute().subscribe(this::onForgotPasswordSuccessful, this::onLoginError);
    }

    private void onLoginError(Throwable e){

        if (e instanceof HttpException) {



            Gson gson = new Gson();
            try {
                BaseResponseData<BlankData> rideRequestGcmModel = gson.fromJson(((HttpException) e).response().errorBody().string(), BaseResponseData.class);

                Log.v("err",rideRequestGcmModel.getError());
                iForgotPasswordView.showSnackbar(rideRequestGcmModel.getMessage());

            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }


    }
    private  void onForgotPasswordSuccessful(BaseResponseData<NoDataResponse> response){
        Log.e("response message", response.getMessage());
        iForgotPasswordView.showSnackbar(response.getMessage());
    }

    @Override
    public void attemptForgotPassword(String username, String password) {

    }


    @Override
    public void attachView(BaseView v) {
        iForgotPasswordView = (IForgotPasswordBaseView)v;

    }

}
