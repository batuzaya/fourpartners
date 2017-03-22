package com.charpixel.fourthpartnerenergy.PlantListModule;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.AppSession;
import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.databinding.PlantsListFragmentBinding;
import com.charpixel.fourthpartnerenergy.Dashboard;
import com.charpixel.fourthpartnerenergy.Models.CurrentPlant;
import com.charpixel.fourthpartnerenergy.PlantListModule.Dialogs.DatePicker;
import com.charpixel.fourthpartnerenergy.PlantListModule.Dialogs.SortPlantsDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import javax.inject.Inject;

/**
 * Created by ashu on 13-12-2016.
 */


public class PlantListFragment extends BaseFragment implements PlantListContract.View  {

    PlantsListFragmentBinding binding;

    ArrayList<Plant> mModels;
    private String TAG = this.getClass().getSimpleName();

    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    private PlantsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String searchText;


    @Inject
    PlantListPresenter presenter;

    @Inject
    CurrentPlant currentPlant;

    @Inject
    AppSession appSession;

    Boolean isPullToRefresh = false;

    private static final Comparator<Plant> ALPHABETICAL_COMPARATOR = new Comparator<Plant>() {
        @Override
        public int compare(Plant a, Plant b) {
            return 0;
        }
    };

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.plants_list_fragment, container, false);

        presenter.attachView(this);

        if(appSession.getPlantArrayList() == null)
        {
            presenter.populatePlants();
            mModels = new ArrayList<>();
        }else {
            mModels = appSession.getPlantArrayList();
        }


        // binding.recyclerView.setHasFixedSize(true);
         mLayoutManager = new LinearLayoutManager(getActivity());
       // mLayoutManager = new GridLayoutManager(getActivity(),2);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PlantsAdapter(getActivity(), ALPHABETICAL_COMPARATOR, binding.recyclerView, new PlantsAdapter.Listener() {
            @Override
            public void onPlantClick(Plant plant) {

                Log.v(TAG,"Click On Plant");
                currentPlant.setPlant(plant);
                ((Dashboard)getActivity()).showDetailView(plant);
            }

            @Override
            public void onLoadMore() {
                Log.v(TAG,"load moree plzzz");
                presenter.loadMorePlant();

            }
        });
        binding.recyclerView.setAdapter(mAdapter);


        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.populatePlants();
            isPullToRefresh = true;
            binding.loader.setVisibility(View.INVISIBLE);
        });






        mAdapter.edit()
                .replaceAll(mModels)
                .commit();

        binding.sortMenu.setOnClickListener(view -> {
            // Create the fragment and show it as a dialog.

            DialogFragment newFragment = SortPlantsDialog.newInstance((sortType, sortItem) -> {
                Log.v(TAG,"callback recieved"+sortItem);

                presenter.populatePlantsSorting(sortType,sortItem);
            });

            newFragment.show(getFragmentManager(), "sort_plant_dialog");
        });
        binding.search.setOnClickListener(view -> {
            Log.v(TAG,"clicked on search");
           // ((Dashboard)getActivity()).showMenuBar(true);
            binding.searchBar.setVisibility(View.VISIBLE);
            binding.header.setVisibility(View.GONE);
        });

        binding.closeSearchBar.setOnClickListener(view -> {
            binding.searchBar.setVisibility(View.GONE);
            binding.header.setVisibility(View.VISIBLE);
        });

        binding.searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchPlant(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//        binding.searchText.setOnClickListener(view -> {
//            binding.searchText.setVisibility(View.GONE);
//            showHeader();
//        });

        binding.searchText.setOnCloseListener(() -> {

            return false;
        });

        binding.calander.setOnClickListener(view -> {
            DatePicker newFragment = new DatePicker();
            newFragment.setListener((date, year , month , day) -> {

                this.year = year;
                this.month = month;
                this.day = day;

                binding.currentDate.setText(getDate());

                Log.v(TAG,"dateSelected   : "+date);
                presenter.setDate(date);
                presenter.populatePlants();

            });

            newFragment.setDate(year,month,day);
            newFragment.show(getFragmentManager(), "datePicker");
        });

        binding.currentDate.setText(getDate());


        return binding.getRoot();
    }


    String getDate(){
        return  day+"/"+(month+1)+"/"+year;
    }

    public static PlantListFragment getInstance() {
        PlantListFragment plantListFragment = new PlantListFragment() ;
        Bundle bundle = new Bundle();
        plantListFragment.setArguments(bundle);
        return plantListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }




    public void searchPlant(String searchQuery){

        searchText = searchQuery;

        presenter.populatePlantsSearch(searchQuery);



        Log.v(TAG,"searching "+searchQuery);
    }




    @Override
    public void RefreshPlantList(ArrayList<Plant> plants) {

        if(!checkIfListEmpty(plants))
        {
            mAdapter.edit()
                    .replaceAll(plants)
                    .commit();
            binding.recyclerView.scrollToPosition(0);
        }


    }

    @Override
    public void appendPlantList(ArrayList<Plant> data) {
        mAdapter.edit().add(data).commit();
    }

    @Override
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

    boolean checkIfListEmpty(ArrayList<Plant> data){
        if(data.size() > 0)
        {
            binding.emptyListView.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
            return false;
        }else{

            binding.emptyListView.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
            binding.searchTextView.setText('"'+searchText+'"');
            return  true;
        }
    }


    public void showHeader() {
        binding.header.setVisibility(View.VISIBLE);
        presenter.populatePlants();
    }
}
