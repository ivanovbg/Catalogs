package com.softomotion.catalogs.base;

public interface Presenter<V extends View> {
    void onAttach(V mvpView);
}
