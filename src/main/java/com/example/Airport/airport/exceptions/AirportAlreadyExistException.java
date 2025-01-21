package com.example.Airport.airport.exceptions;

public class AirportAlreadyExistException extends RuntimeException {
    public AirportAlreadyExistException(String message) {
        super(message);
    }
}
