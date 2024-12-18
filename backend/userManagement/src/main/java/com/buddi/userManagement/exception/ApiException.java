package com.buddi.userManagement.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}

