package com.softomotion.catalogs.main;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.cities.Cities;
import com.softomotion.catalogs.main.adapters.CitiesAdapter;
import com.softomotion.catalogs.map.MapActivity;
import com.softomotion.catalogs.utils.Pager;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity  {
    private ActivityMainBinding binding;
    private Pager pagerAdapter;

    private DataManager dataManager;

    private Api api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.appBar.toolbar);

        pagerAdapter = new Pager(getSupportFragmentManager(), binding.tabLayout.getTabCount());

        dataManager  = ((Catalogs)getApplication()).getDataManager();

        api = ((Catalogs)getApplication()).getApiManager();

        api.getCities(responseCallback);


        binding.pager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.pager);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });

        binding.bottomNavigation.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(menuItem.getItemId() == R.id.map){
                    Intent intent = new Intent(MainActivity.this, MapActivity.class);

                    startActivity(intent);
                }
                return false;
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    private Callback<List<Cities>> responseCallback = new Callback<List<Cities>>() {
        @Override
        public void onResponse(Call<List<Cities>> call, Response<List<Cities>> response) {
            Log.d("API", response.body().get(0).getName());

            CitiesAdapter citiesAdapter = new CitiesAdapter(MainActivity.this, response.body());
            binding.appBar.citiesList.setAdapter(citiesAdapter);
            binding.appBar.citiesList.setSelection(citiesAdapter.getCityPosition(dataManager.getCityId()));

            binding.appBar.citiesList.setOnItemSelectedListener(changeCityListener);
        }

        @Override
        public void onFailure(Call<List<Cities>> call, Throwable t) {

        }
    };

    private AdapterView.OnItemSelectedListener changeCityListener =  new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "YOU CHANGE CITY", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}
