package com.charpixel.fourthpartnerenergy.PlantListModule;

import android.util.Log;

import com.charpixel.baseandroidproject.AppSession;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.BaseView;
import com.charpixel.fourthpartnerenergy.Models.PlantListApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ashu on 13-12-2016.
 */

public class PlantListPresenter implements PlantListContract.Presenter {

    String TAG = this.getClass().getSimpleName();

    PlantListContract.View view;

    PlantListUsecase plantListUsecase;

    AppSession appSession;

    PlantListApi.Request plantListRequest = new PlantListApi.Request();
    boolean isLoading = false;
    boolean isLoadMore = true;
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date today = new Date();
    boolean isInc = true;



    public PlantListPresenter(AppSession appSession , PlantListUsecase plantListUsecase ){
        this.plantListUsecase = plantListUsecase;
        this.appSession = appSession;



        plantListRequest.setDate(formatter.format(today));
        Log.e(TAG, "PlantListPresenter: initialisinggggg................." );
    }




    public void setDate(String date){
        plantListRequest.setDate(date);
    }


    @Override
    public void populatePlants() {

        isLoadMore = true;
        plantListRequest.setSearch(null);
        plantListRequest.setPage(1);
        plantListUsecase.setRequest(plantListRequest);
        if(!isLoading) {
            isLoading = true;
            view.showLoader(true);

            plantListUsecase.execute().subscribe(this::plantListRefresh, this::onError);
        }

        Log.v(TAG,"hiii populating plants");

    }


    public void populatePlantsSorting(int sortType , String sortCategory) {


        plantListRequest.setSortKey(sortCategory);
        plantListRequest.setSortOrder(sortType);
        plantListRequest.setPage(1);
        plantListRequest.setSearch(null);
        plantListUsecase.setRequest(plantListRequest);

        isLoadMore = true;

            isLoading = true;
           view.showLoader(true);
            plantListUsecase.execute().subscribe(this::plantListRefresh, this::onError);


        Log.v(TAG,"hiii populating plants");

    }

    @Override
    public void loadMorePlant() {


        Log.e(TAG, "loadMorePlant: isLoading : "+isLoading+",isLoadMore : "+isLoadMore );

        if(!isLoading && isLoadMore)
        {

            if(isInc){
                plantListRequest.setPage(plantListRequest.getPage()+1);
                isInc = false;
            }else {
                plantListRequest.setPage(plantListRequest.getPage());
            }



            isLoading = true;
            view.showLoader(true);
            plantListUsecase.execute().subscribe(this::plantListSuccess,this::onError);
        }


    }



    private void onError(Throwable throwable) {
        Log.v(TAG,"error occured"+throwable.getMessage());
        isLoading = false;
        view.showLoader(false);

    }

    private void plantListSuccess(BaseResponseData<ArrayList<Plant>> arrayListBaseResponseData) {

        Log.v(TAG,arrayListBaseResponseData.getData().size()+"");

        isLoading = false;
        view.showLoader(false);
        isInc = true;

        if(arrayListBaseResponseData.getData().size() < plantListRequest.getCount()){
            isLoadMore = false;

        }

        appSession.getPlantArrayList().addAll(arrayListBaseResponseData.getData());
        view.appendPlantList(arrayListBaseResponseData.getData());

    }

    private void plantListRefresh(BaseResponseData<ArrayList<Plant>> arrayListBaseResponseData) {

        Log.v(TAG,arrayListBaseResponseData.getData().size()+"");

        isLoading = false;
        view.showLoader(false);

        if(arrayListBaseResponseData.getData().size() < plantListRequest.getCount()){
            isLoadMore = false;
        }
        appSession.setPlantArrayList(arrayListBaseResponseData.getData());
        view.RefreshPlantList(arrayListBaseResponseData.getData());

    }

    @Override
    public void attachView(BaseView v) {
        this.view = (PlantListContract.View)v;
    }

    public void populatePlantsSearch(String searchQuery) {


        plantListRequest.setSortKey("plantName");
        plantListRequest.setSortOrder(1);
        plantListRequest.setPage(1);
        plantListRequest.setSearch(searchQuery);
        plantListUsecase.setRequest(plantListRequest);

        isLoading = true;
        view.showLoader(true);
        plantListUsecase.execute().subscribe(this::plantListRefresh, this::onError);


    }
}
