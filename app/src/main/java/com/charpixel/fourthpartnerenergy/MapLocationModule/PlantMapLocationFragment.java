package com.charpixel.fourthpartnerenergy.MapLocationModule;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.AppSession;
import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.databinding.ActivityMapsBinding;
import com.charpixel.baseandroidproject.databinding.PlantItemMapBinding;
import com.charpixel.fourthpartnerenergy.PlantListModule.Plant;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.charpixel.baseandroidproject.R.id.map;

/**
 * Created by ashu on 23-12-2016.
 */

public class PlantMapLocationFragment extends BaseFragment implements OnMapReadyCallback {

    private  final String TAG = this.getClass().getSimpleName() ;
    private GoogleMap mMap;


    @Inject
    AppSession appSession;


    LayoutInflater inflater;
    ViewGroup container;

    ArrayList<Marker> markers ;

    ActivityMapsBinding binding;
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_maps,container,false);

        this.container = container;
        this.inflater = inflater;

       binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {

               Log.e(TAG, "onQueryTextSubmit: " );
               findMarker(query);
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               return false;
           }
       });




        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        return binding.getRoot();
    }



    void findMarker(String name){

        try {
            for(Marker m : markers){
                Plant p = (Plant)m.getTag();
                Log.e(TAG, "findMarker: "+p.getPlantName() +"," +p.getPlantName().contains(name) );
                if(p.getPlantName() != null && p.getPlantName().toLowerCase().contains(name.toLowerCase()))
                {
                    Log.e(TAG, "findMarker: "+p.getPlantName() );
                    m.showInfoWindow();
                    moveCameraToLatLong(p.getLocation().get(0),p.getLocation().get(1));
                    return;
                }
                //something here
            }

        }catch (Error e){

        }


    }


    public static PlantMapLocationFragment getInstance() {
        PlantMapLocationFragment plantMap = new PlantMapLocationFragment() ;
        Bundle bundle = new Bundle();
        plantMap.setArguments(bundle);
        return plantMap;
    }


    void moveCameraToLatLong(Double lat , Double lng){
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.e(TAG, "onMapReady: " );

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {


                Plant p = (Plant) marker.getTag();
//
                PlantItemMapBinding plantItemBinding =   PlantItemMapBinding.inflate(inflater, container, false);
//                plantItemBinding.setModel(p);



                plantItemBinding.energyLifetime.setText(p.getEnergyLifetime()+"");
                plantItemBinding.plantName.setText(p.getPlantName());
                plantItemBinding.energyToday.setText(p.getEnergyToday()+"kWh @ ");
                plantItemBinding.pr.setText(p.getPr());
                plantItemBinding.energyRate.setText(p.getKwhkwp()+"kWh/kWp");
                plantItemBinding.capacity.setText(p.getPlantCapacity()+" kW");
                plantItemBinding.globe.setImageResource( p.getCommStatus() == 0 ? R.drawable.red_globe : p.getCommStatus() == 1 ?  R.drawable.yellow_golbe : R.drawable.green_globe

                );



//
//                // Getting reference to the TextView to set longitude
//                TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
//
//                // Setting the latitude
//                tvLat.setText("Latitude:" + p.getPlantName());
//
//                // Setting the longitude
//                tvLng.setText("Longitude:"+ latLng.longitude);

                // Returning the view containing InfoWindow contents
                return plantItemBinding.getRoot();
            }
        });

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if(appSession.getPlantArrayList() != null){
            markers = new ArrayList<>();
            for (Plant p : appSession.getPlantArrayList()){




                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(p.getLocation().get(0), p.getLocation().get(1));


                builder.include(sydney);



                Marker m = mMap.addMarker(new  MarkerOptions().position(sydney).title(p.getPlantName()));

                int drawableId = p.getCommStatus() == 0 ? R.drawable.map_pin_red : p.getCommStatus() == 1 ?  R.drawable.map_pin_yellow : R.drawable.map_pin_green;
                m.setIcon(BitmapDescriptorFactory.fromResource(drawableId));

                markers.add(m);

                m.setTag(p);


            }
        }

        LatLngBounds bounds = builder.build();


        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.10); // offset from edges of the map 12% of screen
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,width,height, padding);

       // mMap.moveCamera(cu);
        mMap.animateCamera(cu);



    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }


}
