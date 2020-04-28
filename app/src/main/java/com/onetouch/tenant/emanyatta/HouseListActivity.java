package com.onetouch.tenant.emanyatta;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.onetouch.tenant.emanyatta.data.local.adapter.HouseAdapter;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.HouseViewModel;

public class HouseListActivity extends AppCompatActivity {

    RecyclerView rvHouses;
    HouseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_list);
        fixAdapter();
    }

    private void fixAdapter() {
        HouseViewModel model = ViewModelProviders.of(this).get(HouseViewModel.class);
        model.getHouseList(this).observe(this, complainList -> {
            rvHouses = (RecyclerView) findViewById(R.id.rvHouses);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            rvHouses.setLayoutManager(layoutManager);
            mAdapter = new HouseAdapter(this, complainList);
            rvHouses.setAdapter(mAdapter);
        });
    }
}
