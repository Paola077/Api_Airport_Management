package com.example.Airport.airport;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<AirportResponse> createAirport(@Valid  @RequestBody AirportRequest airportRequest) {
        AirportResponse airportResponse = airportService.createAirport(airportRequest);
        return new ResponseEntity<>(airportResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();

        List<AirportResponse> airportResponses = airports.stream()
                .map(airport -> new AirportResponse(
                        airport.getId(),
                        airport.getCode(),
                        airport.getName(),
                        airport.getLocation()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(airportResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(
            @PathVariable Long id,
            @RequestBody AirportRequest airportRequest) {
        AirportResponse updatedAirport = airportService.updateAirport(id, airportRequest);
        return ResponseEntity.ok(updatedAirport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrend(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>("The airport has been eliminated", HttpStatus.OK);
    }
}
