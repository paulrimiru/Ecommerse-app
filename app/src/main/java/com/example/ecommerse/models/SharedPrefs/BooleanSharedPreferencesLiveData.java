package com.example.ecommerse.models.SharedPrefs;

import android.content.SharedPreferences;

import com.example.ecommerse.repositories.SharedPreferenceLiveData;

public class BooleanSharedPreferencesLiveData extends SharedPreferenceLiveData<Boolean> {

    public BooleanSharedPreferencesLiveData(SharedPreferences preferences, String key, Boolean defValue) {
        super(preferences, key, defValue);
    }

    @Override
    public Boolean getValueFromPreferences(String key, Boolean defValue) {
        return sharedPrefs.getBoolean(key, defValue);
    }
}
