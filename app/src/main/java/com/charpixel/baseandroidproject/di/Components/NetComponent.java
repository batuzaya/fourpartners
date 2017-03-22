package com.charpixel.baseandroidproject.di.Components;

/**
 * Created by ashu on 03-03-2016.
 */

import android.content.SharedPreferences;

import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.LoginActivityFragment;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.RegisterFragment;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.forgotpassword.ForgotpasswordActivityFragment;
import com.charpixel.baseandroidproject.Modules.SplashScreen.SplashScreenActivity;
import com.charpixel.baseandroidproject.Utilities.PrefUtils;
import com.charpixel.baseandroidproject.Utilities.PreferencesConnector;
import com.charpixel.baseandroidproject.common.BaseActivity;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.di.Modules.AppModule;
import com.charpixel.baseandroidproject.di.Modules.NetModule;
import com.charpixel.fourthpartnerenergy.MapLocationModule.PlantMapLocationFragment;
import com.charpixel.fourthpartnerenergy.PlantDetailModule.PlantArchiveDetailFragment;
import com.charpixel.fourthpartnerenergy.PlantDetailModule.PlantDetailFragment;
import com.charpixel.fourthpartnerenergy.PlantDetailModule.PlantLiveDetailFragment;
import com.charpixel.fourthpartnerenergy.PlantDetailModule.PlantSubDatailFragment;
import com.charpixel.fourthpartnerenergy.PlantListModule.PlantListFragment;
import com.charpixel.fourthpartnerenergy.Reports.DownloadService;
import com.charpixel.fourthpartnerenergy.Reports.ReportsFragment;
import com.charpixel.fourthpartnerenergy.SettingModule.ProfileFragment;
import com.charpixel.fourthpartnerenergy.SettingModule.SecurityFragment;
import com.charpixel.fourthpartnerenergy.SettingModule.SettingFragment;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Scheduler;


@Singleton
@Component(modules = {NetModule.class, AppModule.class})
public interface NetComponent {


    //void inject(MyFirebaseInstanceIDService myFirebaseInstanceIDService);
    void inject(BaseActivity baseActivity);
    //void inject(BaseFragment baseAppCompatActivity);
    void inject(BaseFragment baseFragment);
    void inject(LoginActivityFragment loginActivityFragment);
    void inject(RegisterFragment registerFragment);
    void inject(ForgotpasswordActivityFragment forgotpasswordActivityFragment);
    void inject(SplashScreenActivity splashScreenActivity);
    void inject(PlantListFragment plantListFragment);
    void inject(PlantDetailFragment plantDetailFragment);
    void inject(PlantSubDatailFragment plantSubDatailFragment);
    void inject(PlantLiveDetailFragment plantLiveDetailFragment);
    void inject(SettingFragment settingFragment);
    void inject(ProfileFragment profileFragment);
    void inject(SecurityFragment securityFragment);
    void inject(PlantMapLocationFragment plantMapLocationFragment);
    void inject(PlantArchiveDetailFragment plantArchiveDetailFragment);
    void inject(ReportsFragment reportsFragment);
    void inject(DownloadService downloadService);


    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
    PreferencesConnector preferencesConnector();
    PrefUtils utils();



    @Named("ui_thread")
    Scheduler uiThread();
    @Named("executor_thread")
    Scheduler executorThread();

}