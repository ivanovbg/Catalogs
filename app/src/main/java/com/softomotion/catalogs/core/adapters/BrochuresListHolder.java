package com.softomotion.catalogs.core.adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softomotion.catalogs.R;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;


public class BrochuresListHolder extends  RecyclerView.ViewHolder{
    public ImageView brochureImage;
    public ImageButton likeBtn;
    private BrochuresItem brochure;
    public TextView brandName;

    public BrochuresListHolder(@NonNull View itemView, BrochureItemClickListener brochureItemClickListener) {
        super(itemView);
        this.brochureImage = itemView.findViewById(R.id.brochure_image);
        this.likeBtn = itemView.findViewById(R.id.brochure_like_btn);
        this.brandName = itemView.findViewById(R.id.brand_name);

        this.brochureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brochureItemClickListener.onBrochureClick(brochure.getId());
            }
        });

        this.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brochureItemClickListener.onBrochureLike(brochure, itemView);
            }
        });
    }

    public void setBrochureId(BrochuresItem brochure){
        this.brochure = brochure;
    }

    public interface BrochureItemClickListener{
        void onBrochureClick(Integer brochure_id);
        void onBrochureLike(BrochuresItem brochuresItem, View itemView);
    }
}
