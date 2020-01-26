package com.example.amiapp;

public class LoginResponse {

    private boolean error;
    private String message;
    private Citizen user;

    public LoginResponse(boolean error, String message, Citizen user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Citizen getUser() {
        return user;
    }
}
