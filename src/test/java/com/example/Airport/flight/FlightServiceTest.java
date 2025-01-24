package com.example.Airport.flight;

import com.example.Airport.airport.Airport;
import com.example.Airport.airport.AirportRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @MockitoBean
    private FlightRepository flightRepository;

    @MockitoBean
    private AirportRepository airportRepository;

    @Test
    void testCreateFlight_success() {

    }
    }

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
