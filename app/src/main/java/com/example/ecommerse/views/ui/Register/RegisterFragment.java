package com.example.ecommerse.views.ui.Register;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.ecommerse.models.RegisterResponse;
import com.example.ecommerse.viewmodels.FormValidateViewModel;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private FormValidateViewModel formValidateViewModel;

    private EditText emailText;
    private EditText passwordText;
    private EditText usernameText;
    private Button loginButton;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, new RegisterViewModelFactory(this)).get(RegisterViewModel.class);

        formValidateViewModel = ViewModelProviders.of(this).get(FormValidateViewModel.class);

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
                if (authFormState.getUsernameError() != null) {
                    usernameText.setError(getString(authFormState.getUsernameError()));
                }
            }
        });

        mViewModel.getmRegisterResponse().observe(this, new Observer<RegisterResponse>() {
            @Override
            public void onChanged(RegisterResponse registerResponse) {
                if (registerResponse.getMessage() != null) {
                    Toast.makeText(getContext(), registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailText = view.findViewById(R.id.email);
        passwordText = view.findViewById(R.id.password);
        usernameText = view.findViewById(R.id.username);
        loginButton = view.findViewById(R.id.submit);

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
                formValidateViewModel.onRegisterDataChanged(
                        usernameText.getText().toString(),
                        emailText.getText().toString(),
                        passwordText.getText().toString()
                );
            }
        };

        usernameText.addTextChangedListener(afterTextChangedListener);
        emailText.addTextChangedListener(afterTextChangedListener);
        passwordText.addTextChangedListener(afterTextChangedListener);

        passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.submitRegisterData(
                        usernameText.getText().toString(),
                        emailText.getText().toString(),
                        passwordText.getText().toString()
                );
            }
        });

    }

}
