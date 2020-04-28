package com.onetouch.tenant.emanyatta.data.local.viewmodel;

import android.content.Context;

import com.onetouch.tenant.emanyatta.data.local.AppDatabase;
import com.onetouch.tenant.emanyatta.data.local.model.Average;

import androidx.lifecycle.ViewModel;

public class AverageViewModel extends ViewModel {

    private Average average;

    public Average getAverage(Context context) {
        if (average == null) {
            average = new Average();
            AppDatabase appDatabase = AppDatabase.getDatabase(context);
            average = appDatabase.averageDao().getAverage();
        }
        return average;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
