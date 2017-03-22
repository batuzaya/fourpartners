package com.charpixel.fourthpartnerenergy.Reports;

import android.util.Log;

import com.charpixel.baseandroidproject.common.BaseView;

import javax.inject.Inject;

import okhttp3.ResponseBody;



/**
 * Created by ashu on 28-12-2016.
 */

public class ReportsPresenter implements ReportsContract.Presenter  {


    DownloadReportUsecase downloadReportUsecase;
    DownloadLogsUsecase downloadLogsUsecase;

    ReportsContract.View view ;
    private String TAG = this.getClass().getSimpleName();

    @Inject
    ReportsPresenter(DownloadReportUsecase downloadReportUsecase , DownloadLogsUsecase downloadLogsUsecase){

        this.downloadLogsUsecase = downloadLogsUsecase;
        this.downloadReportUsecase = downloadReportUsecase;


    }




    @Override
    public void attachView(BaseView v) {
        this.view = (ReportsContract.View)v;

    }

    @Override
    public void getLogData(String date) {
        downloadLogsUsecase.setDate(date);
        downloadLogsUsecase.execute().subscribe(this::onLogDataSuccess ,this::onError);
    }

    private void onLogDataSuccess(ResponseBody responseBody) {
    }

    @Override
    public void getReportData(String date) {
        downloadReportUsecase.setDate(date);
        downloadReportUsecase.execute().subscribe(this::onDailyReportSuccess ,this::onError);
    }

    private void onDailyReportSuccess(ResponseBody responseBody) {

        view.downloadReport(responseBody);
    }

    private void onError(Throwable throwable) {

        throwable.printStackTrace();
        view.onError();

        Log.e(TAG, "onError: "+"socket timeout exception" );
    }
}
