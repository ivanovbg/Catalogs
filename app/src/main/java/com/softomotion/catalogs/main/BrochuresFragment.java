package com.softomotion.catalogs.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.brochure.BrochureActivity;
import com.softomotion.catalogs.core.adapters.BrochuresListAdapter;
import com.softomotion.catalogs.core.adapters.BrochuresListHolder;
import com.softomotion.catalogs.core.main.BrochuresFragmentView;
import com.softomotion.catalogs.core.main.presenter.BrochuresFragmentPresenter;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.FragmentBrochuresBinding;
import com.softomotion.catalogs.map.MapActivity;
import com.softomotion.catalogs.utils.CommonUtils;
import com.softomotion.catalogs.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BrochuresFragment extends Fragment implements BrochuresFragmentView, MainActivity.brochuresFragmentListener {

    private DataManager dataManager;
    private Api api;
    private DatabaseInstance db;
    private BrochuresFragmentPresenter<BrochuresFragment> brochuresFragmentPresenter;
    private FragmentBrochuresBinding binding;
    private RecyclerView brochuresRecycleView;
    private BrochuresListAdapter brochuresListAdapter;

    public static BrochuresFragment newInstance() {
        BrochuresFragment fragment = new BrochuresFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Catalogs catalogs = (Catalogs) getActivity().getApplication();
        dataManager = catalogs.getDataManager();
        api = catalogs.getApiManager();
        db = catalogs.getDatabaseInstance();

        brochuresFragmentPresenter = new BrochuresFragmentPresenter<>(dataManager, api, db);
        brochuresFragmentPresenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_brochures, container, false);
        binding.brochureRecycleView.brochureRecycleView.setVisibility(View.VISIBLE);
        brochuresRecycleView = binding.brochureRecycleView.brochureRecycleView;
        brochuresRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return binding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((MainActivity) getActivity()).registerBrochuresFragmentListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume(){
        super.onResume();

        if(brochuresListAdapter != null) {
            reloadData();
        }
    }

    @Override
    public void showBrochures(List<BrochuresItem> brochuresItems, List<Integer> likeBrochures) {
        CommonUtils.animateView(binding.progressOverlay.customProgressOverlay, View.GONE, 0.4f, 200);
        if(brochuresListAdapter == null) {
            brochuresListAdapter = new BrochuresListAdapter(getContext(), brochuresItems, brochureItemClickListener);
            brochuresRecycleView.setAdapter(brochuresListAdapter);
        }else{
            brochuresListAdapter.updateData(brochuresItems);
        }
        brochuresListAdapter.likeBrochures = likeBrochures;
    }

    private BrochuresListHolder.BrochureItemClickListener brochureItemClickListener = new BrochuresListHolder.BrochureItemClickListener() {
        @Override
        public void onBrochureClick(Integer brochure_id) {
            Intent intent = new Intent(getActivity().getBaseContext(), BrochureActivity.class);
            intent.putExtra("brochure_id", brochure_id);
            startActivity(intent);
        }

        @Override
        public void onBrochureLike(BrochuresItem brochuresItem, View itemView) {
            boolean is_like = (itemView.findViewById(R.id.brochure_like_btn).isActivated()) ? true : false;
            boolean status = is_like ? false : true;

            if(is_like){
                brochuresFragmentPresenter.unLikeBrochure(brochuresItem.getId());
                itemView.findViewById(R.id.brochure_like_btn).setActivated(status);
                ((MainActivity)getActivity()).favouritesFragmentListener.reloadData();
                if(brochuresListAdapter.likeBrochures.contains(brochuresItem.getId())){
                    brochuresListAdapter.likeBrochures.remove(Integer.valueOf(brochuresItem.getId()));
                }
                brochuresListAdapter.unlikeBrochures.add(brochuresItem.getId());
            }else{
                brochuresFragmentPresenter.likeBrochure(brochuresItem);
                itemView.findViewById(R.id.brochure_like_btn).setActivated(status);
                ((MainActivity)getActivity()).favouritesFragmentListener.reloadData();
                if(brochuresListAdapter.unlikeBrochures.contains(brochuresItem.getId())){
                    brochuresListAdapter.unlikeBrochures.remove(Integer.valueOf(brochuresItem.getId()));
                }
                brochuresListAdapter.likeBrochures.add(brochuresItem.getId());
            }
        }
    };


    @Override
    public void reloadData() {
        if(!NetworkUtils.isNetworkConnected(getContext())){
            ((MainActivity)getActivity()).showError();
            return;
        }


        CommonUtils.animateView(binding.progressOverlay.customProgressOverlay, View.VISIBLE, 0.4f, 200);
        brochuresFragmentPresenter.loadBrochures(dataManager.getUserCityId());
    }
}
