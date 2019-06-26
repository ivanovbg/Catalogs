package com.softomotion.catalogs.brochure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;

import android.os.Bundle;
import android.view.View;

import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.core.adapters.BrochureImagesAdapter;
import com.softomotion.catalogs.core.brochure.BrochureView;
import com.softomotion.catalogs.core.brochure.presenter.BrochurePresenter;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.brochure.Brochure;
import com.softomotion.catalogs.data.api.models.brochure.PagesItem;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.ActivityBrochureBinding;
import com.softomotion.catalogs.utils.CommonUtils;
import com.softomotion.catalogs.utils.SnapHelperOneByOne;

import java.util.List;

public class BrochureActivity extends AppCompatActivity implements BrochureView {
    private ActivityBrochureBinding binding;
    private BrochurePresenter<BrochureActivity> brochurePresenter;
    private DataManager dataManager;
    private Api api;
    private Integer brochureId;
    private DatabaseInstance db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_brochure);
        setSupportActionBar(binding.appBar.toolbar);
        dataManager = ((Catalogs)getApplication()).getDataManager();
        api = ((Catalogs) getApplication()).getApiManager();
        db = ((Catalogs) getApplication()).getDatabaseInstance();
        brochurePresenter = new BrochurePresenter<>(dataManager, api, db);
        brochurePresenter.onAttach(this);
        brochureId = getIntent().getExtras().getInt("brochure_id");

        CommonUtils.animateView(binding.progressOverlay.customProgressOverlay, View.VISIBLE, 0.4f, 200);
        brochurePresenter.getBrochure(brochureId);
    }


    @Override
    public void loadBrochure(Brochure brochure) {

        getSupportActionBar().setSubtitle(brochure.getBrand().getName() + " до " + CommonUtils.convertDate(brochure.getValidity().getEnd()));

        CommonUtils.animateView(binding.progressOverlay.customProgressOverlay, View.GONE, 0.4f, 200);
        LinearLayoutManager layout  = new LinearLayoutManager(BrochureActivity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.brochureImageView.setLayoutManager(layout);
        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        linearSnapHelper.attachToRecyclerView(binding.brochureImageView);
        BrochureImagesAdapter mAdapter = new BrochureImagesAdapter(this, brochure.getPages());
        binding.brochureImageView.setAdapter(mAdapter);
    }


}
