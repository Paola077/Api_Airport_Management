package com.example.Airport.reservation.exceptions;

public class ReservationInvalidStateException extends RuntimeException {
    public ReservationInvalidStateException(String message) {
        super(message);
    }
}
