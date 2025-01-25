package com.example.Airport.flight;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


public record FlightRequest(
        Long originId,

        Long destinationId,

        @NotNull(message = "Departure date and time cannot be null.")
        @Future(message = "Departure date and time must be in the future.")
        LocalDateTime departureDateTime,

        @NotNull(message = "Arrival date and time cannot be null.")
        @Future(message = "Arrival date and time must be in the future.")
        LocalDateTime arrivalDateTime,

        @NotNull(message = "the field availableSeats cannot be null")
        Integer availableSeats,

        @NotNull(message = "the field totalSeats cannot be null")
        Integer totalSeats,

        String status
) {
}
