package com.softomotion.catalogs.core.main.presenter;

import com.softomotion.catalogs.core.base.Presenter;
import com.softomotion.catalogs.core.main.MainView;

public interface MainPresenterInterface <V extends MainView> extends Presenter<V> {
    void loadCities();
}
