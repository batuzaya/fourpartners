package com.charpixel.baseandroidproject.Modules.RegistrationModule.forgotpassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ViewModels.LoginInfo;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.dataBindingUtils.TextWatcherAdapter;
import com.charpixel.baseandroidproject.databinding.FragmentForgotPasswordBinding;

import org.parceler.Parcels;

import javax.inject.Inject;



/**
 * A placeholder fragment containing a simple View.
 */
public class ForgotpasswordActivityFragment extends Fragment implements IForgotPasswordBaseView {


    @Inject
    ForgotPasswordRequest forgotPasswordRequest;
    @Inject
    ForgotPasswordPresenter forgotPasswordPresenter;
    private LoginInfo loginInfo;
    public static final String LOGIN_INFO = "loginInfo";

    public ForgotpasswordActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentForgotPasswordBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false);

        if (savedInstanceState == null) {
            loginInfo = new LoginInfo();


        } else {
            loginInfo = Parcels.unwrap(savedInstanceState.getParcelable(LOGIN_INFO));
        }
        binding.setLoginInfo(loginInfo);
        TextWatcherAdapter watcher = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                loginInfo.validate(getResources());
            }
        };

        binding.forgotPassword.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                v.startAnimation(shake);
                requestForgotPassword();
            }
        });






        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        forgotPasswordPresenter.attachView(this);
    }
    public void requestForgotPassword(){
        Log.v("login request", "here");
        forgotPasswordRequest.setEmail(loginInfo.email.get());

        forgotPasswordPresenter.forgotPassword();

    }


    @Override
    public void showSnackbar(String s) {
        Snackbar.make(getView(), s, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
    }
}

