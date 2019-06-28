package com.softomotion.catalogs.core.main.presenter;

import com.softomotion.catalogs.core.base.BasePresenter;
import com.softomotion.catalogs.core.main.BrochuresFragmentView;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresResponse;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.database.entities.Brochure;
import com.softomotion.catalogs.data.prefs.DataManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrochuresFragmentPresenter  <V extends BrochuresFragmentView> extends BasePresenter<V> implements BrochuresFragmentPresenterInterface<V> {
    public BrochuresFragmentPresenter(DataManager dataManager, Api api, DatabaseInstance db) {
        super(dataManager, api, db);
    }

    @Override
    public void loadBrochures(Integer city_id) {
        getApi().getBrochures(city_id, new Callback<BrochuresResponse>() {
            @Override
            public void onResponse(Call<BrochuresResponse> call, Response<BrochuresResponse> response) {
                if(response.isSuccessful()){
                    responseBrochures(response.body().getBrochuresResponse().getBrochures());
                }
            }

            @Override
            public void onFailure(Call<BrochuresResponse> call, Throwable t) {

            }
        });
    }

    private void responseBrochures(List<BrochuresItem> brochuresItems){
        getDb().getLikedBrochures(new DatabaseInstance.DatabaseListener<List<Integer>>() {
            @Override
            public void onFavouriteBrochuresLoaded(List<Integer> data) {
                getmView().showBrochures(brochuresItems, data);
            }
        });
    }

    @Override
    public void likeBrochure(BrochuresItem brochuresItem) {
        Brochure brochure = new Brochure();
        brochure.setBrochure_id(brochuresItem.getId());
        brochure.setBrochure_name(brochuresItem.getBrand().getName());
        brochure.setBrochure_image(brochuresItem.getPages().get(0).getImage().getMedium());
        getDb().likeBrochure(brochure);
    }

    @Override
    public void unLikeBrochure(Integer brochure_id) {
        getDb().unlikeBrochure(brochure_id);
    }
}
