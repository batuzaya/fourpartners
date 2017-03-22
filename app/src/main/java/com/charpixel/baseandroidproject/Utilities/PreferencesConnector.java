package com.charpixel.baseandroidproject.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesConnector {
    public  final String PREF_NAME = "CHARPIXEL_USER_PREFERENCES_COURIER_GENIE";
    public  final int MODE = Context.MODE_PRIVATE;

    SharedPreferences sharedPreferences;


    public PreferencesConnector(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public  void writeBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public  boolean readBoolean(Context context, String key, boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    public  void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();

    }

    public  int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public  void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public  void writeFloat(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).commit();
    }



    public  float readFloat(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public  void writeLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    public  long readLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);
    }

    public SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public  SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

}