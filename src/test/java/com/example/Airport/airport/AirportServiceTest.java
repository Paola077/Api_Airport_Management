package com.example.Airport.airport;

import com.example.Airport.airport.exceptions.AirportAlreadyExistException;
import com.example.Airport.airport.exceptions.AirportInvalidModificationException;
import com.example.Airport.flight.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class AirportServiceTest {

    @Autowired
    private AirportService airportService;

    @MockitoBean
    private AirportRepository airportRepository;

    @MockitoBean
    private FlightRepository flightRepository;

    @Test
    void shouldCreateAirportIfCodeAndNameAreUnique() {
        when(airportRepository.findByCode("LAX")).thenReturn(Optional.empty());
        when(airportRepository.findByName("Los Angeles Internacional Airport")).thenReturn(Optional.empty());

        Airport airport = new Airport("LAX", "Los Angeles International Airport", "Los Angeles");
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        AirportResponse airportResponse = airportService.createAirport(new AirportRequest("LAX", "Los Angeles International Airport", "Los Angeles"));

        assertEquals("LAX", airportResponse.code());
        assertEquals("Los Angeles International Airport", airportResponse.name());
    }

    @Test
    void shouldThrowExceptionIfCodeExists() {
        when(airportRepository.findByCode("JFK")).thenReturn(Optional.of(new Airport()));

        assertThrows(AirportAlreadyExistException.class, () -> {
            airportService.createAirport(new AirportRequest("JFK", "Duplicate Airport", "New York"));
        });
    }
    @Test
    void shouldReturnAllAirports() {
        List<Airport> airports = List.of(
                new Airport("JFK", "John F. Kennedy International Airport", "New York"),
                new Airport("LAX", "Los Angeles International Airport", "Los Angeles")
        );

        when(airportRepository.findAll()).thenReturn(airports);

        List<AirportResponse> result = airportService.findAll();

        assertEquals(2, result.size());
        assertEquals("JFK", result.get(0).code());
        assertEquals("LAX", result.get(1).code());
    }

    @Test
    void shouldUpdateAirportSuccessfully() {
        Airport existingAirport = new Airport("JFK", "Old Name", "Old Location");
        existingAirport.setId(1L);

        when(airportRepository.findById(1L)).thenReturn(Optional.of(existingAirport));

        AirportRequest request = new AirportRequest("JFK", "Updated Name", "Updated Location");

        when(airportRepository.save(any(Airport.class))).thenReturn(existingAirport);

        AirportResponse response = airportService.updateAirport(1L, request);

        assertEquals("Updated Name", response.name());
        assertEquals("Updated Location", response.location());
    }

    @Test
    void shouldDeleteAirportSuccessfully() {
        Airport existingAirport = new Airport();
        existingAirport.setId(1L);

        when(airportRepository.findById(1L)).thenReturn(Optional.of(existingAirport));
        when(flightRepository.existsByOriginIdOrDestinationId(1L, 1L)).thenReturn(false);

        assertDoesNotThrow(() -> airportService.deleteAirport(1L));
        verify(airportRepository, times(1)).delete(existingAirport);
    }

     @Test
    void shouldThrowErrorWhenDeletingAirportWithActiveFlights() {
         when(airportRepository.findById(1L)).thenReturn(Optional.of(new Airport("JFK", "Test Airport", "Test Location")));
         when(flightRepository.existsByOriginIdOrDestinationId(1L, 1L)).thenReturn(true);

         assertThrows(AirportInvalidModificationException.class, () -> airportService.deleteAirport(1L));
    }
}
