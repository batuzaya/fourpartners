package com.charpixel.fourthpartnerenergy.SettingModule;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.databinding.SecurityLayoutBinding;

import javax.inject.Inject;

/**
 * Created by ashu on 22-12-2016.
 */

public class SecurityFragment extends BaseFragment implements SettingContract.View {

    SecurityLayoutBinding binding;


    @Inject
    SettingPresenter presenter;



    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.security_layout,container,false);

        presenter.attachView(this);

        binding.submit.setOnClickListener(view -> {

            if(validate()){
                presenter.changePassword(binding.newPassword.getText().toString(),binding.oldPassword.getText().toString());
            }

        });

        return binding.getRoot();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    boolean validate(){
        if(binding.newPassword.getText().toString().isEmpty())
        {
            showSnackbar("New Password is Empty");
            return false;
        }
        if(binding.oldPassword.getText().toString().isEmpty())
        {
            showSnackbar("Old Password is Empty");
            return false;
        }
        if(binding.confirmPassword.getText().toString().isEmpty())
        {
            showSnackbar("Confirm Password is Empty");
            return false;
        }

        if(binding.newPassword.getText().toString().equals(binding.confirmPassword.getText().toString()))
        {
            return true;
        }else {
            showSnackbar("Confirm does not matched.");
            return false;
        }
    }


    public void showSnackbar(String s) {

        Snackbar.make(getView(), s, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }




    @Override
    public void showLoader(boolean isShowLoader) {
        if(isShowLoader){
            binding.progress.setVisibility(View.VISIBLE);
        }else {
            binding.progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void logout() {

    }



}
