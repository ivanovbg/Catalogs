package com.softomotion.catalogs.core.main.presenter;

import com.softomotion.catalogs.core.base.Presenter;
import com.softomotion.catalogs.core.main.BrochuresFragmentView;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;

public interface BrochuresFragmentPresenterInterface<V extends BrochuresFragmentView> extends Presenter<V> {
    void getBrochures(Integer city_id);
    void likeBrochure(BrochuresItem brochuresItem);
    void unLikeBrochure(Integer brochure_id);
}
