package com.charpixel.baseandroidproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.charpixel.baseandroidproject.di.Components.DaggerNetComponent;
import com.charpixel.baseandroidproject.di.Components.NetComponent;
import com.charpixel.baseandroidproject.di.Modules.AppModule;
import com.charpixel.baseandroidproject.di.Modules.NetModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;


/**
 * Created by ashu on 16-11-2016.
 */

public class Application extends MultiDexApplication {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "SdL6Mt7haFfjgy4bNes2aOe9G";
    private static final String TWITTER_SECRET = "yG1qJNys9XOLTd42MUC658PVRrD898qf2ab1d8vWM3eqdBYpfk";


    public final static boolean IS_DEBUGGING_ON = false;
    public final static String packName = "com.mswipetech.wisepos.sdk";
    public static final String SERVER_NAME = "Mswipe";
    public static final int PhoneNoLength = 10;
    public static final String Currency_Code = "INR.";
    public static final String smsCode = "+91";
    public static final String mTipRequired = "false";
    public static SharedPreferences appSharedPreferences;

    private static Application instance;

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        instance = this;
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://"+ Constants.domain+"/"))
                .build();
       // MswipeWiseposController.setMACADDType(AppPrefrences.getNetworkType());


    }
    public static Context _getContext(){
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public NetComponent getNetComponent(){return  netComponent;}

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



}
