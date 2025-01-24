package com.example.Airport.flight;

public class FlightMapper {

    public static Flight toEntity(FlightRequest flightRequest) {
        return new Flight(
           flightRequest.departureDateTime(),
           flightRequest.arrivalDateTime(),
           flightRequest.availableSeats(),
           flightRequest.totalSeats()
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
