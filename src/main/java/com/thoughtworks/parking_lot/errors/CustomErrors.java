package com.thoughtworks.parking_lot.errors;

public class CustomErrors {
    private int error_code;
    private String message;

    public int getErrorCode() {
        return error_code;
    }

    public void setErrorCode(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}