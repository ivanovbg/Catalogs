package com.softomotion.catalogs.main.presenter;

import com.softomotion.catalogs.base.Presenter;
import com.softomotion.catalogs.main.MainView;

public interface MainPressenterInterface<V extends MainView> extends Presenter<V> {
    void getCities();
}
