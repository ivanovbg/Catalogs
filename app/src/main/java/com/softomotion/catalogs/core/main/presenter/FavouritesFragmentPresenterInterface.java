package com.softomotion.catalogs.core.main.presenter;

import com.softomotion.catalogs.core.base.Presenter;
import com.softomotion.catalogs.core.main.FavouritesFragmentView;

public interface FavouritesFragmentPresenterInterface <V extends FavouritesFragmentView> extends Presenter<V> {
    void loadBrochures();
    void unLikeBrochure(Integer brochure_id);
}
