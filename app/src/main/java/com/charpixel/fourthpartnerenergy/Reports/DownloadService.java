package com.charpixel.fourthpartnerenergy.Reports;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import okhttp3.ResponseBody;


public class DownloadService extends IntentService implements ReportsContract.View {


   String TAG = this.getClass().getSimpleName();

    public DownloadService() {
        super("Download Service");
    }

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int totalFileSize;
    Uri savedFileUri;
    String fileType;

    String date;
    String type;

    @Inject
    ReportsPresenter presenter;


    @Override
    protected void onHandleIntent(Intent intent) {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Download")
                .setContentText("Downloading File")
                .setAutoCancel(true);
        notificationManager.notify(0, notificationBuilder.build());

        initDownload();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        date = intent.getStringExtra("date");
        type = intent.getStringExtra("type");




        return super.onStartCommand(intent, flags, startId);
    }

    private void initDownload(){

        presenter.attachView(this);

        if(type.equals("DAILY_REPORT"))
        {
            presenter.getReportData(date);
        }else {
            presenter.getLogData(date);
        }

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://download.learn2crack.com/")
//                .build();
//
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        Call<ResponseBody> request = retrofitInterface.downloadFile();
//        try {
//
//            downloadFile(request.execute().body());
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
//
//        }
    }

    private void downloadFile(ResponseBody body) throws IOException {

        int count;
        byte data[] = new byte[1024 * 4];
        long fileSize = body.contentLength();
        String fileName = body.toString();
        Log.e(TAG, "downloadFile: "+fileName );
        InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);
        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), getFileName().concat(".xlsx"));



//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            savedFileUri = Uri.fromFile(outputFile);
//        }else {
       //   savedFileUri = FileProvider.getUriForFile(getApplication(), "com.charpixel.fourthpartnerenergy.fileprovider", outputFile);

  //      }


        fileType = body.contentType().toString();
        OutputStream output = new FileOutputStream(outputFile);
        long total = 0;
        long startTime = System.currentTimeMillis();
        int timeCount = 1;
        while ((count = bis.read(data)) != -1) {

            total += count;
            totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
            double current = Math.round(total / (Math.pow(1024, 2)));

            int progress = (int) ((total * 100) / fileSize);

            long currentTime = System.currentTimeMillis() - startTime;

            Download download = new Download();
            download.setTotalFileSize(totalFileSize);

            if (currentTime > 1000 * timeCount) {

                download.setCurrentFileSize((int) current);
                download.setProgress(progress);
                sendNotification(download);
                timeCount++;
            }

            output.write(data, 0, count);
        }
        onDownloadComplete();
        output.flush();
        output.close();
        bis.close();

    }


    String getFileName(){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy-HH-mm-ss", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return  sdf.format(cal.getTime())+type;
    }

    private void sendNotification(Download download){

        sendIntent(download);
        notificationBuilder.setProgress(100,download.getProgress(),false);
        notificationBuilder.setContentText(String.format("Downloaded (%d/%d) MB",download.getCurrentFileSize(),download.getTotalFileSize()));
        notificationManager.notify(0, notificationBuilder.build());
    }


    private void sendIntent(Download download){

        Intent intent = new Intent(ReportsFragment.MESSAGE_PROGRESS);
        intent.putExtra("download",download);
        LocalBroadcastManager.getInstance(DownloadService.this).sendBroadcast(intent);
    }



    private void onDownloadComplete(){

        Download download = new Download();
        download.setProgress(100);
        sendIntent(download);


        Intent intent = new Intent(Intent.ACTION_VIEW,
                savedFileUri);

        Uri selectedUri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath());



        intent.setDataAndType(selectedUri, "resource/folder");

        //  Intent intent = new Intent(Intent.ACTION_VIEW);
      //  intent.setDataAndType(savedFileUri, fileType);



        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        notificationManager.cancel(0);
        notificationBuilder.setProgress(0,0,false);
        notificationBuilder.setContentText("File Downloaded");
        notificationBuilder.setContentIntent(pIntent);
        notificationManager.notify(0, notificationBuilder.build());

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(0);
    }

    @Override
    public void showLoader(boolean isShowLoader) {

    }

    @Override
    public void onCreate() {
        ((Application) getApplication()).getNetComponent().inject(this);
        super.onCreate();
    }

    @Override
    public void downloadReport(ResponseBody responseBody) {
        try {

            downloadFile(responseBody);

        } catch (IOException e) {

            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onError() {

        Download download = new Download();
        download.setError(true);
        sendIntent(download);

    }

    @Override
    public void showSnackbar(String s) {

    }
}
