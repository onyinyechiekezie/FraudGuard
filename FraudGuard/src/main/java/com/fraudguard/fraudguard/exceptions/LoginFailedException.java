package com.fraudguard.fraudguard.exceptions;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {

        super(message);
    }
}
