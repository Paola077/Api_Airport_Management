package com.example.Airport.flight;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record FlightRequest(
        Long originId,

        Long destinationId,

        @NotNull(message = "the field date cannot be null")
        @Future(message = "The date cannot be previous to actual date")
        LocalDate date,

        @NotNull(message = "the field time cannot be null")
        LocalTime time,

        @NotNull(message = "the field availableSeats cannot be null")
        Integer availableSeats,

        @NotNull(message = "the field totalSeats cannot be null")
        Integer totalSeats,

        @NotNull(message = "the field status cannot be null")
        @NotEmpty(message = "the field status cannot be empty")
        String status
) {
}
