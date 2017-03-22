package com.charpixel.baseandroidproject.Modules.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.Constants;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegistationActivity;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.Utilities.PrefUtils;
import com.charpixel.fourthpartnerenergy.Dashboard;

import javax.inject.Inject;

/**
 * Created by ashu on 13-12-2016.
 */

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenContract.View{
    private  final String TAG = this.getClass().getSimpleName();
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Inject
    PrefUtils utils;

    @Inject
    SplashScreenPesenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Application) getApplication()).getNetComponent().inject(this);


   //     presenter.getAdminDetail();

        setContentView(R.layout.splash_screen);
        goToLogin();

     //   checkForState();
    }


    void goToLogin(){

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent i = new Intent(SplashScreenActivity.this,checkForState());
                startActivity(i);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    Class checkForState(){

        if(utils.getAccessToken(this) == null || utils.getUserId(this) == null || utils.getAccessToken(this).isEmpty() || utils.getUserId(this).isEmpty())
        {
            return  RegistationActivity.class;
        }else {

            Log.e(TAG, "checkForState: " );

            Constants.ACCESS_TOKEN_VALUE = utils.getAccessToken(this);
            Constants.USER_ID_VALUE = utils.getUserId(this);
            presenter.getAdminDetail();


            return Dashboard.class;
        }


    }



    @Override
    public void logout() {
        utils.logout(this);
        finish();
        Intent i = new Intent(this, RegistationActivity.class);
        startActivity(i);
        //showSnackbar("Sorry , Your session has been expired. Please try again.");

    }

    @Override
    public void showLoader(boolean isShowLoader) {

    }


    @Override
    public void showSnackbar(String s) {

    }
}
