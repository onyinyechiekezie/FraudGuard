package com.fraudguard.fraudguard.exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {

        super(message);
    }
}
