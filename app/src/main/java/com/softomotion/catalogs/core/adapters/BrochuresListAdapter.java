package com.softomotion.catalogs.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;

import java.util.ArrayList;
import java.util.List;


public class BrochuresListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<BrochuresItem> brochures;
    private BrochuresListHolder.BrochureItemClickListener brochureItemClickListener;
    public ArrayList<Integer> likeBrochures = new ArrayList<>();
    public ArrayList<Integer> unlikeBrochures = new ArrayList<>();

    public BrochuresListAdapter(Context context, List<BrochuresItem> brochures, BrochuresListHolder.BrochureItemClickListener brochureItemClickListener){
        this.brochures = brochures;
        this.context = context;
        this.brochureItemClickListener = brochureItemClickListener;
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
                .load("https://static.broshura.bg/img/" + brochures.get(position).getPages().get(0).getImage().getMedium())
                .error(R.drawable.ic_launcher_background)
                .into(hol.brochure_image)
        ;

        hol.setBrochureId(brochures.get(position));

        if(brochures.get(position).isIsLiked() || likeBrochures.contains(brochures.get(position).getId())){
            hol.likeBtn.setActivated(true);
        }else if(!brochures.get(position).isIsLiked() || unlikeBrochures.contains(brochures.get(position).getId())){
            hol.likeBtn.setActivated(false);
        }
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
