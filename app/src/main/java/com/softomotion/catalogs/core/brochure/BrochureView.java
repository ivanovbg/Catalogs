package com.softomotion.catalogs.core.brochure;

import com.softomotion.catalogs.core.base.View;
import com.softomotion.catalogs.data.api.models.brochure.Brochure;
import com.softomotion.catalogs.data.api.models.brochure.PagesItem;

import java.util.List;

public interface BrochureView extends View {
    void showBrochure(Brochure brochure);
    void showError();
}
