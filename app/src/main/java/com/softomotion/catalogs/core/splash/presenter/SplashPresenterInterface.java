package com.softomotion.catalogs.core.splash.presenter;

import android.location.Location;

import com.softomotion.catalogs.core.base.Presenter;
import com.softomotion.catalogs.core.splash.SplashView;

import java.util.HashMap;

public interface SplashPresenterInterface<V extends SplashView> extends Presenter<V> {
    void findCity(Location location);
}
