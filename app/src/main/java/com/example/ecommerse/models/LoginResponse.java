package com.example.ecommerse.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    private Data data;

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;


    public String getToken() {
        return data.getToken();
    }

    public String getMessage() {
        return message;
    }

}

class Data {
    public String getToken() {
        return token;
    }

    @SerializedName("access_token")
    private String token;
}
