package com.charpixel.fourthpartnerenergy.SettingModule;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.AppSession;
import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegistationActivity;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.Utilities.PrefUtils;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.databinding.ProfileLayoutBinding;

import javax.inject.Inject;

/**
 * Created by ashu on 22-12-2016.
 */

public class ProfileFragment extends BaseFragment implements SettingContract.View {

    ProfileLayoutBinding binding;

    @Inject
    SettingPresenter settingPresenter;

    @Inject
    PrefUtils utils;


    @Inject
    AppSession appSession;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.profile_layout,container,false);

        settingPresenter.attachView(this);
        binding.logoutButton.setOnClickListener(view -> {
           settingPresenter.logout();
        });
        binding.setAdminData(appSession.getAdminData());


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void logout() {
        utils.logout(getActivity());
        getActivity().finish();
        Intent i = new Intent(getActivity(), RegistationActivity.class);
        startActivity(i);
        //showSnackbar("Sorry , Your session has been expired. Please try again.");

    }


    @Override
    public void showSnackbar(String s) {

    }

    @Override
    public void showLoader(boolean isShowLoader) {

    }
}
