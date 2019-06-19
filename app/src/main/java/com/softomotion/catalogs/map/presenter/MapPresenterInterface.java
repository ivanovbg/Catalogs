package com.softomotion.catalogs.map.presenter;


import com.softomotion.catalogs.base.Presenter;
import com.softomotion.catalogs.map.MapView;

import java.util.HashMap;

public interface MapPresenterInterface<V extends MapView> extends Presenter<V> {
    void getCity(Integer city_id);
    void getPins(HashMap<String, Double> coordinates);
}
