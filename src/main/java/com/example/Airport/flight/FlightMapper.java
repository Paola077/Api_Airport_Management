package com.example.Airport.flight;

public class FlightMapper {

    public static Flight toEntity(FlightRequest flightRequest) {
        return new Flight(
           flightRequest.date(),
           flightRequest.time(),
           flightRequest.availableSeats(),
           flightRequest.totalSeats(),
           flightRequest.status()
        );
    }

    public static FlightResponse toResponse(Flight flight) {
        return new FlightResponse(
                flight.getId(),
                flight.getOrigin().getName(),
                flight.getDestination().getName(),
                flight.getDate(),
                flight.getTime(),
                flight.getAvailableSeats(),
                flight.getTotalSeats(),
                flight.getStatus().name().toLowerCase()
        );
    }
}
