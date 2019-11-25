package com.example.ecommerse.viewmodels;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerse.R;
import com.example.ecommerse.models.AuthFormState;

public class FormValidateViewModel extends ViewModel {

    private MutableLiveData<AuthFormState> mFormState;

    public FormValidateViewModel() {
        mFormState = new MutableLiveData<>();
    }

    public LiveData<AuthFormState> getFormState() {
        return mFormState;
    }

    public void onLoginDataChanged(String email, String password) {
        if (!isEmailValid(email)) {
            mFormState.setValue(new AuthFormState(R.string.invalid_email, null, null));
        } else if (!isPasswordValid(password)) {
            mFormState.setValue(new AuthFormState(null, R.string.invalid_password, null));
        } else {
            mFormState.setValue(new AuthFormState(true));
        }
    }

    public void onRegisterDataChanged(String username, String email, String password) {
        if (!isEmailValid(email)) {
            mFormState.setValue(new AuthFormState(R.string.invalid_email, null, null));
        } else if (!isPasswordValid(password)) {
            mFormState.setValue(new AuthFormState(null, R.string.invalid_password, null));
        } else if(!isUsernameValid(username)){
            mFormState.setValue((new AuthFormState(null, null, R.string.invalid_username)));
        } else {
            mFormState.setValue(new AuthFormState(true));
        }
    }

    private boolean isUsernameValid(String username) {
        return username != null && username.trim().length() > 3;
    }

    // A placeholder username validation check
    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
