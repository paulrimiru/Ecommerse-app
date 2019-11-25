package com.example.ecommerse.views.ui.Register;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RegisterViewModelFactory implements ViewModelProvider.Factory {
    private LifecycleOwner mlifecycleOwner;

    public RegisterViewModelFactory(LifecycleOwner lifecycleOwner) {
        mlifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(mlifecycleOwner);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
