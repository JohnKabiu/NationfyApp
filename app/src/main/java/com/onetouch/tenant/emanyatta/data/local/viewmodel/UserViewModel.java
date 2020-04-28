package com.onetouch.tenant.emanyatta.data.local.viewmodel;

import android.content.Context;

import com.onetouch.tenant.emanyatta.data.local.AppDatabase;
import com.onetouch.tenant.emanyatta.data.local.model.User;

import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private User user;

    public User getUser(Context context, String token) {
        if (user == null) {
            user = new User();
            AppDatabase appDatabase = AppDatabase.getDatabase(context);
            user = appDatabase.userDao().getUser();
        }
        return user;
    }
}
