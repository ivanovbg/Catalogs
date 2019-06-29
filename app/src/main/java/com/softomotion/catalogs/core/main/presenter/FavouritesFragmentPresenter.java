package com.softomotion.catalogs.core.main.presenter;

import android.util.Log;

import com.softomotion.catalogs.core.base.BasePresenter;
import com.softomotion.catalogs.core.main.FavouritesFragmentView;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.database.entities.Brochure;
import com.softomotion.catalogs.data.prefs.DataManager;

import java.util.List;

public class FavouritesFragmentPresenter<V extends FavouritesFragmentView> extends BasePresenter<V> implements FavouritesFragmentPresenterInterface<V> {
    public FavouritesFragmentPresenter(DataManager dataManager, Api api, DatabaseInstance db) {
        super(dataManager, api, db);
    }

    @Override
    public void loadBrochures() {
        getDb().getFavouritesBrochures(new DatabaseInstance.DatabaseListener<List<Brochure>>() {
            @Override
            public void onFavouriteBrochuresLoaded(List<Brochure> data) {
                getmView().showBrochures(data);
            }
        });
    }

    @Override
    public void unLikeBrochure(Integer brochure_id) {
        getDb().unlikeBrochure(brochure_id);
    }
}
