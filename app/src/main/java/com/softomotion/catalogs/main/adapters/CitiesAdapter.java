package com.softomotion.catalogs.main.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.softomotion.catalogs.R;
import com.softomotion.catalogs.data.api.models.cities.Cities;

import java.util.List;


public class CitiesAdapter extends BaseAdapter {

    private Context context;
    private List<Cities> cities;
    private LayoutInflater layoutInflater;

    public CitiesAdapter(Context context, List<Cities> cities) {
        this.context = context;
        this.cities = cities;
        this.layoutInflater = (LayoutInflater.from(context));
    }


    @Override
    public int getCount(){
        return cities.size();
    }

    @Override
    public Cities getItem(int position){
        return cities.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    public int getCityPosition(@Nullable Integer city_id) {
        for (int i=0;i<cities.size();i++){
            if (city_id == cities.get(i).getId()){
                return i;
            }
        }
        return 0;
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.city_spinner_item, null);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.city_spinner_dropdown_item, null);
        TextView cityName = (TextView) convertView.findViewById(R.id.spinner_city_name);
        cityName.setText(cities.get(position).getName());
        return convertView;
    }
}
