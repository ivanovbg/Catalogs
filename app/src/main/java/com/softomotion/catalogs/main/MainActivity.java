package com.softomotion.catalogs.main;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.core.main.MainView;
import com.softomotion.catalogs.core.main.presenter.MainPresenter;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.cities.Cities;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.core.adapters.CitiesAdapter;
import com.softomotion.catalogs.map.MapActivity;
import com.softomotion.catalogs.utils.CommonUtils;
import com.softomotion.catalogs.utils.NetworkUtils;
import com.softomotion.catalogs.utils.Pager;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainView {
    private ActivityMainBinding binding;
    private Pager pagerAdapter;
    private DataManager dataManager;
    private Api api;
    private DatabaseInstance db;
    private CitiesAdapter citiesAdapter;
    private MainPresenter<MainActivity> mainPressenter;
    public brochuresFragmentListener brochuresFragmentListener;
    public favouritesFragmentListener favouritesFragmentListener;
    private List<Cities> cities;
    private boolean errorDialogShowed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.appBar.toolbar);

        pagerAdapter = new Pager(getSupportFragmentManager(), binding.tabLayout.getTabCount(),
                new String[]{getResources().getString(R.string.brochures), getResources().getString(R.string.favourites)});
        setupPager();


        dataManager = ((Catalogs) getApplication()).getDataManager();
        api = ((Catalogs) getApplication()).getApiManager();
        db = ((Catalogs) getApplication()).getDatabaseInstance();


        mainPressenter = new MainPresenter<>(dataManager, api, db);
        mainPressenter.onAttach(this);
        binding.bottomNavigation.bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (!NetworkUtils.isNetworkConnected(this)) {
            showError();
        } else {
            mainPressenter.loadCities();
        }
    }

    @Override
    public void setupPager() {
        binding.pager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.pager);
        binding.tabLayout.addOnTabSelectedListener(onTabSelectedListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void showCities(List<Cities> cities) {
        this.cities = cities;
        citiesAdapter = new CitiesAdapter(this, this.cities);
        binding.appBar.citiesList.setAdapter(citiesAdapter);
        binding.appBar.citiesList.setSelection(citiesAdapter.getCityPosition(dataManager.getUserCityId()));
        binding.appBar.citiesList.setOnItemSelectedListener(citySelectListener);
    }


    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            binding.pager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    private AdapterView.OnItemSelectedListener citySelectListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            dataManager.setUserCityId(cities.get(position).getId());
            brochuresFragmentListener.reloadData();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.map) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
            return false;
        }
    };

    @Override
    public void showError() {
        if (errorDialogShowed) {
            return;
        }

        CommonUtils.showError(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                errorDialogShowed = false;
                brochuresFragmentListener.reloadData();
                favouritesFragmentListener.reloadData();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                errorDialogShowed = false;
                finish();
            }
        }).show();

        errorDialogShowed = true;
    }

    public void registerBrochuresFragmentListener(brochuresFragmentListener listener) {
        this.brochuresFragmentListener = listener;
    }

    public void registerFavouriteFragmentListener(favouritesFragmentListener listener) {
        this.favouritesFragmentListener = listener;
    }

    public interface brochuresFragmentListener {
        void reloadData();
    }

    public interface favouritesFragmentListener {
        void reloadData();
    }
}
