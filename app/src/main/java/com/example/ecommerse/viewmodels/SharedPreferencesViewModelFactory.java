package com.example.ecommerse.viewmodels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SharedPreferencesViewModelFactory implements ViewModelProvider.Factory {
    private Context mContext;
    private String key;
    private String defaultValue;

    public SharedPreferencesViewModelFactory(Context context, String key, String defaultValue) {
        mContext = context;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SharedPreferencesViewModel.class)) {
            return (T) new SharedPreferencesViewModel(mContext);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
