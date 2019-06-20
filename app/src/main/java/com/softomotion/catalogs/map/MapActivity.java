package com.softomotion.catalogs.map;



import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.ActivityMapBinding;
import com.softomotion.catalogs.map.models.MapPin;
import com.softomotion.catalogs.map.presenter.MapPresenter;
import com.softomotion.catalogs.utils.MapPinRender;


import java.util.HashMap;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapView, ClusterManager.OnClusterClickListener<MapPin> {

    private GoogleMap mMap;
    private ActivityMapBinding binding;
    private DataManager dataManager;
    private Api api;
    private MapPresenter<MapActivity> mapPresenter;

    private HashMap<String, Double> worldCoordinates = new HashMap<String, Double>(){{
        put("top_left_lat", 85.0);
        put("top_left_long", -180.0);
        put("bottom_right_lat", -85.0);
        put("bottom_right_long", 179.999999999);

    }};

    private ClusterManager<MapPin> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        setSupportActionBar(binding.appBar.toolbar);

        dataManager = ((Catalogs)getApplication()).getDataManager();
        api = ((Catalogs) getApplication()).getApiManager();

        mapPresenter = new MapPresenter<MapActivity>(dataManager, api);
        mapPresenter.onAttach(this);


        binding.bottomNavigation.bottomNavigationView.setSelectedItemId(R.id.map);
        binding.bottomNavigation.bottomNavigationView.setOnNavigationItemSelectedListener(itemReselectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            if(menuItem.getItemId() == R.id.home){
                finish();
            }
            return false;
        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapPresenter.getCity(dataManager.getCityId());
    }

    @Override
    public void cityReady(City city){
        LatLng userCity = new LatLng(city.getLat(), city.getlLong());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userCity));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userCity,14));

        LatLng sydney = new LatLng(42.6791348, 23.3673909);
        mMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));

        //load pins over map
        mapPresenter.getPins(worldCoordinates);
    }

    @Override
    public void pinsReady(List<MapPin> pins) {
        mClusterManager = new ClusterManager<MapPin>(this, mMap);
        mClusterManager.setRenderer(new MapPinRender(getApplicationContext(), mMap, mClusterManager));
        mClusterManager.addItems(pins);

        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);

        mClusterManager.setOnClusterClickListener(this);
    }

    @Override
    public boolean onClusterClick(Cluster<MapPin> cluster) {
        LatLngBounds.Builder builder = LatLngBounds.builder();

        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }

        final LatLngBounds bounds = builder.build();
        try {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
