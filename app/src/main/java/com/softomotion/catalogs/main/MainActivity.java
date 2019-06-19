package com.softomotion.catalogs.main;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.cities.Cities;
import com.softomotion.catalogs.main.adapters.CitiesAdapter;
import com.softomotion.catalogs.main.presenter.MainPressenter;
import com.softomotion.catalogs.map.MapActivity;
import com.softomotion.catalogs.main.adapters.Pager;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainView  {
    private ActivityMainBinding binding;
    private Pager pagerAdapter;


    private DataManager dataManager;
    private Api api;

    private CitiesAdapter citiesAdapter;

    private MainPressenter<MainActivity> mainPressenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.appBar.toolbar);

        pagerAdapter = new Pager(getSupportFragmentManager(), binding.tabLayout.getTabCount());
        setupPager();


        dataManager  = ((Catalogs)getApplication()).getDataManager();
        api = ((Catalogs)getApplication()).getApiManager();


        mainPressenter = new MainPressenter<>(dataManager, api);
        mainPressenter.onAttach(this);
        mainPressenter.getCities();


        binding.bottomNavigation.bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }

    @Override
    public void setupPager(){
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
    public void citiesReady(List<Cities> cities) {
        citiesAdapter = new CitiesAdapter(this, cities);
        binding.appBar.citiesList.setAdapter(citiesAdapter);
        binding.appBar.citiesList.setSelection(citiesAdapter.getCityPosition(dataManager.getCityId()));
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

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            if(menuItem.getItemId() == R.id.map){
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
            return false;
        }
    };

}
