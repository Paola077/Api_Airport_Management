package com.example.Airport.airport;

public record AirportResponse(
        Long id,
        String code,
        String name,
        String location
) {
}
