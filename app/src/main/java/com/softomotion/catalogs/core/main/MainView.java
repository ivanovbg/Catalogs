package com.softomotion.catalogs.core.main;

import com.softomotion.catalogs.core.base.View;
import com.softomotion.catalogs.data.api.models.cities.Cities;

import java.util.List;

public interface MainView extends View {
    void citiesReady(List<Cities> cities);
    void setupPager();
}
