package com.charpixel.baseandroidproject.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * UIHelper
 */
public class UIHelper {

    private static final String TAG = "POS_UI";

    @Nullable
    public static Fragment getTopFragment(FragmentManager manager, int containerId) {
        if (manager == null) {
            return null;
        }
        return manager.findFragmentById(containerId);
    }

    public static void addFragment(FragmentManager manager, Fragment fragment, int containerId) {
        if (manager == null || fragment == null) {
            return;
        }
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(containerId, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    public static void replaceFragment(FragmentManager manager, Fragment fragment, int containerId) {
        if (manager == null || fragment == null) {
            return;
        }
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void showSnackBar(Activity activity, String message, ViewGroup view) {
        if (activity == null || isNullOrEmpty(message)) {
            return;
        }
        log("UIHelper show snack bar");
    }

    public static void log(String message) {
        Log.v(TAG, message);
    }

    public static void debugLog(String msg) {
        Log.d(CommonConstant.DEBUG_KEY, TextUtils.isEmpty(msg) ? "blank message" : msg);
    }

    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(Context context, int resourceId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(resourceId, null);
        }
        return context.getResources().getDrawable(resourceId);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewById(View parent, int id) {
        return (T) parent.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewById(Activity activity, int id) {
        return (T) activity.findViewById(id);
    }

    public static boolean isNullOrEmpty(String testName) {
        return testName == null || testName.trim().isEmpty();
    }

    public static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) (dp * (metrics.densityDpi / 160f));
    }

    public static float getSizeFromResourcesId(int resourceId, Context context) {
        return context.getResources().getDimension(resourceId);
    }

    public static void setUpforKeyboard(View p_view, final Activity activity) {
        if (p_view == null || activity == null) {
            return;
        }
        if (!(p_view instanceof EditText)) {
            p_view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }
        if (p_view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) p_view).getChildCount(); i++) {
                View innerView = ((ViewGroup) p_view).getChildAt(i);
                setUpforKeyboard(innerView, activity);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity == null) return;
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    @SuppressWarnings("deprecation")
    public static int getColor(@NonNull Context context, int resourceId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return context.getResources().getColor(resourceId);
        } else {
            return context.getResources().getColor(resourceId, null);
        }
    }

    public static void openKeyBoard(Context context, View view) {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (view != null) {
            inputManager.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public static void refreshFragment(Fragment fragment)
    {
        FragmentActivity activity = fragment.getActivity();
        if(activity !=null&& activity.getSupportFragmentManager()!=null) {
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            ft.detach(fragment);
            ft.attach(fragment);
            ft.commit();
        }
    }

}
