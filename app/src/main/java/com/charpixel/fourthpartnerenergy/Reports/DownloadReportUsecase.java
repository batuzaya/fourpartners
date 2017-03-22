package com.charpixel.fourthpartnerenergy.Reports;

import com.charpixel.baseandroidproject.common.UsecaseBase;
import com.charpixel.fourthpartnerenergy.Repositories.DataRepository;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by ashu on 28-12-2016.
 */

public class DownloadReportUsecase extends UsecaseBase<ResponseBody> {

    private DataRepository dataRepository;

    private String date ;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Inject
    public DownloadReportUsecase(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @Override
    public Observable<ResponseBody> buildObservable() {
        return dataRepository.downloadDailyReport(date);
    }
}
