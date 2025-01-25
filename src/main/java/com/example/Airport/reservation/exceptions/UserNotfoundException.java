package com.example.Airport.reservation.exceptions;

public class UserNotfoundException extends RuntimeException {
    public UserNotfoundException(String message) {
        super(message);
    }
}
