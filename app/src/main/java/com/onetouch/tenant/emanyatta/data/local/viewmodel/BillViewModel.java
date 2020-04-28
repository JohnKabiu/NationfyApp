package com.onetouch.tenant.emanyatta.data.local.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apps.nationfy.Constants.BaseApp;
import com.onetouch.tenant.emanyatta.data.local.AppDatabase;
import com.onetouch.tenant.emanyatta.data.local.model.Bill;
import com.onetouch.tenant.emanyatta.data.network.APIClient;
import com.onetouch.tenant.emanyatta.data.network.RequestInterface;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillViewModel extends ViewModel {

    String TAG = "";
    private LiveData<List<Bill>> billList;

    public LiveData<List<Bill>> getBillList(Context context, String token) {
        if (billList == null) {
            billList = new MutableLiveData<>();
            loadBills(context, token);
            AppDatabase appDatabase = AppDatabase.getDatabase(context);
            billList = appDatabase.billDao().getAll();
        }
        return billList;
    }

    private void loadBills(Context context, String token) {
        BaseApp app = (BaseApp) Objects.requireNonNull(context).getApplicationContext();
        RequestInterface requestInterface = APIClient.getClient(app.settings.getBearerToken()).create(RequestInterface.class);
        requestInterface.getBills(token).enqueue(new Callback<List<Bill>>() {
            @Override
            public void onResponse(@NonNull Call<List<Bill>> call, @NonNull Response<List<Bill>> response) {

                Thread thread = new Thread(() -> {
                    AppDatabase appDatabase = AppDatabase.getDatabase(context);
                    appDatabase.billDao().insertBills(response.body());
                });
                thread.start();
            }

            @Override
            public void onFailure(@NonNull Call<List<Bill>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
