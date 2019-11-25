package com.example.ecommerse.views;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecommerse.R;
import com.example.ecommerse.views.ui.Login.LoginFragment;
import com.example.ecommerse.views.ui.Register.RegisterFragment;

public class AuthActivity extends AppCompatActivity {
    boolean isLoginScreen = true;

    Fragment loginFragment;
    Fragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        final Button switchScreens = findViewById(R.id.btnSwitchFrags);

        loginFragment = LoginFragment.newInstance();
        registerFragment = RegisterFragment.newInstance();

        loadFragment(loginFragment);

        switchScreens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoginScreen = !isLoginScreen;

                if (isLoginScreen) {
                    loadFragment(loginFragment);
                    switchScreens.setText("Click here to register");
                } else {
                    loadFragment(registerFragment);
                    switchScreens.setText("Click here to login");
                }
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.flFragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}
