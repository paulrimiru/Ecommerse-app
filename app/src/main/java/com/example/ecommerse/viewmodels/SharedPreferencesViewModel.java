package com.example.ecommerse.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.example.ecommerse.models.SharedPrefs.StringSharedPreferencesLiveData;
import com.example.ecommerse.repositories.SharedPreferencesRepository;

public class SharedPreferencesViewModel extends ViewModel {

    private SharedPreferencesRepository preferencesRepository;

    public SharedPreferencesViewModel(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE);
        preferencesRepository = new SharedPreferencesRepository(sharedPreferences);
    }

    public StringSharedPreferencesLiveData getTokenState() {
        return preferencesRepository.getToken();
    }

    public void onTokenReceived(String token) {
        if (token.isEmpty()) {
            return;
        }

        this.preferencesRepository.setToken(token);
    }

}
