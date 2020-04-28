package com.onetouch.tenant.emanyatta;

import android.app.Application;

import com.apps.nationfy.BuildConfig;
import com.facebook.stetho.Stetho;
import com.onetouch.tenant.emanyatta.settings.SessionManager;

public class Emanyatta extends Application {

    public static Emanyatta instance;
    public SessionManager settings;

    public Emanyatta() {
        super();
    }

    public static Emanyatta getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        settings = new SessionManager(getApplicationContext());

        if (BuildConfig.DEBUG) {

            Stetho.initializeWithDefaults(this);
        }

    }
}
