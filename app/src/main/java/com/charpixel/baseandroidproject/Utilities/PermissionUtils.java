package com.charpixel.baseandroidproject.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * PermissionUtils
 */
public class PermissionUtils {

    /**
     * @return true if given permission is granted
     */
    public static boolean hasPermission(Context context, String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || !(context == null
                || permission == null
                || permission.trim().isEmpty())
                && ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @return true if given permission list is granted
     */
    public static boolean hasPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (permissions == null || permissions.length == 0) {
            return false;
        }
        for (String permission : permissions) {
            if (!hasPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermissions(Activity activity, String[] permissions, int requestId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        if (activity == null || permissions == null || permissions.length == 0) {
            return;
        }
        activity.requestPermissions(permissions, requestId);
    }

    public static void requestPermissions(Fragment fragment, String[] permissions, int requestId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        if (fragment == null || permissions == null || permissions.length == 0) {
            return;
        }
        fragment.requestPermissions(permissions, requestId);
    }

    public static boolean isMarshMallow() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.M;
    }
}
