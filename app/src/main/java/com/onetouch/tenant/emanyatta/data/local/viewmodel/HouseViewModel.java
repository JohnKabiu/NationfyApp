package com.onetouch.tenant.emanyatta.data.local.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apps.nationfy.Constants.BaseApp;
import com.onetouch.tenant.emanyatta.data.local.AppDatabase;
import com.onetouch.tenant.emanyatta.data.local.model.House;
import com.onetouch.tenant.emanyatta.data.network.APIClient;
import com.onetouch.tenant.emanyatta.data.network.JSONResponse;
import com.onetouch.tenant.emanyatta.data.network.RequestInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HouseViewModel extends ViewModel {
    private MutableLiveData<List<House>> billList;

    public LiveData<List<House>> getHouseList(Context context) {
        if (billList == null) {
            billList = new MutableLiveData<>();
            loadHouses(context);
        }
        return billList;
    }

    public House getHouseLiveData(Context context, int id) {
        AppDatabase appDatabase = AppDatabase.getDatabase(context);
        return appDatabase.houseDao().getHouse(id);
    }

    private void loadHouses(Context context) {

        BaseApp app = (BaseApp) Objects.requireNonNull(context).getApplicationContext();
        RequestInterface requestInterface = APIClient.getClient(app.settings.getBearerToken()).create(RequestInterface.class);
        requestInterface.getJSON().enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(@NonNull Call<JSONResponse> call, @NonNull Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                assert jsonResponse != null;
                List<House> data = new ArrayList<>(Arrays.asList(jsonResponse.getHouse()));

                AppDatabase appDatabase = AppDatabase.getDatabase(context);
//                appDatabase.houseDao().insertHouse(data);
                billList.setValue(data);
            }

            @Override
            public void onFailure(@NonNull Call<JSONResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

