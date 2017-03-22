package com.charpixel.fourthpartnerenergy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.common.BaseActivity;
import com.charpixel.baseandroidproject.databinding.DashboardBinding;
import com.charpixel.fourthpartnerenergy.MapLocationModule.PlantMapLocationFragment;
import com.charpixel.fourthpartnerenergy.PlantDetailModule.PlantDetailFragment;
import com.charpixel.fourthpartnerenergy.PlantListModule.Plant;
import com.charpixel.fourthpartnerenergy.PlantListModule.PlantListFragment;
import com.charpixel.fourthpartnerenergy.Reports.ReportsFragment;
import com.charpixel.fourthpartnerenergy.SettingModule.SettingFragment;

import static com.charpixel.baseandroidproject.R.id.map;

/**
 * Created by ashu on 13-12-2016.
 */

public class Dashboard extends BaseActivity implements SearchView.OnQueryTextListener  {

    public  static boolean isShowToolbar = false;

    private String TAG = this.getClass().getSimpleName();


    DashboardBinding binding;

    PlantListFragment plantListFragment ;

    PlantDetailFragment plantDetailFragment;

    PlantMapLocationFragment plantMapLocationFragment;
    ReportsFragment reportsFragment;
    SettingFragment settingFragment;

    private int currentFragent = R.id.dashboard;


    @Override
    public void onBackPressed() {
        Log.e("vishal","Radio current  id:"+binding.workingLayoutSelector.radioGroup.getCheckedRadioButtonId());

        //super.onBackPressed();


        if(binding.workingLayoutSelector.radioGroup.getCheckedRadioButtonId() == R.id.dashboard)
        {
            super.onBackPressed();
        }else {
            binding.workingLayoutSelector.radioGroup.check(R.id.dashboard);

        }

    }


    @Override
    protected void create(Bundle savedInstanceState) {


        binding = DataBindingUtil.setContentView(this, R.layout.dashboard);

        plantListFragment = PlantListFragment.getInstance();
        plantDetailFragment = PlantDetailFragment.getInstance();
        settingFragment = SettingFragment.getInstance();
        reportsFragment  = new ReportsFragment();

        plantMapLocationFragment = PlantMapLocationFragment.getInstance();
        addFragment(plantListFragment, R.id.dashboardRecyclerView,false);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initToolbar();




        binding.workingLayoutSelector.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i){
                case R.id.settings:
                    showMenuBar(false);
                    replaceFragment(settingFragment,R.id.dashboardRecyclerView,false);
                    break;
                case map:
                showMenuBar(false);
                replaceFragment(plantMapLocationFragment,R.id.dashboardRecyclerView,false);
                break;
                case R.id.dashboard:
                    replaceFragment(plantListFragment,R.id.dashboardRecyclerView,false);
                    break;
                case R.id.reports:
                    //replaceFragment(reportsFragment,R.id.dashboardRecyclerView,false);
                    break;

            }
        });


    }


    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        setToolbarTitle(getResources().getString(R.string.app_name));
        if(!showToolbar())
        {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.dashboard;
    }

    @Override
    public void showLoading(boolean show) {

    }



    @Override
    public void setToolbarTitle(String title) {
        super.setToolbarTitle("Powerplants");
    }


    @Override
    protected boolean showToolbar() {
        return isShowToolbar;
    }


    @Override
    protected void showToolbar(Boolean b) {
        if(b){
            getSupportActionBar().show();
        }else {
            getSupportActionBar().hide();
        }

    }


    public void showMenuBar(Boolean b){
        showToolbar(b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu, menu);
//
//        final MenuItem searchItem = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        //searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setIconified(false);
//        searchView.setOnQueryTextListener(this);
//        searchView.setOnCloseListener(this);


//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(searchView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        return false;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        plantListFragment.searchPlant(query);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                break;
        }
        return true;
    }

    public void showDetailView(Plant plant) {

        plantDetailFragment.setPlant(plant);
        replaceFragment(plantDetailFragment,R.id.dashboardRecyclerView,true);
    }
}
