package com.example.ecommerse.repositories;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

abstract public class SharedPreferenceLiveData<T> extends MutableLiveData<T> {
    public SharedPreferences sharedPrefs;
    String key;
    T defValue;

    public SharedPreferenceLiveData(SharedPreferences prefs, String key, T defValue) {
        this.sharedPrefs = prefs;
        this.key = key;
        this.defValue = defValue;
    }

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (SharedPreferenceLiveData.this.key.equals(key)) {
                setValue(getValueFromPreferences(key, defValue));
            }
        }
    };

    protected abstract T getValueFromPreferences(String key, T defValue);

    @Override
    protected void onActive() {
        super.onActive();
        setValue(getValueFromPreferences(key, defValue));
        sharedPrefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    protected void onInactive() {
        sharedPrefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
        super.onInactive();
    }
}
