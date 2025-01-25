package com.example.Airport.reservation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReservationRequest(
        @NotNull(message = "User ID is required.")
        Long userId,

        @NotNull(message = "Flight ID is required.")
        Long flightId,

        @NotNull(message = "Seats reserved is required.")
        @Positive(message = "Seats reserved must be greater than 0.")
        Integer seatsReserved,

        @NotNull(message = "Reservation Status is required.")
        String status
) {
}
