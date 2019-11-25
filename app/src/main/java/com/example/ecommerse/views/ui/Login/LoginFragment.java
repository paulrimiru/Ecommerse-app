package com.example.ecommerse.views.ui.Login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerse.R;
import com.example.ecommerse.models.AuthFormState;
import com.example.ecommerse.models.LoginResponse;
import com.example.ecommerse.viewmodels.FormValidateViewModel;
import com.example.ecommerse.viewmodels.SharedPreferencesViewModel;
import com.example.ecommerse.viewmodels.SharedPreferencesViewModelFactory;
import com.example.ecommerse.views.HomeActivity;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private FormValidateViewModel formValidateViewModel;
    private SharedPreferencesViewModel preferencesViewModel;

    EditText emailText;
    EditText passwordText;
    Button loginButton;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(
                this,
                new LoginViewModelFactory(this)
        ).get(LoginViewModel.class);

        formValidateViewModel = ViewModelProviders.of(this).get(FormValidateViewModel.class);

        preferencesViewModel = ViewModelProviders.of(this, new SharedPreferencesViewModelFactory(
                getContext(),
                "LOGIN",
                ""
        )).get(SharedPreferencesViewModel.class);


        preferencesViewModel.getTokenState().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                if (!s.isEmpty()) {
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
            }
        });

        formValidateViewModel.getFormState().observe(this, new Observer<AuthFormState>() {
            @Override
            public void onChanged(AuthFormState authFormState) {
                if (authFormState == null) {
                    return;
                }
                loginButton.setEnabled(authFormState.isDataValid());
                if (authFormState.getPasswordError() != null) {
                    passwordText.setError(getString(authFormState.getPasswordError()));
                }
                if (authFormState.getEmailError() != null) {
                    emailText.setError(getString(authFormState.getEmailError()));
                }
            }
        });

        mViewModel.getLoggedInUser().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse.getToken() != null) {
                    preferencesViewModel.onTokenReceived(loginResponse.getToken());
                }
                Toast.makeText(getContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailText = view.findViewById(R.id.email);
        passwordText = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.submit);
        loginButton.setEnabled(false);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                formValidateViewModel.onLoginDataChanged(
                        emailText.getText().toString(),
                        passwordText.getText().toString()
                );
            }
        };

        emailText.addTextChangedListener(afterTextChangedListener);
        passwordText.addTextChangedListener(afterTextChangedListener);

        passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mViewModel.submitLoginData(
                            emailText.getText().toString(),
                            passwordText.getText().toString()
                    );
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.submitLoginData(
                        emailText.getText().toString(),
                        passwordText.getText().toString()
                );
            }
        });

    }
}
