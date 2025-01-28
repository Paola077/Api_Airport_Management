package com.example.Airport.flight;

import com.example.Airport.airport.Airport;
import com.example.Airport.airport.AirportRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/*
@SpringBootTest
class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @MockitoBean
    private FlightRepository flightRepository;

    @MockitoBean
    private AirportRepository airportRepository;

    @Test
    void shouldCreateFlightSuccessfully() {

        LocalDateTime expectedDepartureDateTime = LocalDateTime.now().plusDays(1);
        LocalDateTime expectedArrivalDateTime = LocalDateTime.now().plusDays(1).plusHours(2);

        FlightRequest flightRequest = new FlightRequest(
                1L, 2L,
                expectedDepartureDateTime,
                expectedArrivalDateTime,
                100, 200
        );

        Airport origin = new Airport("JFK", "John F. Kennedy International Airport", "New York");
        Airport destination = new Airport("LAX", "Los Angeles International Airport", "Los Angeles");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(origin));
        when(airportRepository.findById(2L)).thenReturn(Optional.of(destination));
        when(flightRepository.findByFilters(
                1L, 2L,
                flightRequest.departureDateTime(),
                flightRequest.arrivalDateTime(),
                FlightStatus.ACTIVE
        )).thenReturn(Collections.emptyList());



        Flight flight = new Flight(1L,origin, destination, expectedDepartureDateTime, expectedArrivalDateTime, 100, 200, FlightStatus.ACTIVE);

        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        FlightResponse flightResponse = flightService.createFlight(flightRequest);

        assertNotNull(flightResponse);
        assertEquals(1L, flightResponse.id());
        assertEquals("John F. Kennedy International Airport", flightResponse.origin());
        assertEquals("Los Angeles International Airport", flightResponse.destination());
    }
    }
*/

/*    @Test
    void getFlights() {
    }

    @Test
    void findById() {
    }

    @Test
    void findFlights() {
    }

    @Test
    void updateFlight() {
    }

    @Test
    void deleteFlight() {
    }*/
