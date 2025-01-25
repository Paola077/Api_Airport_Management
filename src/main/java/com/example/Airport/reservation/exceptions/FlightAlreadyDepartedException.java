package com.example.Airport.reservation.exceptions;

public class FlightAlreadyDepartedException extends RuntimeException {
    public FlightAlreadyDepartedException(String message) {
        super(message);
    }
}
