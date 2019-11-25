package com.example.ecommerse.repositories;


import android.content.SharedPreferences;

import com.example.ecommerse.models.SharedPrefs.StringSharedPreferencesLiveData;

public class SharedPreferencesRepository {
    String TOKEN_KEY = "TOKEN_KEY";


    StringSharedPreferencesLiveData token;
    SharedPreferences preferences;

    public SharedPreferencesRepository(SharedPreferences preferences) {
        this.preferences = preferences;
        token = new StringSharedPreferencesLiveData(preferences, TOKEN_KEY, "");
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public StringSharedPreferencesLiveData getToken() {
        return token;
    }
}
