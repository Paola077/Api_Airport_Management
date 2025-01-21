package com.example.Airport.airport;

public class AirportMapper {

    public static Airport toEntity(AirportRequest airportRequest) {
        return new Airport(
                airportRequest.code(),
                airportRequest.name(),
                airportRequest.location()
        );
    }

    public static AirportResponse toResponse(Airport airport) {
        return new AirportResponse(
                airport.getId(),
                airport.getCode(),
                airport.getName(),
                airport.getLocation()
        );
    }
}
