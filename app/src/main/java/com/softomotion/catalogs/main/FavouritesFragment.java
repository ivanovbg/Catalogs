package com.softomotion.catalogs.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.brochure.BrochureActivity;
import com.softomotion.catalogs.core.adapters.BrochuresListFavouritesAdapter;
import com.softomotion.catalogs.core.adapters.BrochuresListFavouritesHolder;
import com.softomotion.catalogs.core.main.FavouritesFragmentView;
import com.softomotion.catalogs.core.main.presenter.FavouritesFragmentPresenter;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.database.entities.Brochure;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.FragmentFavouritesBinding;

import java.util.List;

public class FavouritesFragment extends Fragment implements FavouritesFragmentView, MainActivity.favouritesFragmentListener {

    private DataManager dataManager;
    private Api api;
    private DatabaseInstance db;
    private FavouritesFragmentPresenter<FavouritesFragment> favouritesFragmentPresenter;
    private FragmentFavouritesBinding binding;
    private RecyclerView brochuresRecycleView;
    private BrochuresListFavouritesAdapter brochuresListFavouritesAdapter;

    public static FavouritesFragment newInstance() {
        FavouritesFragment fragment = new FavouritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Catalogs catalogs = (Catalogs) getActivity().getApplication();
        dataManager = catalogs.getDataManager();
        api = catalogs.getApiManager();
        db = catalogs.getDatabaseInstance();

        favouritesFragmentPresenter = new FavouritesFragmentPresenter<>(dataManager, api, db);
        favouritesFragmentPresenter.onAttach(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false);
        favouritesFragmentPresenter.getBrochures();

        binding.brochureRecycleView.brochureRecycleView.setVisibility(View.VISIBLE);
        brochuresRecycleView = binding.brochureRecycleView.brochureRecycleView;
        brochuresRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return binding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)getActivity()).registerFavouriteFragmentListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void loadBrochures(List<Brochure> brochures) {
       if(brochuresListFavouritesAdapter == null){
           brochuresListFavouritesAdapter = new BrochuresListFavouritesAdapter(getContext(), brochures, brochureItemClickListener);
           brochuresRecycleView.setAdapter(brochuresListFavouritesAdapter);
       }else {
           brochuresListFavouritesAdapter.updateData(brochures);
       }
    }

    @Override
    public void reloadData() {
        favouritesFragmentPresenter.getBrochures();
    }

    private BrochuresListFavouritesHolder.BrochureItemClickListener brochureItemClickListener = new BrochuresListFavouritesHolder.BrochureItemClickListener() {
        @Override
        public void onBrochureClick(Integer brochure_id) {
            Intent intent = new Intent(getActivity().getBaseContext(), BrochureActivity.class);
            intent.putExtra("brochure_id", brochure_id);
            startActivity(intent);
        }

        @Override
        public void onBrochureLik(Brochure brochuresItem, View itemView) {
            favouritesFragmentPresenter.unLikeBrochure(brochuresItem.getBrochure_id());
        }
    };
}
