package com.example.Airport.profile.exceptions;

public class ProfileAlreadyExistException extends RuntimeException {
    public ProfileAlreadyExistException(String message) {
        super(message);
    }
}
