package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import com.charpixel.baseandroidproject.common.BasePresenter;
import com.charpixel.baseandroidproject.common.BaseView;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType1;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType2;

/**
 * Created by ashu on 24-12-2016.
 */

public class PlantGraphContract  {
    public interface View extends BaseView {


        void showLoader(boolean isShowLoader);


        void refresh();


        void refreshPlantEnergyData(GraphDataType1 data);

        void refreshPlantIrridationData(GraphDataType2 data);
    }

    interface Presenter extends BasePresenter {

        void getPlantEnergyData(String date , String type);
        void getPlantIrridationData(String date);


    }
}
