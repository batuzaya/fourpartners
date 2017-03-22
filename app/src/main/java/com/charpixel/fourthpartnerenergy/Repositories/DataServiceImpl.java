package com.charpixel.fourthpartnerenergy.Repositories;

import com.charpixel.baseandroidproject.Constants;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;
import com.charpixel.fourthpartnerenergy.Models.ChangePasswordApi;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType1;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType2;
import com.charpixel.fourthpartnerenergy.Models.PlantDetail;
import com.charpixel.fourthpartnerenergy.PlantListModule.Plant;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by ashu on 13-12-2016.
 */


public class DataServiceImpl implements DataRepository {


    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private DataService dataService;

    @Inject
    public DataServiceImpl(DataService dataService,
                                  @Named("ui_thread") Scheduler uiThread,
                                  @Named("executor_thread") Scheduler executorThread)
    {
        this.mUiThread = uiThread;
        this.mExecutorThread = executorThread;
        this.dataService = dataService;

    }
    @Override
    public Observable<BaseResponseData<ArrayList<Plant>>> getPlants(String search, Integer page, Integer count, String date , String sortKey , Integer sortType) {
      return dataService.getPlants(Constants.getAccessToken() , search , page,count,date,sortKey,sortType ).subscribeOn(mExecutorThread).observeOn(mUiThread);


    }

    @Override
    public Observable<BaseResponseData<PlantDetail>> getPlantDetail(String plantId) {
        return dataService.getPlantDetail(Constants.getAccessToken() , plantId ).subscribeOn(mExecutorThread).observeOn(mUiThread);

    }

    @Override
    public Observable<BaseResponseData<NoDataResponse>> logout() {
        return dataService.logout(Constants.getAccessToken()).subscribeOn(mExecutorThread).observeOn(mUiThread);

    }

    @Override
    public Observable<BaseResponseData<NoDataResponse>> changePassword(ChangePasswordApi.Request request) {

        request._id = Constants.USER_ID_VALUE;
        return dataService.changePassword(Constants.getAccessToken(),Constants.USER_ID_VALUE,request).subscribeOn(mExecutorThread).observeOn(mUiThread);
    }

    @Override
    public Observable<BaseResponseData<GraphDataType1>> getEnergyPrGraph(String plantId, String date, String graphType) {
        return dataService.getEnergyPrGraph(Constants.getAccessToken(),plantId,date,graphType).subscribeOn(mExecutorThread).observeOn(mUiThread);
    }

    @Override
    public Observable<BaseResponseData<GraphDataType2>> getInverterIrridationGraph(String plantId, String date) {
        return dataService.getInverterIrridationGraph(Constants.getAccessToken(),plantId,date,"inverter").subscribeOn(mExecutorThread).observeOn(mUiThread);
    }

    @Override
    public Observable<ResponseBody> downloadDailyReport(String date) {
        return dataService.downloadReport(Constants.getAccessToken(),date,Constants.USER_ID_VALUE).subscribeOn(mExecutorThread).observeOn(mUiThread);

    }

    @Override
    public Observable<ResponseBody> downloadLogBook(String date) {
        return dataService.downloadLogBook(Constants.getAccessToken(),date,Constants.USER_ID_VALUE).subscribeOn(mExecutorThread).observeOn(mUiThread);

    }


}
