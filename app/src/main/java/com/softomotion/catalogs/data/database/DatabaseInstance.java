package com.softomotion.catalogs.data.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.softomotion.catalogs.core.AppConsts;
import com.softomotion.catalogs.data.database.entities.Brochure;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInstance {

    private final AppDatabase db;
    private static DatabaseInstance instance;

    public static DatabaseInstance getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseInstance(context);
        }
        return instance;
    }

    private DatabaseInstance(Context context) {
        db = Room
                .databaseBuilder(context, AppDatabase.class, AppConsts.DATABASE_NAME)
                .build();
    }


    public void likeBrochure(Brochure brochure) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.brochureDao().likeBrochure(brochure);
                return null;
            }
        }.execute();
    }

    public void unlikeBrochure(Integer brochure_id) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.brochureDao().removeFromFavourite(brochure_id);
                return null;
            }
        }.execute();
    }


    public void getFavouritesBrochures(DatabaseListener<List<Brochure>> callback) {
        new AsyncTask<Void, Void, List<Brochure>>() {
            @Override
            protected List<Brochure> doInBackground(Void... voids) {
                List<Brochure> brochures = db.brochureDao().getFavouriteBrochures();
                return brochures;
            }

            @Override
            protected void onPostExecute(List<Brochure> brochures) {
                super.onPostExecute(brochures);
                callback.onFavouriteBrochuresLoaded(brochures);
            }
        }.execute();
    }

    public void getLikedBrochures(DatabaseListener<List<Integer>> callback) {
        new AsyncTask<Void, Void, List<Integer>>() {
            @Override
            protected List<Integer> doInBackground(Void... voids) {
                List<Integer> brochures = db.brochureDao().likedBrochures();
                return brochures;
            }

            @Override
            protected void onPostExecute(List<Integer> brochures) {
                super.onPostExecute(brochures);
                callback.onFavouriteBrochuresLoaded(brochures);
            }
        }.execute();
    }


    public interface DatabaseListener<T> {
        void onFavouriteBrochuresLoaded(T data);
    }


}