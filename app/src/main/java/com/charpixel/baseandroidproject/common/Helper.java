package com.charpixel.baseandroidproject.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;


/**
 * Helper
 */
public class Helper {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static boolean isValidUrl(String url) {
        return URLUtil.isValidUrl(url);
    }

    public static void printStackTrace() {
        StringBuilder builder = new StringBuilder();
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement e : stackTrace) {
            builder.append(e);
            builder.append('\n');
        }
        System.err.print(Thread.currentThread() + " Stack Trace :" + builder.toString());
    }

    public static double normalize(double value, double min, double max) {
        if (max == min) {
            return -1;
        }
        return (value - min) / (max - min);
    }

    public static boolean checkSelfPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

}