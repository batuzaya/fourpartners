package com.charpixel.fourthpartnerenergy.SettingModule;

import com.charpixel.baseandroidproject.common.BasePresenter;
import com.charpixel.baseandroidproject.common.BaseView;

/**
 * Created by ashu on 21-12-2016.
 */

public class SettingContract {
    public interface View extends BaseView {


       void showSnackbar(String s);
        void showLoader(boolean isShowLoader);
        void logout();
    }

    interface Presenter extends BasePresenter {

        void logout();
        void changePassword(String newPassword ,String oldPassword);

    }
}
