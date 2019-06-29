package com.softomotion.catalogs.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.core.AppConsts;
import com.softomotion.catalogs.data.api.models.brochure.Image;
import com.softomotion.catalogs.data.api.models.brochure.PagesItem;
import com.softomotion.catalogs.utils.CommonUtils;

import java.util.List;

public class BrochureImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<PagesItem> pages;
    private Context context;
    private CircularProgressDrawable circularProgressDrawable;

    public BrochureImagesAdapter(Context context, List<PagesItem> pages){
        this.pages = pages;
        this.context = context;
        this.circularProgressDrawable = CommonUtils.circularProgressDrawable(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View brochure = LayoutInflater.from(parent.getContext()).inflate(R.layout.brochure_image_item, parent, false);
        BrochuresImageHolder brochureHolder = new BrochuresImageHolder(brochure);
        return brochureHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BrochuresImageHolder hol = (BrochuresImageHolder) holder;

        Glide.with(context)
                .load(AppConsts.STATIC_DOMAIN + pages.get(position).getImage().getMedium())
                .error(R.drawable.ic_brochure_image)
                .placeholder(circularProgressDrawable)
                .into(hol.image)
        ;
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

}
