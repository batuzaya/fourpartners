package com.charpixel.fourthpartnerenergy.Reports;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.databinding.ReportFragmentBinding;
import com.charpixel.fourthpartnerenergy.PlantListModule.Dialogs.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import okhttp3.ResponseBody;



/**
 * Created by ashu on 28-12-2016.
 */


public class ReportsFragment extends BaseFragment implements ReportsContract.View {

        public static final String MESSAGE_PROGRESS = "message_progress";
    private static final int PERMISSION_REQUEST_CODE = 1;
    private String TAG = this.getClass().getSimpleName();

    enum ReportType{
        LOG_BOOK,DAILY_REPORT
    }

    ReportFragmentBinding binding;

    ReportType reportType ;

    Calendar cal = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();

    @Inject
    ReportsPresenter presenter;



    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.report_fragment,container,false);
        registerReceiver();

        presenter.attachView(this);

        binding.dailyReport.setOnClickListener(view -> {
            DatePicker newFragment = new DatePicker();
            newFragment.setListener((date, year , month , day) -> {


                Log.v(TAG,"dateSelected   : "+date);
                reportType = ReportType.DAILY_REPORT;

                downloadFile(getCurrentDate(cal2,Calendar.DATE,0));
                showProgress();

            });

            newFragment.setDate(cal2.get(Calendar.YEAR),cal2.get(Calendar.MONTH),cal2.get(Calendar.DAY_OF_MONTH));
            newFragment.show(getFragmentManager(), "datePicker");




        });
        binding.logBook.setOnClickListener(view -> {
            DatePicker newFragment = new DatePicker();
            newFragment.setListener((date, year , month , day) -> {

                Log.v(TAG,"dateSelected   : "+date);
                reportType = ReportType.LOG_BOOK;

                downloadFile(getCurrentDate(cal,Calendar.DATE,0));

                showProgress();
            });
            newFragment.setDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
            newFragment.show(getFragmentManager(), "datePicker");

        });

        return binding.getRoot();
    }

    void showProgress(){
        binding.progressCircle.setVisibility(View.VISIBLE);
    }



    public String getCurrentDate(Calendar cal,int type , int offset){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.add(type, offset);

        return  sdf.format(cal.getTime());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }



    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;
        }
    }

    private void requestPermission(){

        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    if(reportType == ReportType.DAILY_REPORT)
                    {
                        startDownload(getCurrentDate(cal2,Calendar.DATE,0));
                    }else{
                        startDownload(getCurrentDate(cal,Calendar.DATE,0));
                    }

                } else {

                    Snackbar.make(binding.getRoot(),"Permission Denied, Please allow to proceed !", Snackbar.LENGTH_LONG).show();

                }
                break;
        }
    }

    private void registerReceiver(){

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(MESSAGE_PROGRESS)){

                Download download = intent.getParcelableExtra("download");
             //   binding.progress.setProgress(download.getProgress());

                if(download.getError())
                {

                    binding.progressCircle.setVisibility(View.GONE);

                    binding.progressText.setText("Please Retry.");
                    return;

                }


                if(download.getProgress() == 100){
                    binding.progressCircle.setVisibility(View.GONE);

                    binding.progressText.setText("File Download Complete");

                } else {

                    binding.progressText.setText(String.format("Downloaded (%d/%d) MB",download.getCurrentFileSize(),download.getTotalFileSize()));

                }
            }
        }
    };


    private void startDownload(String date ){

        Log.e(TAG, "startDownload: "+reportType.toString() );

        Intent intent = new Intent(getActivity(),DownloadService.class);
        Bundle bundle = new Bundle();
        bundle.putString("date",date);
        bundle.putString("type",reportType.toString());
        intent.putExtras(bundle);
        getActivity().startService(intent);

    }

    public void downloadFile(String date ){

        if(checkPermission()){
            startDownload( date );
        } else {
            requestPermission();
        }
    }


    @Override
    public void showLoader(boolean isShowLoader) {

    }

    @Override
    public void downloadReport(ResponseBody responseBody) {

    }

    @Override
    public void onError() {

    }
}
