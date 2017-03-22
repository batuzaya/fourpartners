package com.charpixel.fourthpartnerenergy.PlantListModule;

import com.charpixel.baseandroidproject.common.BasePresenter;
import com.charpixel.baseandroidproject.common.BaseView;

import java.util.ArrayList;

/**
 * Created by ashu on 13-12-2016.
 */

public class PlantListContract {
    public interface View extends BaseView {

        void RefreshPlantList(ArrayList<Plant> plants);

        void appendPlantList(ArrayList<Plant> data);

        void showLoader(boolean isShowLoader);
    }

    interface Presenter extends BasePresenter {

        void populatePlants();
        void loadMorePlant();

    }
}
