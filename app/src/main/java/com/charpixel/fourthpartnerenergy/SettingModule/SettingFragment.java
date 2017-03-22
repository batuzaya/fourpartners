package com.charpixel.fourthpartnerenergy.SettingModule;

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
import com.charpixel.baseandroidproject.databinding.SettingLayoutBinding;
import com.charpixel.fourthpartnerenergy.Models.CurrentPlant;
import com.charpixel.fourthpartnerenergy.PlantDetailModule.PlantDetailPresenter;
import com.charpixel.fourthpartnerenergy.PlantListModule.Plant;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ashu on 21-12-2016.
 */

public class SettingFragment extends BaseFragment  {

    private Plant plant;



    private SettingFragment fragment = null;

    @Inject
    CurrentPlant currentPlant;

    SettingLayoutBinding binding;

    SecurityFragment securityFragment = new SecurityFragment();
    ProfileFragment profileFragment =  new ProfileFragment();

    @Inject
    PlantDetailPresenter plantDetailPresenter;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.setting_layout, container, false);

        setupViewPager(binding.viewpager);
        binding.tabs.setupWithViewPager(binding.viewpager);

       // plantDetailPresenter.attachView(this);

        initialise();

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }





    void initialise(){

//        plantDetailPresenter.getPlantDetail(plant.getId());

    }
    public static SettingFragment getInstance() {

        SettingFragment settingFragment = new SettingFragment() ;
        Bundle bundle = new Bundle();
        settingFragment.setArguments(bundle);
        return settingFragment;
    }

    private void setupViewPager(ViewPager viewPager) {
        SettingFragment.ViewPagerAdapter adapter = new SettingFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(profileFragment, "PROFILE");
        adapter.addFragment(securityFragment, "SECURITY");
        viewPager.setAdapter(adapter);
    }
    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
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

