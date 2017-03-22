package com.charpixel.baseandroidproject.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.charpixel.baseandroidproject.Application;

import java.util.Set;


public class PreferenceUtils {

    private static final String AppPreference = "PREFERENCES";

    private final static SharedPreferences preferences = Application._getContext().getSharedPreferences(AppPreference, Context.MODE_PRIVATE);

    public static String getString(String key) {
        return preferences.getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public static int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public static int getInt(String key, Integer defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public static boolean putString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean putLong(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public static long getLong(String key) {
        return getLong(key, 0);
    }

    public static boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static Set<String> getStringSet(String key, Set<String> defValue) {
        return preferences.getStringSet(key, defValue);

    }

    public static boolean putStringSet(String key, Set<String> value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }
}
