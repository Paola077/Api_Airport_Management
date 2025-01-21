package com.example.Airport.airport;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AirportRequest(
        @Pattern(regexp = "^[A-Z0-9]{3}$", message = "The code must be 3 characters")
        String code,

        @NotNull(message = "the field name cannot be null")
        @NotEmpty(message = "the field name cannot be empty")
        String name,

        @NotNull(message = "the field location cannot be null")
        @NotEmpty(message = "the field location cannot be empty")
        String location
) {
}
