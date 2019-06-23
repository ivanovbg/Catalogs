package com.softomotion.catalogs.core.map.presenter;


import com.softomotion.catalogs.core.base.Presenter;
import com.softomotion.catalogs.core.map.MapView;

import java.util.HashMap;

public interface MapPresenterInterface<V extends MapView> extends Presenter<V> {
    void getCity(Integer city_id);
    void getPins(HashMap<String, Double> coordinates);
    void getBrochures(Integer city_id, Integer brands_filters[]);
}
