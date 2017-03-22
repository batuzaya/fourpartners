package com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ViewModels;

/**
 * Created by ashu on 09-03-2016.
 */

import android.content.res.Resources;
import android.util.Patterns;

import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.dataBindingUtils.BindableBoolean;
import com.charpixel.baseandroidproject.dataBindingUtils.BindableString;

import org.parceler.Parcel;



@Parcel
public class LoginInfo {
    public BindableString email = new BindableString();
    public BindableString password = new BindableString();
    public BindableString emailError = new BindableString();
    public BindableString passwordError = new BindableString();

    public BindableBoolean existingUser = new BindableBoolean();

    public boolean loginExecuted;

    public void reset() {
        email.set(null);
        password.set(null);
        emailError.set(null);
        passwordError.set(null);
        loginExecuted = false;
    }

    public boolean validate(Resources res) {
        if (!loginExecuted) {
            return true;
        }
        int emailErrorRes = 0;
        int passwordErrorRes = 0;
        if (email.get().isEmpty()) {
            emailErrorRes = R.string.mandatory_field;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {
                emailErrorRes = R.string.invalid_email;
            }
        }
        if ( password.get().isEmpty()) {
            passwordErrorRes = R.string.mandatory_field;
        }
        emailError.set(emailErrorRes != 0 ? res.getString(emailErrorRes) : null);
        passwordError.set(passwordErrorRes != 0 ? res.getString(passwordErrorRes) : null);
        return emailErrorRes == 0 && passwordErrorRes == 0;
    }
    public boolean validateEmail (Resources res) {
        if (!loginExecuted) {
            return true;
        }
        int emailErrorRes = 0;
        int passwordErrorRes = 0;
        if (email.get().isEmpty()) {
            emailErrorRes = R.string.mandatory_field;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {
                emailErrorRes = R.string.invalid_email;
            }
        }

        emailError.set(emailErrorRes != 0 ? res.getString(emailErrorRes) : null);
//        passwordError.set(passwordErrorRes != 0 ? res.getString(passwordErrorRes) : null);
        return emailErrorRes == 0 ;
    }

}