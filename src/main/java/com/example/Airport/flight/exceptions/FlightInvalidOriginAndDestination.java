package com.example.Airport.flight.exceptions;

public class FlightInvalidOriginAndDestination extends RuntimeException {
    public FlightInvalidOriginAndDestination(String message) {
        super(message);
    }
}
