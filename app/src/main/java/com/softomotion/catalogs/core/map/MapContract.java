package com.softomotion.catalogs.core.map;

import com.softomotion.catalogs.base.Presenter;
import com.softomotion.catalogs.base.View;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.map.MapView;
import com.softomotion.catalogs.map.models.MapPin;

import java.util.HashMap;
import java.util.List;

public interface MapContract {


    interface MapPresenterInterface<V extends com.softomotion.catalogs.map.MapView> extends Presenter<V> {
        void getCity(Integer city_id);
        void getPins(HashMap<String, Double> coordinates);
    }



    interface MapView extends View {
        void cityReady(City city);
        void pinsReady(List<MapPin> pins);
    }

}
