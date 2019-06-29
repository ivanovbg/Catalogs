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
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;
import com.softomotion.catalogs.data.database.entities.Brochure;
import com.softomotion.catalogs.utils.CommonUtils;

import java.util.List;


public class BrochuresListFavouritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Brochure> brochures;
    private BrochuresListFavouritesHolder.BrochureItemClickListener brochureItemClickListener;
    private CircularProgressDrawable circularProgressDrawable;


    public BrochuresListFavouritesAdapter(Context context, List<Brochure> brochures, BrochuresListFavouritesHolder.BrochureItemClickListener brochureItemClickListener){
        this.brochures = brochures;
        this.context = context;
        this.brochureItemClickListener = brochureItemClickListener;
        this.circularProgressDrawable = CommonUtils.circularProgressDrawable(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View brochure_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.brochure_item, parent, false);
        BrochuresListFavouritesHolder brochureHolder = new BrochuresListFavouritesHolder(brochure_item, brochureItemClickListener);
        return brochureHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BrochuresListFavouritesHolder hol = (BrochuresListFavouritesHolder) holder;

        Glide.with(context)
                .load(AppConsts.STATIC_DOMAIN + brochures.get(position).getBrochure_image())
                .error(R.drawable.ic_brochure_image)
                .placeholder(circularProgressDrawable)
                .into(hol.brochureImage)
        ;


        hol.setBrochureId(brochures.get(position));
        hol.likeBtn.setActivated(true);
        hol.brandName.setText(brochures.get(position).getBrochure_name());
    }

    @Override
    public int getItemCount() {
        return brochures.size();
    }


    public void updateData(List<Brochure> brs){
        this.brochures.clear();
        this.brochures.addAll(brs);
        this.notifyDataSetChanged();
    }

}
