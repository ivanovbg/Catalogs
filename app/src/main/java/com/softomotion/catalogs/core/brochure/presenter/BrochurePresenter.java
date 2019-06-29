package com.softomotion.catalogs.core.brochure.presenter;

import com.softomotion.catalogs.core.base.BasePresenter;
import com.softomotion.catalogs.core.brochure.BrochureView;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.brochure.BrochureResponse;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrochurePresenter <V extends BrochureView> extends BasePresenter<V> implements BrochurePresenterInterface<V> {

    public BrochurePresenter(DataManager dataManager, Api api, DatabaseInstance db) {
        super(dataManager, api, db);
    }

    @Override
    public void loadBrochure(Integer brochure_id) {
        getApi().getBrochure(brochure_id, new Callback<BrochureResponse>() {
            @Override
            public void onResponse(Call<BrochureResponse> call, Response<BrochureResponse> response) {
                if(response.isSuccessful()){
                    getmView().showBrochure(response.body().getBrochureResponse().getBrochure());
                }
            }

            @Override
            public void onFailure(Call<BrochureResponse> call, Throwable t) {
                getmView().showError();
            }
        });
    }
}
