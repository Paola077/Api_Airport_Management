package com.example.Airport.flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record FlightResponse(
        Long id,
        String origin,
        String destination,
        LocalDateTime departureDateTime,
        LocalDateTime arrivalDateTime,
        Integer availableSeats,
        Integer totalSeats,
        String status
) {
}
