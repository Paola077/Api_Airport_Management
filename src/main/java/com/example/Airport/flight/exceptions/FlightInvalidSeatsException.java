package com.example.Airport.flight.exceptions;

public class FlightInvalidSeatsException extends RuntimeException {
    public FlightInvalidSeatsException(String message) {
        super(message);
    }
}
