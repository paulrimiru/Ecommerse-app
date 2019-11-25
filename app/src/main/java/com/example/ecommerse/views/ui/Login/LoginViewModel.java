package com.example.ecommerse.views.ui.Login;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.ecommerse.models.LoginResponse;
import com.example.ecommerse.repositories.AuthRepository;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoginResponse> mloginResponse;
    private AuthRepository authRepository;

    public LoginViewModel(final LifecycleOwner lifecycleOwner) {
        mloginResponse = new MutableLiveData<>();

        authRepository = AuthRepository.getInstance();

        authRepository.getLoginUserData().observe(lifecycleOwner, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                mloginResponse.setValue(loginResponse);
            }
        });
    }

    public void  submitLoginData(String email, String password) {
        authRepository.loginUser(email, password);
    }

    public LiveData<LoginResponse> getLoggedInUser() {
        return mloginResponse;
    }
}
