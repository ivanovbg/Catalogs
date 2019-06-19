package com.softomotion.catalogs.map.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;


public class MapPin implements ClusterItem {
    private LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private String mImage;

    public MapPin(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
        mTitle = null;
        mSnippet = null;
    }

    public MapPin(double lat, double lng, String title, String snippet, String image) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
        mImage = image;

    }

    public MapPin(LatLng location, String title, String snippet, String image) {
        mPosition = location;
        mTitle = title;
        mSnippet = snippet;
        mImage = image;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }


    public String getTitle() { return mTitle; }


    public String getSnippet() { return mSnippet; }

    /**
     * Set the title of the marker
     * @param title string to be set as title
     */
    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * Set the description of the marker
     * @param snippet string to be set as snippet
     */
    public void setSnippet(String snippet) {
        mSnippet = snippet;
    }


    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}