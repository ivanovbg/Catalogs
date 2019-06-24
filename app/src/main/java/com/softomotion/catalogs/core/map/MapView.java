package com.softomotion.catalogs.core.map;

import com.softomotion.catalogs.core.base.View;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.map.models.MapPin;

import java.util.List;

public interface MapView extends View {
    void showCity(City city);
    void showPins(List<MapPin> pins);
    void showBrochures(List<BrochuresItem> brochures, List<Integer> likedBrochures);
}
