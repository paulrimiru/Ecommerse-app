package com.example.ecommerse.models;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
public class AuthFormState {
    @Nullable
    private Integer emailError;
    @Nullable
    private Integer passwordError;

    @Nullable
    private Integer usernameError;
    private boolean isDataValid;

    public AuthFormState(@Nullable Integer emailError, @Nullable Integer passwordError, @Nullable Integer usernameError) {
        this.emailError = emailError;
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    public AuthFormState(boolean isDataValid) {
        this.usernameError = null;
        this.emailError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    public Integer getEmailError() {
        return emailError;
    }

    @Nullable
    public Integer getPasswordError() {
        return passwordError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
