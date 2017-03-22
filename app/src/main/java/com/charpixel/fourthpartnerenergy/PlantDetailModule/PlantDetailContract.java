package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import com.charpixel.baseandroidproject.common.BasePresenter;
import com.charpixel.baseandroidproject.common.BaseView;

/**
 * Created by ashu on 20-12-2016.
 */

public class PlantDetailContract {
    public interface View extends BaseView {


        void showLoader(boolean isShowLoader);

        void refresh();
    }

    interface Presenter extends BasePresenter {

        void getPlantDetail(String id);


    }
}
