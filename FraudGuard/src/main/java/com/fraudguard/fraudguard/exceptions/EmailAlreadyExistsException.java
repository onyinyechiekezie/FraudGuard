package com.fraudguard.fraudguard.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {

        super(message);
    }
}
