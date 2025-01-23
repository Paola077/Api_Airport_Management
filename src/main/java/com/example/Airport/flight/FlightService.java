package com.example.Airport.flight;

import com.example.Airport.airport.Airport;
import com.example.Airport.airport.AirportRepository;
import com.example.Airport.airport.exceptions.AirportNotFoundException;
import com.example.Airport.flight.exceptions.FlightInvalidOriginAndDestination;
import com.example.Airport.flight.exceptions.FlightInvalidSeatsException;
import com.example.Airport.flight.exceptions.FlightNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    private final AirportRepository airportRepository;

    public FlightService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public FlightResponse createFlight (FlightRequest flightRequest) {

        if(flightRequest.originId().equals(flightRequest.destinationId())) {
            throw new FlightInvalidOriginAndDestination("Origin and destination airports must be different.");
        }

        if(flightRequest.availableSeats() > flightRequest.totalSeats()) {
            throw new FlightInvalidSeatsException("Available seats cannot exceed total seats.");
        }

        Integer availableSeats = flightRequest.availableSeats();
        Integer totalSeats = flightRequest.totalSeats();

        Airport origin = airportRepository.findById(flightRequest.originId())
                .orElseThrow(() -> new FlightInvalidOriginAndDestination("Origin airport not found."));

        Airport destination = airportRepository.findById(flightRequest.destinationId())
                .orElseThrow(() -> new FlightInvalidOriginAndDestination("Destination airport not found."));

        boolean flightExists = flightRepository.existsByOriginAndDestinationAndDateAndTime(
                flightRequest.originId(),
                flightRequest.destinationId(),
                flightRequest.date(),
                flightRequest.time()
        );

        if (flightExists) {
            throw new FlightInvalidOriginAndDestination("A flight with the same origin, destination, date, and time already exists.");
        }

        Flight flight = FlightMapper.toEntity(flightRequest);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setAvailableSeats(availableSeats);
        flight.setTotalSeats(totalSeats);
        flight.setStatus(FlightStatus.valueOf(flightRequest.status().toUpperCase()));

        Flight savedFlight = flightRepository.save(flight);
        return FlightMapper.toResponse(savedFlight);
    }

    public List<FlightResponse> getFlights(Long originId, Long destinationId, LocalDate date) {
        if (originId == null && destinationId == null && date == null) {
            List<Flight> allFlights = flightRepository.findAll();
            if (allFlights.isEmpty()) {
                throw new FlightNotFoundException("There are no flights to show");
            }

            return allFlights.stream()
                    .map(FlightMapper::toResponse)
                    .collect(Collectors.toList());
        }

        List<Flight> filteredFlights = flightRepository.findByFilters(originId, destinationId, date);

        if (filteredFlights.isEmpty()) {
            StringBuilder message = new StringBuilder("No flights found");
            if (originId != null) {
                message.append(" for origin ID: ").append(originId);
            }
            if (destinationId != null) {
                message.append(" and destination ID: ").append(destinationId);
            }
            if (date != null) {
                message.append(" on date: ").append(date);
            }
            throw new FlightNotFoundException(message.toString());
        }

        return filteredFlights.stream()
                .map(FlightMapper::toResponse)
                .collect(Collectors.toList());
    }

    public FlightResponse findById(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if(optionalFlight.isEmpty()) {
            throw new AirportNotFoundException("The airport with id " + id + " does not exist.");
        }

        Flight flight = optionalFlight.get();
        return FlightMapper.toResponse(flight);
    }

    public List<FlightResponse> findFlights(Long originId, Long destinationId, LocalDate date) {
        List<Flight> flights = flightRepository.findByFilters(originId, destinationId, date);
        return flights.stream()
                .map(FlightMapper::toResponse)
                .collect(Collectors.toList());
    }

    public FlightResponse updateFlight(Long id,FlightRequest flightRequest) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight with ID " + id + " not found"));

        if (flightRequest.date() != null) {
            flight.setDate(flightRequest.date());
        }

        if(flightRequest.time() != null) {
            flight.setTime(flightRequest.time());
        }

        if (flightRequest.availableSeats() != null) {
            if (flightRequest.availableSeats() > flight.getTotalSeats()) {
                throw new FlightInvalidSeatsException("Available seats cannot exceed total seats");
            }
            flight.setAvailableSeats(flightRequest.availableSeats());
        }

        Flight updatedFlight = flightRepository.save(flight);
        return FlightMapper.toResponse(updatedFlight);
    }

    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight with ID " + id + " not found."));
        flightRepository.delete(flight);
    }
}
