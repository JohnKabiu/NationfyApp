package com.onetouch.tenant.emanyatta;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.onetouch.tenant.emanyatta.data.local.adapter.BillCategoryAdapter;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.BillCategoryViewModel;

public class BillListActivity extends AppCompatActivity {

    RecyclerView rvBillCategories;
    BillCategoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);

        fixAdapter();
    }

    private void fixAdapter() {
        BillCategoryViewModel model = ViewModelProviders.of(this).get(BillCategoryViewModel.class);
        model.getBillCategoryList().observe(this, billList -> {
            rvBillCategories = (RecyclerView) findViewById(R.id.rvBillCategories);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
            rvBillCategories.setLayoutManager(layoutManager);
            mAdapter = new BillCategoryAdapter(this, billList);
            rvBillCategories.setAdapter(mAdapter);
        });
    }
}
