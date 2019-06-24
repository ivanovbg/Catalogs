package com.softomotion.catalogs.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.core.AppConsts;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;
import com.softomotion.catalogs.data.database.entities.Brochure;

import java.util.List;


public class BrochuresListFavouritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Brochure> brochures;
    private BrochuresListFavouritesHolder.BrochureItemClickListener brochureItemClickListener;


    public BrochuresListFavouritesAdapter(Context context, List<Brochure> brochures, BrochuresListFavouritesHolder.BrochureItemClickListener brochureItemClickListener){
        this.brochures = brochures;
        this.context = context;
        this.brochureItemClickListener = brochureItemClickListener;
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
                .error(R.drawable.ic_launcher_background)
                .into(hol.brochure_image)
        ;


        hol.setBrochureId(brochures.get(position));
        hol.likeBtn.setActivated(true);
        hol.brand_name.setText(brochures.get(position).getBrochure_name());
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
