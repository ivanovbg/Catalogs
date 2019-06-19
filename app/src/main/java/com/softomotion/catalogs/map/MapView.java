package com.softomotion.catalogs.map;

import com.softomotion.catalogs.base.View;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.map.models.MapPin;

import java.util.List;

public interface MapView extends View {
    void cityReady(City city);
    void pinsReady(List<MapPin> pins);
}
