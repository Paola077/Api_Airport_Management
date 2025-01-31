package com.example.Airport.flight;

import com.example.Airport.airport.Airport;
import com.example.Airport.airport.AirportRepository;
import com.example.Airport.airport.exceptions.AirportNotFoundException;
import com.example.Airport.flight.exceptions.FlightInvalidOriginAndDestination;
import com.example.Airport.flight.exceptions.FlightInvalidSeatsException;
import com.example.Airport.flight.exceptions.FlightNotFoundException;
import com.example.Airport.flight.exceptions.InvalidStatusException;
import com.example.Airport.profile.exceptions.FlightInvalidDeleteException;
import com.example.Airport.reservation.ReservationRepository;
import com.example.Airport.reservation.exceptions.FlightAlreadyDepartedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final ReservationRepository reservationRepository;

    public FlightService(FlightRepository flightRepository, AirportRepository airportRepository, ReservationRepository reservationRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.reservationRepository = reservationRepository;
    }

    public FlightResponse createFlight (FlightRequest flightRequest) {

        if(flightRequest.originId().equals(flightRequest.destinationId())) {
            throw new FlightInvalidOriginAndDestination("Origin and destination airports must be different.");
        }

        if(flightRequest.availableSeats() > flightRequest.totalSeats()) {
            throw new FlightInvalidSeatsException("Available seats cannot exceed total seats.");
        }

        Airport origin = airportRepository.findById(flightRequest.originId())
                .orElseThrow(() -> new FlightInvalidOriginAndDestination("Origin airport not found."));

        Airport destination = airportRepository.findById(flightRequest.destinationId())
                .orElseThrow(() -> new FlightInvalidOriginAndDestination("Destination airport not found."));

        FlightStatus status = FlightStatus.valueOf(flightRequest.status().toUpperCase());

        List<Flight> exitingFlights = flightRepository.findByFilters(
                flightRequest.originId(),
                flightRequest.destinationId(),
                flightRequest.departureDateTime(),
                flightRequest.arrivalDateTime(),
                status
        );

        if (!exitingFlights.isEmpty()) {
            throw new FlightInvalidOriginAndDestination("A flight with the same origin, destination, departure and arrival DateTime already exists.");
        }

        Flight flight = FlightMapper.toEntity(flightRequest, origin, destination);

        Flight savedFlight = flightRepository.save(flight);
        flight.setStatus(FlightStatus.ACTIVE);
        return FlightMapper.toResponse(savedFlight);
    }

    public List<FlightResponse> getFlights(Long originId, Long destinationId, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, String status) {
        if (originId == null && destinationId == null && departureDateTime == null && arrivalDateTime == null && status == null) {
            List<Flight> allFlights = flightRepository.findAll();
            if (allFlights.isEmpty()) {
                throw new FlightNotFoundException("There are no flights to show");
            }

            return allFlights.stream()
                    .map(FlightMapper::toResponse)
                    .collect(Collectors.toList());
        }

        if (status == null) {
            throw new InvalidStatusException("Status cannot be null");
        }

        FlightStatus flightStatus = FlightStatus.valueOf(status.toUpperCase());

        List<Flight> filteredFlights = flightRepository.findByFilters(originId, destinationId, departureDateTime, arrivalDateTime, flightStatus);

        if (filteredFlights.isEmpty()) {
            StringBuilder message = new StringBuilder("No flights found");
            if (originId != null) {
                message.append(" for origin ID: ").append(originId);
            }
            if (destinationId != null) {
                message.append(" and destination ID: ").append(destinationId);
            }
            if (departureDateTime != null) {
                message.append(" departing at: ").append(departureDateTime);
            }
            if (arrivalDateTime != null) {
                message.append(" arriving at: ").append(arrivalDateTime);
            }
            message.append(" status at: ").append(status);
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

    public List<FlightResponse> findFlights(Long originId, Long destinationId, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, FlightStatus status) {
        List<Flight> flights = flightRepository.findByFilters(originId, destinationId, departureDateTime, arrivalDateTime, status);
        return flights.stream()
                .map(FlightMapper::toResponse)
                .collect(Collectors.toList());
    }

    public FlightResponse updateFlight(Long id,FlightRequest flightRequest) {
            Flight flight = flightRepository.findById(id)
                    .orElseThrow(() -> new FlightNotFoundException("Flight with ID " + id + " not found"));

            if (flightRequest.departureDateTime() != null) {
                flight.setDepartureDateTime(flightRequest.departureDateTime());
            }

            if (flightRequest.arrivalDateTime() != null) {
                if (flightRequest.arrivalDateTime().isBefore(flight.getDepartureDateTime())) {
                    throw new FlightAlreadyDepartedException("Arrival date and time must be after departure date and time");
                }
                flight.setArrivalDateTime(flightRequest.arrivalDateTime());
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

        if (reservationRepository.existsByFlightId(id)) {
            throw new FlightInvalidDeleteException("Cannot delete flight with ID " + id + " as it has associated reservations");
        }

        flightRepository.delete(flight);
    }
}
