package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.databinding.PlantDetailFragmentBinding;
import com.charpixel.fourthpartnerenergy.Models.CurrentPlant;
import com.charpixel.fourthpartnerenergy.PlantListModule.Plant;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ashu on 15-12-2016.
 */

public class PlantDetailFragment extends BaseFragment implements  PlantDetailContract.View {

    private Plant plant;

    @Inject
    CurrentPlant currentPlant;

    PlantDetailFragmentBinding binding;

    PlantLiveDetailFragment live = new PlantLiveDetailFragment();
    PlantSubDatailFragment detail =  new PlantSubDatailFragment();
    PlantArchiveDetailFragment archive = new PlantArchiveDetailFragment();

    @Inject
    PlantDetailPresenter plantDetailPresenter;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.plant_detail_fragment, container, false);


        setupViewPager(binding.viewpager);
        binding.tabs.setupWithViewPager(binding.viewpager);
        plantDetailPresenter.attachView(this);

        initialise();

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }





    void initialise(){

        plantDetailPresenter.getPlantDetail(plant.getId());

    }
    public static PlantDetailFragment getInstance() {
        PlantDetailFragment plantDetailFragment = new PlantDetailFragment() ;
        Bundle bundle = new Bundle();
        plantDetailFragment.setArguments(bundle);
        return plantDetailFragment;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(live, "Live");
        adapter.addFragment(archive,"Archive");
        adapter.addFragment(detail, "Details");

        viewPager.setAdapter(adapter);
    }
    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    @Override
    public void showLoader(boolean isShowLoader) {

    }

    @Override
    public void refresh() {

        binding.plantName.setText(currentPlant.getPlantDetail().getPlantName());
        binding.header.setVisibility(View.GONE);

        detail.refresh();
        live.refresh();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
