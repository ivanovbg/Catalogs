package com.softomotion.catalogs.core.adapters;

import android.content.Context;
import android.util.Log;
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
import com.softomotion.catalogs.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class BrochuresListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<BrochuresItem> brochures;
    private BrochuresListHolder.BrochureItemClickListener brochureItemClickListener;
    public List<Integer> likeBrochures = new ArrayList<>();
    public List<Integer> unlikeBrochures = new ArrayList<>();
    private CircularProgressDrawable circularProgressDrawable;

    public BrochuresListAdapter(Context context, List<BrochuresItem> brochures, BrochuresListHolder.BrochureItemClickListener brochureItemClickListener){
        this.brochures = brochures;
        this.context = context;
        this.brochureItemClickListener = brochureItemClickListener;
        this.circularProgressDrawable = CommonUtils.circularProgressDrawable(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View brochure_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.brochure_item, parent, false);
        BrochuresListHolder brochureHolder = new BrochuresListHolder(brochure_item, brochureItemClickListener);
        return brochureHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BrochuresListHolder hol = (BrochuresListHolder) holder;

        Glide.with(context)
                .load(AppConsts.STATIC_DOMAIN + brochures.get(position).getPages().get(AppConsts.DEFAULT_THUMB_IMAGE_ID).getImage().getMedium())
                .error(R.drawable.ic_brochure_image)
                .placeholder(circularProgressDrawable)
                .into(hol.brochureImage)
        ;

        hol.setBrochureId(brochures.get(position));
        hol.brandName.setText(brochures.get(position).getBrand().getName());

        boolean like_status = likeBrochures.contains(brochures.get(position).getId()) ? true : false;
        hol.likeBtn.setActivated(like_status);
    }

    @Override
    public int getItemCount() {
        return brochures.size();
    }


    public void updateData(List<BrochuresItem> brs){
        this.brochures.clear();
        this.brochures.addAll(brs);
        this.notifyDataSetChanged();
    }

}
