package com.charpixel.baseandroidproject.di.Modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.charpixel.baseandroidproject.AppSession;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Facebook;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Google;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Login;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.Repositories.RegisterModuleRestDataSource;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.Repositories.RegistrationModuleApiRepository;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.Services.RegistrationModuleApiServices;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.forgotpassword.ForgotPasswordRequest;
import com.charpixel.baseandroidproject.Utilities.PrefUtils;
import com.charpixel.baseandroidproject.Utilities.PreferencesConnector;
import com.charpixel.fourthpartnerenergy.Models.CurrentPlant;
import com.charpixel.fourthpartnerenergy.PlantListModule.PlantListPresenter;
import com.charpixel.fourthpartnerenergy.PlantListModule.PlantListUsecase;
import com.charpixel.fourthpartnerenergy.Repositories.DataRepository;
import com.charpixel.fourthpartnerenergy.Repositories.DataService;
import com.charpixel.fourthpartnerenergy.Repositories.DataServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashu on 17-09-2016.
 */
@Module
public class NetModule {

    String baseUrl;

    // Constructor needs one parameter to instantiate.
    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    PreferencesConnector providesPreferenceConnector(SharedPreferences sharedPreferences) {
        return new PreferencesConnector(sharedPreferences);
    }
    @Provides
    @Singleton
    PrefUtils providePrefUtils (PreferencesConnector preferencesConnector) {
        return new PrefUtils(preferencesConnector);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS).addInterceptor(interceptor).cache(cache).build();
        // OkHttpClient client = new OkHttpClient();

        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    RegistrationModuleApiServices provideLoginServices(Retrofit retrofit){
        return retrofit.create(RegistrationModuleApiServices.class);
    }

    @Provides
    @Singleton
    RegistrationModuleApiRepository provideSignupModuleApiRepo(RegisterModuleRestDataSource registerModuleRestDataSource){
        return registerModuleRestDataSource;
    }

    @Provides
    @Singleton
    Login.Request provideLoginRequest(){
        return new Login.Request();
    }
    @Provides
    @Singleton
    Facebook.Request provideFacebookRequest(){
        return new Facebook.Request();
    }

    @Provides
    @Singleton
    ForgotPasswordRequest provideForgotPasswordRequest(){
        return new ForgotPasswordRequest();
    }
    @Provides
    @Singleton
    Google.Request provideGoogleRequest(){
        return new Google.Request();
    }

    @Provides
    @Singleton
    DataService provideDataServices(Retrofit retrofit){
        return retrofit.create(DataService.class);
    }

    @Provides
    @Singleton
    DataRepository provideDataRepoApi(DataServiceImpl dataServiceImpl){
        return dataServiceImpl;
    }

    @Provides
    @Singleton
    CurrentPlant provideCurrentPlant(){
        return new CurrentPlant();
    }

    @Provides
    @Singleton
    AppSession provideAppSession(){
        return new AppSession();
    }

    @Provides
    @Singleton
    PlantListPresenter providePlantListPresenter(AppSession appSession,PlantListUsecase plantListUsecase){
        return new PlantListPresenter(appSession ,plantListUsecase);
    }


}
