package com.example.Airport.flight;

import java.time.LocalDate;
import java.time.LocalTime;

public record FlightResponse(
        Long id,
        String origin,
        String destination,
        LocalDate date,
        LocalTime time,
        Integer availableSeats,
        Integer totalSeats,
        String status
) {
}
