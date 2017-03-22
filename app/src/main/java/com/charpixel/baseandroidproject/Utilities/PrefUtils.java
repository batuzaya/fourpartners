package com.charpixel.baseandroidproject.Utilities;

import android.content.Context;
import android.location.Location;

import com.charpixel.baseandroidproject.Constants;
import com.charpixel.baseandroidproject.fcm.MyFirebaseInstanceIDService;


/**
 * Created by ashu on 17-04-2016.
 */
public class PrefUtils {

    private PreferencesConnector preferencesConnector;

    public PrefUtils(PreferencesConnector preferencesConnector){
        this.preferencesConnector = preferencesConnector;
    }

    public  void saveDeviceToken(Context context, String deviceRegId) {
        preferencesConnector.writeString(context, Constants.GCM_REG_ID, deviceRegId);
    }

    public  void saveAccessToken(Context context, String accessToken) {
        preferencesConnector.writeString(context, Constants.ACCESS_TOKEN, accessToken);
        Constants.ACCESS_TOKEN_VALUE = accessToken;
    }
    public String getAccessToken(Context context) {
        return preferencesConnector.readString(context, Constants.ACCESS_TOKEN, "");
    }


    public  void saveUserId(Context context, String userId) {
        preferencesConnector.writeString(context, Constants.USER_ID, userId);
    }
    public  String getUserId(Context context) {
        return preferencesConnector.readString(context, Constants.USER_ID,null);
    }


    public String getDeviceToken(Context context) {

        return MyFirebaseInstanceIDService.token;
        //return preferencesConnector.readString(context, Constants.GCM_REG_ID, "");
    }
    public void logout(Context c){
        saveAccessToken(c,null);
    }



    public  void saveCurrentLocation(Context context, Location currentLocation ) {
        Constants.CURRENT_LOCATION_LATITUDE_VALUE = currentLocation.getLatitude();
        Constants.CURRENT_LOCATION_LONGITUDE_VALUE = currentLocation.getLongitude();
        preferencesConnector.writeString(context, Constants.CURRENT_LOCATION_LATITUDE, String.valueOf(currentLocation.getLatitude()));
        preferencesConnector.writeString(context, Constants.CURRENT_LOCATION_LONGITUDE, String.valueOf( currentLocation.getLongitude()));
    }

    public  void saveCurrentLocationAddress(Context context, String currentLocationAddress ) {

        preferencesConnector.writeString(context, Constants.CURRENT_LOCATION_ADDRESS, currentLocationAddress);


    }





}
