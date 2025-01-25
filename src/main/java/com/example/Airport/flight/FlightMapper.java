package com.example.Airport.flight;

import com.example.Airport.airport.Airport;

public class FlightMapper {

    public static Flight toEntity(FlightRequest flightRequest, Airport originAirport, Airport destinationAirport) {
        return new Flight(
                originAirport,
                destinationAirport,
                flightRequest.departureDateTime(),
                flightRequest.arrivalDateTime(),
                flightRequest.availableSeats(),
                flightRequest.totalSeats(),
                FlightStatus.valueOf(flightRequest.status().toUpperCase())
        );
    }

    public static FlightResponse toResponse(Flight flight) {
        return new FlightResponse(
                flight.getId(),
                flight.getOrigin().getName(),
                flight.getDestination().getName(),
                flight.getDepartureDateTime(),
                flight.getArrivalDateTime(),
                flight.getAvailableSeats(),
                flight.getTotalSeats(),
                flight.getStatus().name().toLowerCase()
        );
    }
}
