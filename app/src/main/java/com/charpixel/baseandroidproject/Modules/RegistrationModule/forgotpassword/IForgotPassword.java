package com.charpixel.baseandroidproject.Modules.RegistrationModule.forgotpassword;


import com.charpixel.baseandroidproject.common.BasePresenter;

/**
 * Created by ashu on 01-04-2016.
 */
public interface IForgotPassword extends BasePresenter {
    void attemptForgotPassword(String username, String password);
}
