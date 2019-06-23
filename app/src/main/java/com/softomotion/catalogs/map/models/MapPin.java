package com.softomotion.catalogs.map.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;


public class MapPin implements ClusterItem {
    private LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private String mImage;
    private int mBrandId;


    public MapPin(LatLng location, String title, String snippet, String image, int brandId) {
        mPosition = location;
        mTitle = title;
        mSnippet = snippet;
        mImage = image;
        mBrandId = brandId;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }


    public String getTitle() { return mTitle; }


    public String getSnippet() { return mSnippet; }


    public void setTitle(String title) {
        mTitle = title;
    }


    public void setSnippet(String snippet) {
        mSnippet = snippet;
    }


    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public int getmBrandId() {
        return mBrandId;
    }

    public void setmBrandId(int mBrandId) {
        this.mBrandId = mBrandId;
    }
}