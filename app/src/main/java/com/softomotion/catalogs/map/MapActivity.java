package com.softomotion.catalogs.map;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.brochure.BrochureActivity;
import com.softomotion.catalogs.core.AppConsts;
import com.softomotion.catalogs.core.adapters.BrochuresListAdapter;
import com.softomotion.catalogs.core.adapters.BrochuresListHolder;
import com.softomotion.catalogs.core.map.MapView;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.ActivityMapBinding;
import com.softomotion.catalogs.databinding.CustomBrochuresPopupBinding;
import com.softomotion.catalogs.main.MainActivity;
import com.softomotion.catalogs.map.models.MapPin;
import com.softomotion.catalogs.core.map.presenter.MapPresenter;
import com.softomotion.catalogs.utils.CommonUtils;
import com.softomotion.catalogs.utils.MapPinRender;

import java.util.HashMap;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapView, ClusterManager.OnClusterClickListener<MapPin>, ClusterManager.OnClusterItemClickListener<MapPin> {

    private GoogleMap mMap;
    private ActivityMapBinding binding;
    private DataManager dataManager;
    private Api api;
    private DatabaseInstance db;
    private MapPresenter<MapActivity> mapPresenter;

    private Dialog brochuresDialog;
    private RecyclerView brochuresRecycleView;
    private BrochuresListAdapter brochuresListAdapter;
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
        db = ((Catalogs) getApplication()).getDatabaseInstance();

        mapPresenter = new MapPresenter<MapActivity>(dataManager, api, db);
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
    public void showCity(City city){
        LatLng userCity = new LatLng(city.getLat(), city.getlLong());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userCity));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userCity,14));
        mapPresenter.getPins(AppConsts.WORLD_COORDINATES);
    }

    @Override
    public void showPins(List<MapPin> pins) {
        mClusterManager = new ClusterManager<MapPin>(this, mMap);
        mClusterManager.setRenderer(new MapPinRender(getApplicationContext(), mMap, mClusterManager));
        mClusterManager.addItems(pins);

        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);

        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
    }

    @Override
    public void showBrochures(List<BrochuresItem> brochures, List<Integer> likedBrochures) {
        if(brochuresListAdapter == null){
            brochuresListAdapter = new BrochuresListAdapter(this, brochures, brochureItemClickListener);
            brochuresRecycleView.setAdapter(brochuresListAdapter);
        }else{
            brochuresListAdapter.updateData(brochures);
        }

        brochuresListAdapter.likeBrochures = likedBrochures;

        brochuresDialog.findViewById(R.id.progress_overlay).setVisibility(View.GONE);

        if(brochures.size() == 0){
            brochuresDialog.findViewById(R.id.no_brochure_text_view).setVisibility(View.VISIBLE);
        }else {
            brochuresDialog.findViewById(R.id.brochureRecycleView).setVisibility(View.VISIBLE);
        }


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


    @Override
    public boolean onClusterItemClick(MapPin mapPin) {
        setupBrochureDialog();
        Integer[] brands_filters = {mapPin.getmBrandId()};
        mapPresenter.getBrochures(dataManager.getCityId(), brands_filters);
        return false;
    }

    private void setupBrochureDialog(){
        if(brochuresDialog == null){
            brochuresDialog = new Dialog(this);
            brochuresDialog.setContentView(R.layout.custom_brochures_popup);
            brochuresRecycleView = brochuresDialog.findViewById(R.id.brochureRecycleView);
            brochuresRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
            brochuresDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }

        brochuresDialog.findViewById(R.id.progress_overlay).setVisibility(View.VISIBLE);
        brochuresDialog.findViewById(R.id.no_brochure_text_view).setVisibility(View.GONE);
        brochuresDialog.findViewById(R.id.brochureRecycleView).setVisibility(View.GONE);

        brochuresDialog.show();
    }


    private BrochuresListHolder.BrochureItemClickListener brochureItemClickListener = new BrochuresListHolder.BrochureItemClickListener() {
        @Override
        public void onBrochureClick(Integer brochure_id) {
            Intent intent = new Intent(MapActivity.this, BrochureActivity.class);
            intent.putExtra("brochure_id", brochure_id);
            startActivity(intent);
        }

        @Override
        public void onBrochureLik(BrochuresItem brochuresItem, View itemView) {
            if(itemView.findViewById(R.id.brochure_like_btn).isActivated()){
                itemView.findViewById(R.id.brochure_like_btn).setActivated(false);
                mapPresenter.unLikeBrochure(brochuresItem.getId());
                if(brochuresListAdapter.likeBrochures.contains(brochuresItem.getId())){
                    brochuresListAdapter.likeBrochures.remove(Integer.valueOf(brochuresItem.getId()));
                }
                brochuresListAdapter.unlikeBrochures.add(brochuresItem.getId());
            }else{
                mapPresenter.likeBrochure(brochuresItem);
                itemView.findViewById(R.id.brochure_like_btn).setActivated(true);
                if(brochuresListAdapter.unlikeBrochures.contains(brochuresItem.getId())){
                    brochuresListAdapter.unlikeBrochures.remove(Integer.valueOf(brochuresItem.getId()));
                }
                brochuresListAdapter.likeBrochures.add(brochuresItem.getId());
            }
        }
    };

}
