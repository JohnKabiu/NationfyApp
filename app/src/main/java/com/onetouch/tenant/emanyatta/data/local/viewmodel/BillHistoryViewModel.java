package com.onetouch.tenant.emanyatta.data.local.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apps.nationfy.Constants.BaseApp;
import com.onetouch.tenant.emanyatta.data.local.AppDatabase;
import com.onetouch.tenant.emanyatta.data.local.model.BillHistory;
import com.onetouch.tenant.emanyatta.data.network.APIClient;
import com.onetouch.tenant.emanyatta.data.network.RequestInterface;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillHistoryViewModel extends ViewModel {
    private LiveData<List<BillHistory>> billList;

    public LiveData<List<BillHistory>> getBillList(Context context) {
        if (billList == null) {
            billList = new MutableLiveData<>();
            loadBills(context);
            AppDatabase appDatabase = AppDatabase.getDatabase(context);
            billList = appDatabase.billHistoryDao().getAll();
        }
        return billList;
    }

    private void loadBills(Context context) {
        BaseApp app = (BaseApp) Objects.requireNonNull(context).getApplicationContext();
        RequestInterface requestInterface = APIClient.getClient(app.settings.getBearerToken()).create(RequestInterface.class);
        requestInterface.getHistoryBills().enqueue(new Callback<List<BillHistory>>() {
            @Override
            public void onResponse(@NonNull Call<List<BillHistory>> call, @NonNull Response<List<BillHistory>> response) {

                AppDatabase appDatabase = AppDatabase.getDatabase(context);
                appDatabase.billHistoryDao().insertBillHistories(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<BillHistory>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
