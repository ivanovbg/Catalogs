package com.softomotion.catalogs.ui.brochure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.softomotion.catalogs.Catalogs;
import com.softomotion.catalogs.R;
import com.softomotion.catalogs.core.adapters.BrochureImagesAdapter;
import com.softomotion.catalogs.core.brochure.BrochureView;
import com.softomotion.catalogs.core.brochure.presenter.BrochurePresenter;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.brochure.Brochure;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.databinding.ActivityBrochureBinding;
import com.softomotion.catalogs.utils.CommonUtils;
import com.softomotion.catalogs.utils.SnapUtils;


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

        dataManager = ((Catalogs) getApplication()).getDataManager();
        api = ((Catalogs) getApplication()).getApiManager();
        db = ((Catalogs) getApplication()).getDatabaseInstance();

        brochurePresenter = new BrochurePresenter<>(dataManager, api, db);
        brochurePresenter.onAttach(this);


        loadBrochure();
    }


    private void loadBrochure() {
        if (!CommonUtils.isNetworkConnected(this)) {
            showError();
            return;
        }

        brochureId = getIntent().getExtras().getInt("brochure_id");
        CommonUtils.animateView(binding.progressOverlay.customProgressOverlay, View.VISIBLE, 0.4f, 200);
        brochurePresenter.loadBrochure(brochureId);
    }


    @Override
    public void showBrochure(Brochure brochure) {
        getSupportActionBar().setSubtitle(brochure.getBrand().getName() + " " + getResources().getString(R.string.brochure_to) + " " + CommonUtils.convertDate(brochure.getValidity().getEnd()));
        CommonUtils.animateView(binding.progressOverlay.customProgressOverlay, View.GONE, 0.4f, 200);

        LinearLayoutManager layout = new LinearLayoutManager(BrochureActivity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.brochureImageView.setLayoutManager(layout);
        LinearSnapHelper linearSnapHelper = new SnapUtils();
        linearSnapHelper.attachToRecyclerView(binding.brochureImageView);
        BrochureImagesAdapter mAdapter = new BrochureImagesAdapter(this, brochure.getPages());
        binding.brochureImageView.setAdapter(mAdapter);
    }

    @Override
    public void showError() {
        CommonUtils.showError(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadBrochure();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }


}
