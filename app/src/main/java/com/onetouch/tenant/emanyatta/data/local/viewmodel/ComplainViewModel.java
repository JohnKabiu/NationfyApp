package com.onetouch.tenant.emanyatta.data.local.viewmodel;

import com.onetouch.tenant.emanyatta.data.local.model.Complain;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ComplainViewModel extends ViewModel {
    private MutableLiveData<List<Complain>> complainList;

    public LiveData<List<Complain>> getComplainList() {
        if (complainList == null) {
            complainList = new MutableLiveData<>();
            loadComplains();
        }
        return complainList;
    }

    private void loadComplains() {
            List<Complain> complainAddList = new ArrayList<>();

            Complain c = new Complain();
            c.setApartmentName("Mountain View Apartment");
            c.setComplainName("No Water!");
            c.setHouseNumber("C6");
            c.setDateRecorded("4 January, 2018");
            c.setComplainStatus("Resolved");
            c.setDateResolved("8 December, 2018");
            c.setComplainDescription("I don't Have water in my apartment. Please fix it as soon as possible");

            Complain d = new Complain();
            d.setApartmentName("Johari Apartment");
            d.setComplainName("Roof Leaking");
            d.setHouseNumber("E1");
            d.setDateRecorded("4 January, 2018");
            d.setComplainStatus("Resolved");
            d.setDateResolved("8 December, 2018");
            d.setComplainDescription("My apartment has a leak in the roof. Please fix it as soon as possible");

            complainAddList.add(c);
            complainAddList.add(d);
//            long seed = System.nanoTime();
//            Collections.shuffle(complainAddList, new Random(seed));
            complainList.setValue(complainAddList);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
