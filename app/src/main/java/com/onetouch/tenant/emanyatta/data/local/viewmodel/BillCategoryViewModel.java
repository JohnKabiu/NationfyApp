package com.onetouch.tenant.emanyatta.data.local.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apps.nationfy.R;
import com.onetouch.tenant.emanyatta.data.local.model.BillCategory;

import java.util.ArrayList;
import java.util.List;

public class BillCategoryViewModel extends ViewModel {
    private MutableLiveData<List<BillCategory>> billCategoryList;

    public LiveData<List<BillCategory>> getBillCategoryList() {
        if (billCategoryList == null) {
            billCategoryList = new MutableLiveData<>();
            loadBills();
        }
        return billCategoryList;
    }

    private void loadBills() {
        List<BillCategory> billAddList = new ArrayList<>();

        BillCategory c = new BillCategory();
        c.setCategoryPicture(R.drawable.ic_kplc_token);
        c.setCategoryName("KPLC - (Pre-paid)");

        BillCategory e = new BillCategory();
        e.setCategoryPicture(R.drawable.ic_kplc_postpaid);
        e.setCategoryName("KPLC - (Post-Paid)");

        BillCategory d = new BillCategory();
        d.setCategoryPicture(R.drawable.ic_airtime_category);
        d.setCategoryName("Airtime");

        billAddList.add(c);
        billAddList.add(e);
        billAddList.add(d);

        billCategoryList.setValue(billAddList);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
