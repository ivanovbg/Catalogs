package com.softomotion.catalogs.core.brochure.presenter;

import com.softomotion.catalogs.core.base.Presenter;
import com.softomotion.catalogs.core.brochure.BrochureView;

public interface BrochurePresenterInterface <V extends BrochureView> extends Presenter<V> {
    void loadBrochure(Integer brochure_id);
}
