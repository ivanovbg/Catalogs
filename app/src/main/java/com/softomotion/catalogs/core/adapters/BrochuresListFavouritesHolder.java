package com.softomotion.catalogs.core.adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softomotion.catalogs.R;
import com.softomotion.catalogs.data.database.entities.Brochure;

public class BrochuresListFavouritesHolder extends  RecyclerView.ViewHolder{
    public ImageView brochure_image;
    public ImageButton likeBtn;
    private Brochure brochure;

    public BrochuresListFavouritesHolder(@NonNull View itemView, BrochuresListFavouritesHolder.BrochureItemClickListener brochureItemClickListener) {
        super(itemView);
        this.brochure_image = itemView.findViewById(R.id.brochure_image);
        this.likeBtn = itemView.findViewById(R.id.brochure_like_btn);

        this.brochure_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brochureItemClickListener.onBrochureClick(brochure.getBrochure_id());
            }
        });

        this.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brochureItemClickListener.onBrochureLik(brochure, itemView);
            }
        });
    }

    public void setBrochureId(Brochure brochure){
        this.brochure = brochure;
    }

    public interface BrochureItemClickListener{
        void onBrochureClick(Integer brochure_id);
        void onBrochureLik(Brochure brochuresItem, View itemView);
    }
}
