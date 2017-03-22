package com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ViewModels;

import android.content.res.Resources;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.dataBindingUtils.BindableBoolean;
import com.charpixel.baseandroidproject.dataBindingUtils.BindableString;


/**
 * Created by ashu on 25-05-2016.
 */

public class RegisterInfo {
    public BindableString email = new BindableString();
    public BindableString name = new BindableString();
    public BindableString phoneNo = new BindableString();
    public BindableString password = new BindableString();
    public BindableString confirm_password = new BindableString();
    public BindableString emailError = new BindableString();
    public BindableString passwordError = new BindableString();
    public BindableString counteryCode = new BindableString();
    public BindableBoolean existingUser = new BindableBoolean();

    public boolean loginExecuted;

    public void reset() {

        email.set(null);
        password.set(null);
        confirm_password.set(null);
        emailError.set(null);
        passwordError.set(null);
        loginExecuted = false;
        phoneNo.set(null);
        name.set(null);
    }

    public String validate(Resources res) {

     //   if (!loginExecuted) {
       //     return true;
       // }
        Log.e("vishal","\nEmail:" + email.get());

        int emailErrorRes = 0;
        int passwordErrorRes = 0;
        int nameErrorRes=0;

        //checking name for blank
        if(name.get().isEmpty()){
            return  res.getString(R.string.name_mandatory);
        }

        //checking email
        if (email.get().isEmpty()) {
            emailErrorRes =R.string.email_mandatory;
            return res.getString(emailErrorRes);
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {
                emailErrorRes = R.string.invalid_email;
                return res.getString(emailErrorRes);
            }
        }


        //checking password
        if (password.get().isEmpty()) {
            passwordErrorRes = R.string.password_mandatory;
            return res.getString(passwordErrorRes);
        }
        Log.e("vishal","\nconfirm password " + confirm_password.get());

        //checking confirm password
        if (confirm_password.get().isEmpty()) {
            passwordErrorRes = R.string.confirm_password_mandatory;
            return res.getString(passwordErrorRes);
        }


        if (!password.get().equals(confirm_password.get())) {

            Log.e("vishal",
                    "\npassword:" + password.get()+
                    "\nconfirm password:" + confirm_password.get()
            );
            passwordErrorRes = R.string.password_does_not_match;
            return res.getString(passwordErrorRes);
        }

        Log.e("vishal","email:" + email.get()+
                "\npassword:" + password.get()+
                "\nconfirm password:" + confirm_password.get()+
                "\nErrorpass:" + passwordErrorRes+
                "\nErroremail:" + emailErrorRes);

        emailError.set(emailErrorRes != 0 ? res.getString(emailErrorRes) : null);
        passwordError.set(passwordErrorRes != 0 ? res.getString(passwordErrorRes) : null);
        return null;
    }


    public boolean validateEmail(Resources res) {
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
        return emailErrorRes == 0;
    }

}