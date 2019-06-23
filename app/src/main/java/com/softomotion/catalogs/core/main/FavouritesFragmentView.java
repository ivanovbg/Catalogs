package com.softomotion.catalogs.core.main;

import com.softomotion.catalogs.core.base.View;
import com.softomotion.catalogs.data.database.entities.Brochure;

import java.util.List;

public interface FavouritesFragmentView extends View {
    void loadBrochures(List<Brochure> brochures);
    void reloadData();
}
