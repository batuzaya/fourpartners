package com.charpixel.baseandroidproject.Modules.SplashScreen;

import com.charpixel.baseandroidproject.common.BasePresenter;
import com.charpixel.baseandroidproject.common.BaseView;

/**
 * Created by ashu on 13-12-2016.
 */

public class SplashScreenContract {

    interface Presenter extends BasePresenter {

        void getAdminDetail();


    }

    public interface View extends BaseView {


        void logout();

        void showLoader(boolean isShowLoader);
    }



}
