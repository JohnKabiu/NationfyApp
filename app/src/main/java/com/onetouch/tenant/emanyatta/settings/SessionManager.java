package com.onetouch.tenant.emanyatta.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SessionManager {
    private SharedPreferences settings;

    public SessionManager(Context context) {
        settings = context.getSharedPreferences("emanyatta_settings", Context.MODE_PRIVATE);
    }

    public String getBearerToken() {
        return settings.getString("token", "");
    }

    public void setBearerToken(String token) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getUserId() {
        return settings.getString("user_id", "");
    }

    public void setUserId(String user_id) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user_id", user_id);
        editor.apply();
    }

    public String getPhoneNumber() {
        return settings.getString("phone_number", "");
    }

    public void setPhoneNumber(String phone_number) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("phone_number", phone_number);
        editor.apply();
    }

    public Boolean IsloggedIn() {
        return settings.getBoolean("is_logged_in", false);
    }

    public void SetIsloggedIn(boolean isloggedin) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("is_logged_in", isloggedin);
        editor.apply();
    }
}
