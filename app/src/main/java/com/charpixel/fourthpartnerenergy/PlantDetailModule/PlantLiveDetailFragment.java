package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.databinding.PlantDetailLiveBinding;
import com.charpixel.fourthpartnerenergy.Models.CurrentPlant;
import com.charpixel.fourthpartnerenergy.Models.Inverter_;
import com.charpixel.fourthpartnerenergy.Models.Meter_;
import com.charpixel.fourthpartnerenergy.PlantDetailModule.Adapters.EnergyMeterAdapter;
import com.charpixel.fourthpartnerenergy.PlantDetailModule.Adapters.inverterAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ashu on 19-12-2016.
 */


public class PlantLiveDetailFragment extends BaseFragment {

    String TAG = "PlantLiveDetailFragment";
    PlantDetailLiveBinding binding;
    @Inject
    CurrentPlant currentPlant;

    ArrayList<Inverter_> inverters = new ArrayList<>();
    ArrayList<Meter_> meters = new ArrayList<>();

    Boolean isPullToRefresh = false;

    private inverterAdapter mAdapter;
    private EnergyMeterAdapter energyMeterAdapter;

    private RecyclerView.LayoutManager mLayoutManager  , energyMeterLayoutManager;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      binding =  DataBindingUtil.inflate(inflater, R.layout.plant_detail_live, container, false);
        initInverterRecyclerView();
        initEnergyMeterRecyclerView();


        binding.swipeRefreshLayout.setOnRefreshListener(() -> {


            refresh();
            isPullToRefresh = true;
            binding.loader.setVisibility(View.INVISIBLE);
            showLoader(!isPullToRefresh);
        });

            refresh();
        return binding.getRoot();


    }





    void initInverterRecyclerView(){
        mLayoutManager = new LinearLayoutManager(getActivity());
        binding.inverterList.inverters.setLayoutManager(mLayoutManager);
        mAdapter = new inverterAdapter(getActivity(), null, companyRate -> {

        });




//        inverters.add(new Inverter());
//        inverters.add(new Inverter());
//        inverters.add(new Inverter());

        binding.inverterList.inverters.setAdapter(mAdapter);

        mAdapter.edit()
                .replaceAll(inverters)
                .commit();
    }


    void initEnergyMeterRecyclerView(){
        energyMeterLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.energyMeterList.energyMetersRecyclerView.setLayoutManager(energyMeterLayoutManager);
        energyMeterAdapter = new EnergyMeterAdapter(getActivity(), null, companyRate -> {

        });




//        inverters.add(new Inverter());
//        inverters.add(new Inverter());
//        inverters.add(new Inverter());

        binding.energyMeterList.energyMetersRecyclerView.setAdapter(energyMeterAdapter);

        energyMeterAdapter.edit()
                .replaceAll(meters)
                .commit();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }



    void setLayoutWidth(int maxWidth){



        if(currentPlant.getPlantDetail() != null){

            Display display = getActivity().getWindowManager().getDefaultDisplay();
            int width = (display.getWidth() );

            RelativeLayout relMain = binding.powerLayout.powerLayout;
            RelativeLayout.LayoutParams currentParams = (RelativeLayout.LayoutParams)relMain.getLayoutParams();
            LinearLayout.LayoutParams currentParamsParant = (LinearLayout.LayoutParams) binding.powerLayout.parantLayout.getLayoutParams();


            Log.e(TAG, "refresh: "+currentParams.width+","+currentParams.height+","+width );
            Log.e(TAG, "refresh2: "+currentParamsParant.width+","+currentParamsParant.height+","+width );
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            Float d = (maxWidth*currentPlant.getPlantDetail().getPowerPercentFraction());
            params.width = d.intValue();
            params.height =(int) getResources().getDimension(R.dimen.bluebar);//120
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            relMain.setLayoutParams(params);

        }

    }


    public void refresh(){

        Log.v(TAG,"refressing plant detail 222 ");


            binding.setModel(currentPlant.getPlantDetail());


if(currentPlant.getPlantDetail() != null && currentPlant.getPlantDetail().getSlideDown().getEnergyMeterNow().get(0).getMeters() != null)
{
    meters  = currentPlant.getPlantDetail().getSlideDown().getEnergyMeterNow().get(0).getMeters();
    energyMeterAdapter.edit()
            .replaceAll(meters)
            .commit();
}
   if(currentPlant.getPlantDetail() != null && currentPlant.getPlantDetail().getSlideDown().getInvertersData().get(1).getInverters() != null)
   {

       inverters  = currentPlant.getPlantDetail().getSlideDown().getInvertersData().get(1).getInverters();

       mAdapter.edit()
               .replaceAll(inverters)
               .commit();
   }


      //  binding.powerLayout.powerLayout.
//


//        int height = (display.getHeight() );
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams )binding.powerLayout.powerLayout.getLayoutParams();

        try {



            binding.powerLayout.parantLayout.post(new Runnable() {
                @Override
                public void run() {

                    int mWidth = binding.powerLayout.parantLayout.getWidth();
                    Log.d("tag", "Width of box :" + mWidth);

                    setLayoutWidth(mWidth);

                }
            });


        }catch (Exception e){
            e.printStackTrace();

        }


        //initInverterRecyclerView();
        //initEnergyMeterRecyclerView();


        Log.v(TAG,"after refressing plant detail");


    }

    public void showLoader(boolean isShowLoader) {
        if(isShowLoader){

            if(!isPullToRefresh)
            {
                binding.loader.setVisibility(View.VISIBLE);
            }


        }

        else {
            binding.swipeRefreshLayout.setRefreshing(false);
            binding.loader.setVisibility(View.INVISIBLE);
            isPullToRefresh = false;
        }


    }


}
