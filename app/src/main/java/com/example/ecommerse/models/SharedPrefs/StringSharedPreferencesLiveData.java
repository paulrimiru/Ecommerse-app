package com.example.ecommerse.models.SharedPrefs;

import android.content.SharedPreferences;

import com.example.ecommerse.repositories.SharedPreferenceLiveData;

public class StringSharedPreferencesLiveData extends SharedPreferenceLiveData<String>{

    public StringSharedPreferencesLiveData(SharedPreferences preferences, String key, String defaultVal) {
        super(preferences, key, defaultVal);
    }

    @Override
    public String getValueFromPreferences(String key, String defValue) {
        return sharedPrefs.getString(key, defValue);
    }
}

