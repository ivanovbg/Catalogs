package com.softomotion.catalogs.core.base;

public interface Presenter<V extends View> {
    void onAttach(V mvpView);
}
