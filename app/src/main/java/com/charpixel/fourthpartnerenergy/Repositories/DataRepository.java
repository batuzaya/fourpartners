package com.charpixel.fourthpartnerenergy.Repositories;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;
import com.charpixel.fourthpartnerenergy.Models.ChangePasswordApi;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType1;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType2;
import com.charpixel.fourthpartnerenergy.Models.PlantDetail;
import com.charpixel.fourthpartnerenergy.PlantListModule.Plant;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by ashu on 13-12-2016.
 */

public interface DataRepository {

        Observable<BaseResponseData<ArrayList<Plant>>> getPlants(String search, Integer page, Integer count, String date, String sortKey , Integer sortType);
        Observable<BaseResponseData<PlantDetail>> getPlantDetail(String plantId);
        Observable<BaseResponseData<NoDataResponse>> logout();
        Observable<BaseResponseData<NoDataResponse>> changePassword(ChangePasswordApi.Request request);
        Observable<BaseResponseData<GraphDataType1>> getEnergyPrGraph(String plantId , String date , String graphType);
        Observable<BaseResponseData<GraphDataType2>> getInverterIrridationGraph(String plantId , String date);
        Observable<ResponseBody> downloadDailyReport(String date);
        Observable<ResponseBody> downloadLogBook(String date);


}
