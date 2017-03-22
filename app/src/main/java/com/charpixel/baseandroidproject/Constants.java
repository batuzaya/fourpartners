package com.charpixel.baseandroidproject;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Ashu Saini on 4/25/2015.
 */
public class Constants {

    public static final String APP_NAME = "BaseProject";

    public static final String GCM_REG_ID = APP_NAME+"_GCM_REG_ID";
    public static final String CURRENT_LOCATION_LATITUDE = APP_NAME+"_CURRENT_LOCATION_LATITUDE";
    public static final String CURRENT_LOCATION_LONGITUDE = APP_NAME+"_CURRENT_LOCATION_LONGITUDE";
    public static final String CURRENT_LOCATION_ADDRESS = APP_NAME+"_CURRENT_LOCATION_ADDRESS";
    public static final String ACCESS_TOKEN = APP_NAME+"_ACCESS_TOKEN";
    public static final String USER_ID = APP_NAME+"_USER_ID";
    public static String USER_ID_VALUE = null;

    public static String domainLocal = "192.168.1.10:8008";
    public static String domainServer = "dashboard.fourthpartner.co:80";
    public static String domainServerLive = "www.vclnetwork.com:9900";


    public static final int SUCCESS_RESULT = 0;

    public static final int FAILURE_RESULT = 1;

    public static final String PACKAGE_NAME =
            "charpixel.taxicustomer";

    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";

    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";

    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";

    public static String domain = domainServer;

    //public static final String BASE_URL = "http://www.mclnetwork.com/users/";  // original

    public static final String BASE_URL = "http://"+domain+"/api/";
    public static final String WEB_SOCKET_URL = "http://"+domain;


    static Calendar mCalendar = new GregorianCalendar();
    static TimeZone mTimeZone = mCalendar.getTimeZone();
    public  static int TIME_ZONE_OFFSET = mTimeZone.getRawOffset() / (1000*60);
    public  static String DEVICE_TOKEN = "";
    public static Double CURRENT_LOCATION_LATITUDE_VALUE = 0.00;
    public static Double CURRENT_LOCATION_LONGITUDE_VALUE = 0.00;
    public static String ACCESS_TOKEN_VALUE = "";



    public static final String GCM_APP_VERSION = "appVersion";

    public static final String USER_TOKEN = "TokenACCESS";

    public static final String USER_STATUS = "status";

    public static final String USER_VEHICLE = "vehicle";

    public static final String JOB_REC_BROADCAST_INTENT = "JOB_REC";

    public static final String NOTIFICATION_TYPE_INTENT = "accesstokenlogin";

    public static final String NOTIFICATION_TYPE_CANCEL = "cancelbycustomer";

    public static final String IS_USER_LOGGEDIN = "loggedinNEWONE";

    public static final String IS_USER_REGISTERED = "IS_USER_REGISTERED";

    public static final String HTTP_CONTENT_TYPE = "Content-Type";

    public static final String HTTP_USER_AGENT = "User-Agent";

    public static final String CONTENT_TYPE_JSON = "application/json";


    public static final int STATUS_OFFLINE = 0;

    public static final int STATUS_ONLINE = 1;

    public static final int STATUS_BUSY = 2;


    public static final String RESPONSE_UNAUTHORIZED= "Unauthorized Access";

    public static final int STATUS_UNAUTHORIZED= 401;

    public static final int STATUS_OK= 200;

    public static String getAccessToken(){

        return  "bearer "+ACCESS_TOKEN_VALUE;

    }

}
