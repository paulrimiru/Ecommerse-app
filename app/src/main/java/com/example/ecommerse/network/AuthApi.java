package com.example.ecommerse.network;

import com.example.ecommerse.models.LoginData;
import com.example.ecommerse.models.LoginResponse;
import com.example.ecommerse.models.RegisterData;
import com.example.ecommerse.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body() LoginData userData);

    @POST("user/register")
    Call<RegisterResponse> registerUser(@Body() RegisterData userData);
}
