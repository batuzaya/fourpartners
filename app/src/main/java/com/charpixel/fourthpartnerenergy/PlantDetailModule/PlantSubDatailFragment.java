package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.databinding.PlantDetailLayoutBinding;
import com.charpixel.fourthpartnerenergy.Models.CurrentPlant;
import com.charpixel.fourthpartnerenergy.Models.Inverter;
import com.charpixel.fourthpartnerenergy.PlantDetailModule.Adapters.InverterTypeMAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ashu on 19-12-2016.
 */

public class PlantSubDatailFragment extends BaseFragment {

    String TAG = "PlantSubDatailFragment";

    PlantDetailLayoutBinding binding;

    ArrayList<Inverter> inverters = new ArrayList<>();

    private RecyclerView.LayoutManager mLayoutManager  ;

    private InverterTypeMAdapter mAdapter;

    @Inject
    CurrentPlant currentPlant;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding =  DataBindingUtil.inflate(inflater, R.layout.plant_detail_layout, container, false);


        initInverterRecyclerView();
        if( currentPlant.getPlantDetail() != null)
        {
            refresh();
        }


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    public void refresh(){

        Log.v(TAG,"refressing plant detail");
        binding.setModel(currentPlant.getPlantDetail());

        if(currentPlant.getPlantDetail().getDeviceMac().get(0) != null){
            inverters  = currentPlant.getPlantDetail().getDeviceMac().get(0).getInverters();
            mAdapter.edit()
                    .replaceAll(inverters)
                    .commit();
        }



    }


    void initInverterRecyclerView(){
        mLayoutManager = new LinearLayoutManager(getActivity());
        binding.inverterList.panels.setLayoutManager(mLayoutManager);
        mAdapter = new InverterTypeMAdapter(getActivity(), null, companyRate -> {

        });




//        inverters.add(new Inverter());
//        inverters.add(new Inverter());
//        inverters.add(new Inverter());

        binding.inverterList.panels.setAdapter(mAdapter);

        mAdapter.edit()
                .replaceAll(inverters)
                .commit();
    }






}
