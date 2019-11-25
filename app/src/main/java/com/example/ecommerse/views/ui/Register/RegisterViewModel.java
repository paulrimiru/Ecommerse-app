package com.example.ecommerse.views.ui.Register;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.ecommerse.models.RegisterResponse;
import com.example.ecommerse.repositories.AuthRepository;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<RegisterResponse> mRegisterResponse;
    private AuthRepository authRepository;

    public RegisterViewModel(final LifecycleOwner lifecycleOwner) {
        mRegisterResponse = new MutableLiveData<>();

        authRepository = AuthRepository.getInstance();

        authRepository.getRegisterUserData().observe(lifecycleOwner, new Observer<RegisterResponse>() {
            @Override
            public void onChanged(RegisterResponse registerResponse) {
                mRegisterResponse.setValue(registerResponse);
            }
        });
    }

    public MutableLiveData<RegisterResponse> getmRegisterResponse() {
        return mRegisterResponse;
    }

    public void submitRegisterData(String username, String email, String password) {
        authRepository.registerUser(username, email, password);
    }
}
