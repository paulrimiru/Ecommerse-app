package com.example.ecommerse.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ecommerse.models.LoginData;
import com.example.ecommerse.models.LoginResponse;
import com.example.ecommerse.models.RegisterData;
import com.example.ecommerse.models.RegisterResponse;
import com.example.ecommerse.network.AuthApi;
import com.example.ecommerse.network.RetrofitService;
import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static final AuthRepository ourInstance = new AuthRepository();

    final MutableLiveData<LoginResponse> loginUserData = new MutableLiveData<>();

    final MutableLiveData<RegisterResponse> registerUserData = new MutableLiveData<>();

    private AuthApi authApi;

    public static AuthRepository getInstance() {
        return ourInstance;
    }

    public MutableLiveData<RegisterResponse> getRegisterUserData() {
        return registerUserData;
    }

    public MutableLiveData<LoginResponse> getLoginUserData() {
        return loginUserData;
    }

    private AuthRepository() {
        authApi = RetrofitService.createService(AuthApi.class);
    }

    public void loginUser(String email, String password) {

        LoginData loginData = new LoginData();
        loginData.email = email;
        loginData.password = password;

        authApi.loginUser(loginData).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("LOGIN", new Gson().toJson(response.body()));
                    loginUserData.setValue(response.body());
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Log.d("LOGIN >>>>", jsonObject.toString());

                        LoginResponse mResponse = new LoginResponse();
                        mResponse.setMessage(jsonObject.getString("message"));

                        loginUserData.setValue(mResponse);
                    } catch (Exception e) {
                        loginUserData.setValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("RESP", t.getMessage());
                loginUserData.setValue(null);
            }
        });
    }

    public void registerUser(String username, String email, String password) {
        final RegisterData registerData = new RegisterData();
        registerData.username = username;
        registerData.email = email;
        registerData.password = password;

        authApi.registerUser(registerData).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()) {
                    registerUserData.setValue(response.body());
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());

                        RegisterResponse mResponse = new RegisterResponse();
                        mResponse.setMessage(jsonObject.getString("message"));

                        registerUserData.setValue(mResponse);
                    } catch (Exception e) {
                        registerUserData.setValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d("REGISTER ERROR", t.toString());
                registerUserData.setValue(null);
            }
        });
    }
}
