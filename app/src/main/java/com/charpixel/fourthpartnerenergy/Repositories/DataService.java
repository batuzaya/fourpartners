package com.charpixel.fourthpartnerenergy.Repositories;

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;
import com.charpixel.fourthpartnerenergy.Models.ChangePasswordApi;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType1;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType2;
import com.charpixel.fourthpartnerenergy.Models.PlantDetail;
import com.charpixel.fourthpartnerenergy.PlantListModule.Plant;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by ashu on 13-12-2016.
 */

public interface DataService {

    @GET("/api/mobile/powerPlants")
    Observable<BaseResponseData<ArrayList<Plant>>> getPlants(@Header("Authorization") String token ,
                                                          @Query("search") String search,
                                                          @Query("page") Integer page,
                                                          @Query("count") Integer count,
                                                          @Query("date") String date,
                                                          @Query("sortKey") String sortKey,
                                                          @Query("sortOrder") Integer sortOrder
                                                         );


    @GET("/api/powerPlants/details/{powerPlantId}")
    Observable<BaseResponseData<PlantDetail>> getPlantDetail(@Header("Authorization") String token ,
                                                             @Path("powerPlantId") String plantId

    );

    @PUT("/api/auth/logout")
    Observable<BaseResponseData<NoDataResponse>> logout(@Header("Authorization") String token);

    @PUT("/api/admins/changePassword/{adminId}")
    Observable<BaseResponseData<NoDataResponse>> changePassword(@Header("Authorization") String token , @Path("adminId") String adminId , @Body ChangePasswordApi.Request request);


    @GET("/api/powerPlants/graph/{powerPlantId}")
    Observable<BaseResponseData<GraphDataType1>> getEnergyPrGraph(@Header("Authorization") String token , @Path("powerPlantId") String plantId , @Query("date") String date , @Query("graphType") String graphType);

    @GET("/api/powerPlants/graph/{powerPlantId}")
    Observable<BaseResponseData<GraphDataType2>> getInverterIrridationGraph(@Header("Authorization") String token , @Path("powerPlantId") String plantId , @Query("date") String date , @Query("graphType") String graphType);

    @GET("/api/reports/logBook")
    @Streaming
    Observable<ResponseBody> downloadLogBook(@Header("Authorization") String token , @Query("date") String date , @Query("_id") String id);

    @GET("/api/reports/dailyReport")
    @Streaming
    Observable<ResponseBody> downloadReport(@Header("Authorization") String token , @Query("date") String date , @Query("_id") String id);





}
