package com.example.Airport.reservation.exceptions;

public class NotAvailableSeatsReservationException extends RuntimeException {
    public NotAvailableSeatsReservationException(String message) {
        super(message);
    }
}
