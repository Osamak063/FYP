package com.example.osamakhalid.thesmartinterviewer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.osamakhalid.thesmartinterviewer.models.User;
import com.google.gson.Gson;

import static com.example.osamakhalid.thesmartinterviewer.utils.PreferencesUtility.LOGGED_IN_PREF;

/**
 * Created by Osama Khalid on 1/7/2019.
 */

public class SaveSharedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static void saveUserDetails(Context context, User user) {
        SharedPreferences mPrefs = context.getSharedPreferences("UserData", 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("User", json);
        prefsEditor.apply();
    }

    public static User getUserData(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("UserData", 0);
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        User userData = gson.fromJson(json, User.class);
        return userData;
    }
}
