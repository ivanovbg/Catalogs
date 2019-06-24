package com.softomotion.catalogs.core.brochure.presenter;

import com.softomotion.catalogs.core.base.BasePresenter;
import com.softomotion.catalogs.core.brochure.BrochureView;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.brochure.Response;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;

import retrofit2.Call;
import retrofit2.Callback;

public class BrochurePresenter <V extends BrochureView> extends BasePresenter<V> implements BrochurePresenterInterface<V> {

    public BrochurePresenter(DataManager dataManager, Api api, DatabaseInstance db) {
        super(dataManager, api, db);
    }

    @Override
    public void getBrochure(Integer brochure_id) {
        getApi().getServices().getBrochure(brochure_id).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                getmView().loadBrochure(response.body().getResponse().getBrochure());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
