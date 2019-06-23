package com.softomotion.catalogs.core.main;

import com.softomotion.catalogs.core.base.View;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;

import java.util.List;

public interface BrochuresFragmentView extends View {
    void loadBrochures(List<BrochuresItem> brochuresItems);
    void reloadData();
}
