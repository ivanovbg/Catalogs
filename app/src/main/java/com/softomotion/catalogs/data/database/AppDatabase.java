package com.softomotion.catalogs.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.softomotion.catalogs.data.database.daos.BrochureDao;
import com.softomotion.catalogs.data.database.entities.Brochure;

@Database(entities = {Brochure.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BrochureDao brochureDao();
}