package com.softomotion.catalogs.core.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softomotion.catalogs.R;

public class BrochuresImageHolder extends RecyclerView.ViewHolder{
    public ImageView image;

    public BrochuresImageHolder(@NonNull View itemView) {
        super(itemView);
        this.image = itemView.findViewById(R.id.brochure_medium_image);
    }
}
