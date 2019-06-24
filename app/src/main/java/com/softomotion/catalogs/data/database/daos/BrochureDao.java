package com.softomotion.catalogs.data.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.softomotion.catalogs.data.database.entities.Brochure;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BrochureDao {
    @Insert
    void likeBrochure(Brochure brochure);

    @Query("SELECT * FROM brochure")
    List<Brochure> getFavouriteBrochures();

    @Query("SELECT brochure_id FROM brochure")
    List<Integer> likedBrochures();

    @Query("DELETE FROM brochure WHERE brochure_id = :brochure_id")
    void removeFromFavourite(Integer brochure_id);
}
