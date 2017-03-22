package com.charpixel.baseandroidproject.Modules.RegistrationModule.Services;

import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Facebook;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Google;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Login;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ApiModels.Register;
import com.charpixel.baseandroidproject.Modules.SplashScreen.AdminData;
import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ashu on 25-11-2016.
 */

public interface RegistrationModuleApiServices {

    @POST("/api/auth/login")
    Observable<BaseResponseData<Login.Response>> login(@Body Login.Request request);


    @POST("/api/admins")
    Observable<BaseResponseData<Register.Response>> register(@Body Register.Request request);

    @POST("/api/appAuth/facebook")
    Observable<BaseResponseData<Facebook.Response>> facebookLogin(@Body Facebook.Request request);

    @POST("/api/appAuth/google")
    Observable<BaseResponseData<Google.Response>> googleLogin(@Body Google.Request request);

    @GET("/api/users/getResetPasswordToken/{email}")
    Observable<BaseResponseData<NoDataResponse>> forgotPassword(@Path("email") String email);

    @GET("/api/admins/{id}")
    Observable<BaseResponseData<AdminData>> getAdminData(@Header("Authorization") String token , @Path("id") String id ,

                                                         @Query("criteria") String criteria,
                                                         @Query("projection") String projection,
                                                         @Query("options") String options

                                                              );


}
