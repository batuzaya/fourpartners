package com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule;

import android.content.Context;

import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ApiModels.Register;
import com.charpixel.baseandroidproject.common.BasePresenter;
import com.charpixel.baseandroidproject.common.BaseView;

/**
 * Created by ashu on 25-11-2016.
 */

public class LoginContract {
    public interface Presenter extends BasePresenter {
        void attemptLogin(String username, String password);
        void doFacebookLogin();
        void doGoogleLogin();

        void requestForRegister(Register.Request map);
    }

    public interface View extends BaseView {
        void navigateToForgotPasswordActivity();
        void navigateToDashboard();
        void loginFailed();
        void showSnackbar(String s);
        Context getContext();
        public void navigateToOtpVerification();
        public void navigateToFacebookDetails();
        public  void navigateToGoogleDetails();
    }
}
