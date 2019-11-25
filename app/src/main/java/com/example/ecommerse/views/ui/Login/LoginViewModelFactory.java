package com.example.ecommerse.views.ui.Login;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private LifecycleOwner mlifecycleOwner;

    public LoginViewModelFactory(LifecycleOwner lifecycleOwner) {
        mlifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mlifecycleOwner);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
