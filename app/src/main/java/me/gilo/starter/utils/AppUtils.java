package me.gilo.starter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.widget.Toast;

/*
 *
 */
public class AppUtils {

    Context context;

    String token;
    String expiry;

    public static final String MY_PREFS_NAME = "StarterApp";

    public AppUtils(Context context) {
        this.context = context;
    }

    public static void showToast(Context context, @StringRes int text, boolean isLong) {
        showToast(context, context.getString(text), isLong);
    }

    public static void showToast(Context context, String text, boolean isLong) {
        Toast.makeText(context, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    public void saveToken(String token, String expiry){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE).edit();
        editor.putString("token", token);
        editor.putString("expiry", expiry);
        editor.putBoolean("loggedIn", true);
        editor.apply();
    }

    public String getToken() {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE);
        return  prefs.getString("token", null);
    }

    public String getExpiry() {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE);
        return  prefs.getString("expiry", null);
    }

    public boolean isLoggedIn(){
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE);
        return  prefs.getBoolean("loggedIn", false);
    }

    public void logOut(){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE).edit();
        editor.putString("token", "");
        editor.putString("expiry", "");
        editor.putBoolean("loggedIn", false);
        editor.apply();
    }
}