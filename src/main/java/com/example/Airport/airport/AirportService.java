package com.example.Airport.airport;

import com.example.Airport.airport.exceptions.AirportAlreadyExistException;
import com.example.Airport.airport.exceptions.AirportInvalidModificationException;
import com.example.Airport.airport.exceptions.AirportNotFoundException;
import com.example.Airport.flight.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    private final FlightRepository flightRepository;

    public AirportService(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    public AirportResponse createAirport(AirportRequest airportRequest) {
        Optional<Airport> existAirportByCode = airportRepository.findByCode(airportRequest.code());
        if(existAirportByCode.isPresent()) {
            throw new AirportAlreadyExistException("An airport with this code already exists: " + airportRequest.code());
        }

        Optional<Airport> existAirportByName = airportRepository.findByName(airportRequest.name());
        if(existAirportByName.isPresent()) {
            throw new AirportAlreadyExistException("An airport with this name already exists: " + airportRequest.name());
        }

        Airport airport = AirportMapper.toEntity(airportRequest);
        Airport savedAirport = airportRepository.save(airport);
        return AirportMapper.toResponse(savedAirport);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public AirportResponse updateAirport(Long id, AirportRequest airportRequest) {
        Airport existingAirport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException("Airport with ID " + id + " not found"));

        if(!existingAirport.getCode().equals(airportRequest.code())) {
            throw new AirportInvalidModificationException("Modification of the airport code (IATA) is not allowed");
        }

        existingAirport.setName(airportRequest.name());
        existingAirport.setLocation(airportRequest.location());
        Airport updatedAirport = airportRepository.save(existingAirport);
        return AirportMapper.toResponse(updatedAirport);
    }

    public void deleteAirport(Long id) {
        Airport existingAirport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException("Airport with ID " + id + " not found"));

        boolean hasActiveFlights = flightRepository.existsByOriginIdOrDestinationId(id, id);
        if (hasActiveFlights) {
            throw new AirportInvalidModificationException("Cannot delete the airport because it is associated with active flights");
        }

        airportRepository.delete(existingAirport);
    }
}
