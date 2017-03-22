package com.charpixel.fourthpartnerenergy.PlantListModule.Dialogs;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.databinding.SortPlantsDialogBinding;

/**
 * Created by ashu on 15-12-2016.
 */

public class SortPlantsDialog extends DialogFragment {

    public interface MyDialogCloseListener
    {
        public void handleDialogClose(int sortType , String sortItem);
    }


    public interface OnItemClick
    {
        public void onClick(String sortItem);
    }
    public class sortKeys {
        public String name = "plantName";
        public String city = "city";
        public String state = "state";
        public String capacity = "plantCapacity";

    }

    MyDialogCloseListener listener ;

    int sortType = 1;

    SortPlantsDialogBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.sort_plants_dialog, container, false);

        binding.close.setOnClickListener(view -> {
            dismiss();
        });

        binding.setListener(sortItem -> {
            dismiss();
            listener.handleDialogClose(sortType,sortItem);
        });
        binding.setModel(new sortKeys());

        binding.sortType.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i){
                case R.id.asc:
                    sortType = 1;
                    break;
                case R.id.desc:
                    sortType = -1;
                    break;
            }

        });
        return binding.getRoot();
    }
    public static SortPlantsDialog newInstance(MyDialogCloseListener listener) {
        SortPlantsDialog frag = new SortPlantsDialog();
        Bundle args = new Bundle();
        //args.putInt("title", title);
        frag.setArguments(args);
        frag.DismissListner(listener);
        return frag;
    }

    public void DismissListner(MyDialogCloseListener closeListener){
        this.listener = closeListener;
    }




}
