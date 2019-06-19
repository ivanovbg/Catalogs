package com.softomotion.catalogs.map;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.ActivityMapBinding;
import com.softomotion.catalogs.map.models.MapPin;
import com.softomotion.catalogs.map.presenter.MapPresenter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapView, ClusterManager.OnClusterClickListener<MapPin> {

    private GoogleMap mMap;
    private ActivityMapBinding binding;
    private DataManager dataManager;
    private Api api;
    private MapPresenter mapPresenter;
    private HashMap<String, Double> worldCoordinates = new HashMap<>();

    public static final LatLng WORLD_TOP_LEFT = new LatLng(85.0, -180.0);
    public static final LatLng WORLD_BOTTOM_RIGHT = new LatLng(-85.0, 179.999999999);

    private ClusterManager<MapPin> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setSupportActionBar(binding.appBar.toolbar);

        dataManager = ((Catalogs)getApplication()).getDataManager();
        api = ((Catalogs) getApplication()).getApiManager();

        mapPresenter = new MapPresenter(dataManager, api);
        mapPresenter.onAttach(this);

        worldCoordinates.put("top_left_lat", WORLD_TOP_LEFT.latitude);
        worldCoordinates.put("top_left_long", WORLD_TOP_LEFT.longitude);
        worldCoordinates.put("bottom_right_lat", WORLD_BOTTOM_RIGHT.latitude);
        worldCoordinates.put("bottom_right_long", WORLD_BOTTOM_RIGHT.longitude);


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

        mapPresenter.getPins(worldCoordinates);
    }

    @Override
    public void pinsReady(List<MapPin> pins) {
        mClusterManager = new ClusterManager<MapPin>(this, mMap);
        mClusterManager.setRenderer(new MapPinRender());
        mClusterManager.addItems(pins);

        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);

        mClusterManager.setOnClusterClickListener(this);
    }

    @Override
    public boolean onClusterClick(Cluster<MapPin> cluster) {
        //Toast.makeText(this, cluster.getSize(), Toast.LENGTH_SHORT).show();

        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        // Get the LatLngBounds
        final LatLngBounds bounds = builder.build();

        // Animate camera to the bounds
        try {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }


    private class MapPinRender extends DefaultClusterRenderer<MapPin> {
        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final int mDimension;
        Bitmap icon;

        MapPinRender() {
            super(getApplicationContext(), mMap, mClusterManager);

            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
        }

        @Override
        protected void onBeforeClusterItemRendered(MapPin pin, MarkerOptions markerOptions) {
            icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(pin.getTitle());

        }

        @Override
        protected void onClusterItemRendered(MapPin clusterItem, final Marker marker) {
            Log.d("API", clusterItem.getmImage());


            Glide.with(getApplicationContext()).
                    load(clusterItem.getmImage())
                    .asBitmap()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                            marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                        }
                    });
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }
    }
}
