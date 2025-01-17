package com.example.Airport.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AirportExistingUserException extends RuntimeException {
    public AirportExistingUserException(String message) {
        super(message);
    }
}
