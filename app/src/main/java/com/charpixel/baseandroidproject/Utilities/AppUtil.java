package com.charpixel.baseandroidproject.Utilities;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * SystemAppUtil
 */
public class AppUtil {

    public static String getAppVersion(Context context) {

        if (context == null) {
            Log.d("log", "context may not ve null");
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getDeviceId(Context context) {
        if (context == null)
            return null;
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }



    private static String getDeviceIdBySlot(TelephonyManager manager, String name, int slotID) throws Exception {
        Class<?> telephonyClass;
        telephonyClass = manager.getClass();
        Method getSimID = telephonyClass.getMethod(name, int.class);
        Object result = getSimID.invoke(manager, slotID);
        return result.toString();
    }

    public static String toURL(Uri uri) {
        StringBuilder sb = new StringBuilder();
        sb.append(uri.getScheme()).append(':');
        sb.append(uri.getEncodedSchemeSpecificPart());
        String fragment = uri.getFragment();
        if (fragment != null && !fragment.isEmpty()) {
            sb.append('#').append(uri.getEncodedFragment());
        }
        return sb.toString();
    }

    public static Date getDate(String dateLabel) {
        if (dateLabel == null || dateLabel.isEmpty()) return null;
        try {
            //  Date Format Thu 4th Aug
            DateFormat formatter = new SimpleDateFormat("EEE dd'th' MMM yyyy", Locale.getDefault());
            int year = Calendar.getInstance().get(Calendar.YEAR);
            return formatter.parse(dateLabel + " " + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}