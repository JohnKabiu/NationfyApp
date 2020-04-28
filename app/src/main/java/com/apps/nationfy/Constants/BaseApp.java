package com.apps.nationfy.Constants;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.multidex.MultiDex;

import com.apps.nationfy.BuildConfig;
import com.facebook.stetho.Stetho;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OneSignal;
import com.onetouch.tenant.emanyatta.settings.SessionManager;

/**
 * Created by apps on 12/12/2018.
 */

public class BaseApp extends Application {
    private static BaseApp mInstance;
    public SharedPreferences preferences;
    public String prefName = "GoEstate";
    public BaseApp() {
        mInstance = this;
    }

    public SessionManager settings;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        settings = new SessionManager(getApplicationContext());

        if (BuildConfig.DEBUG) {

            Stetho.initializeWithDefaults(this);
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized BaseApp getInstance() {
        return mInstance;
    }

    public void saveIsLogin(boolean flag) {
        preferences = this.getSharedPreferences(prefName, 0);
        Editor editor = preferences.edit();
        editor.putBoolean("IsLoggedIn", flag);
        editor.apply();
    }

    public boolean getIsLogin() {
        preferences = this.getSharedPreferences(prefName, 0);
        return preferences.getBoolean("IsLoggedIn", false);
    }
}
