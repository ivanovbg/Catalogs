package com.softomotion.catalogs.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.map.models.MapPin;

public class MapPinRender extends DefaultClusterRenderer<MapPin> {
    private final IconGenerator mIconGenerator;
    private final ImageView mImageView;
    private final int mDimension;
    private Bitmap icon;
    private Context mContext;

    public MapPinRender(Context context, GoogleMap map, ClusterManager<MapPin> clusterManager) {
        super(context, map, clusterManager);
        mContext = context;
        mIconGenerator = new IconGenerator(context);
        mImageView = new ImageView(context);
        mDimension = (int) context.getResources().getDimension(R.dimen.custom_profile_image);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
        int padding = (int) context.getResources().getDimension(R.dimen.custom_profile_padding);
        mImageView.setPadding(padding, padding, padding, padding);
        mIconGenerator.setContentView(mImageView);
    }

    @Override
    protected void onBeforeClusterItemRendered(MapPin pin, MarkerOptions markerOptions) {
        icon = mIconGenerator.makeIcon();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(pin.getTitle());
    }

    @Override
    protected void onClusterItemRendered(MapPin clusterItem, final Marker marker) {

        Glide.with(mContext).
                load(clusterItem.getmImage())
                .asBitmap()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                    }
                });
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        return cluster.getSize() > 1;
    }
}