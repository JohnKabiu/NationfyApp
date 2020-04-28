package com.onetouch.tenant.emanyatta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.Constants.BaseApp;
import com.apps.nationfy.R;
import com.onetouch.tenant.emanyatta.data.local.adapter.BillCategoryAdapter;
import com.onetouch.tenant.emanyatta.data.local.adapter.HouseAdapter;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.BillCategoryViewModel;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.HouseViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreloginActivity extends AppCompatActivity {

    RecyclerView rvHouses, rvBillCategories;
    HouseAdapter mAdapter;
    BillCategoryAdapter adapter;
    @BindView(R.id.btnLogin)
    TextView btnLogin;

    @BindView(R.id.btnLogin2)
    TextView btnLogin2;

    private BaseApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelogin);

        ButterKnife.bind(this);

        app = (BaseApp) getApplicationContext();

        if (app.settings.IsloggedIn()) {
            Intent launchNextActivity;
            launchNextActivity = new Intent(this, com.onetouch.tenant.emanyatta.MainActivity.class);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(launchNextActivity);
        }

        btnLogin.setOnClickListener(v -> {

            Intent launchNextActivity;
            launchNextActivity = new Intent(this, AuthenticationActivity.class);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(launchNextActivity);

        });

        btnLogin2.setOnClickListener(v -> {

            Intent launchNextActivity;
            launchNextActivity = new Intent(this, com.onetouch.tenant.emanyatta.MainActivity.class);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(launchNextActivity);

        });

        fixAdapter();
    }

    private void fixAdapter() {
        HouseViewModel model = ViewModelProviders.of(this).get(HouseViewModel.class);
        model.getHouseList(this).observe(this, houses -> {
            rvHouses = (RecyclerView) findViewById(R.id.rvHouses);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
            ((GridLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
            rvHouses.setLayoutManager(layoutManager);
            mAdapter = new HouseAdapter(this, houses);
            rvHouses.setAdapter(mAdapter);
        });

        BillCategoryViewModel m = ViewModelProviders.of(this).get(BillCategoryViewModel.class);
        m.getBillCategoryList().observe(this, billList -> {
            rvBillCategories = (RecyclerView) findViewById(R.id.rvBillCategories);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
            ((GridLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
            rvBillCategories.setLayoutManager(layoutManager);
            adapter = new BillCategoryAdapter(this, billList);
            rvBillCategories.setAdapter(adapter);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (app.settings.IsloggedIn()) {
            Intent launchNextActivity;
            launchNextActivity = new Intent(this, com.onetouch.tenant.emanyatta.MainActivity.class);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(launchNextActivity);
        }
    }
}
