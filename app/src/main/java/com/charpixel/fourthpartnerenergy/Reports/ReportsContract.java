package com.charpixel.fourthpartnerenergy.Reports;

import com.charpixel.baseandroidproject.common.BasePresenter;
import com.charpixel.baseandroidproject.common.BaseView;

import okhttp3.ResponseBody;

/**
 * Created by ashu on 28-12-2016.
 */

public class ReportsContract {
    public interface View extends BaseView {


        void showLoader(boolean isShowLoader);
        void downloadReport(ResponseBody responseBody);
        void onError();
    }

    interface Presenter extends BasePresenter {

        void   getLogData(String date);
        void getReportData(String date);



    }
}
