package com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.text.TextWatcher;

import com.charpixel.baseandroidproject.dataBindingUtils.TextWatcherAdapter;

import java.util.Objects;



/**
 * Created by ashu on 09-03-2016.
 */
public class BLoginView extends BaseObservable {
    private String email;
    private String password;

    public BLoginView(String email , String password) {
        this.email = email;
        this.password = password;
    }
    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(com.charpixel.baseandroidproject.BR.email);
    }
    @Bindable
    public String getPassword() {
        return password;


    }



    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(com.charpixel.baseandroidproject.BR.password);
    }

    public TextWatcher watcher = new TextWatcherAdapter() {
        @Override
        public void afterTextChanged(Editable s) {
            if (!Objects.equals(email, s.toString())) {
               email = s.toString();
            }
        }
    };
}
