package com.charpixel.fourthpartnerenergy.PlantListModule.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.charpixel.baseandroidproject.R;

import java.util.Calendar;

/**
 * Created by ashu on 15-12-2016.
 */

public class DatePicker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    final Calendar c = Calendar.getInstance();
     int year = c.get(Calendar.YEAR);
     int month = c.get(Calendar.MONTH);
     int day = c.get(Calendar.DAY_OF_MONTH);


    public interface FragmentInteractionListener {
        void onDateSelect(String date , int year , int month , int day);
    }

    FragmentInteractionListener listener;

    public FragmentInteractionListener getListener() {
        return listener;
    }

    public void setListener(FragmentInteractionListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, day);
    }


   public void setDate(int year , int month , int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }



    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {

        listener.onDateSelect(i+"-"+(i1+1)+"-"+i2 , i , i1 , i2);

    }
}
