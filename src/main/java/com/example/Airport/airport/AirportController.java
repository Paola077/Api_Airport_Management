package com.example.Airport.airport;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
       List<AirportResponse> airportResponseList = airportService.findAll();
       return new ResponseEntity<>(airportResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) {
        AirportResponse airportResponse = airportService.findById(id);
        return new ResponseEntity<>(airportResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(
            @PathVariable Long id,
            @Valid @RequestBody AirportRequest airportRequest) {
        AirportResponse updatedAirport = airportService.updateAirport(id, airportRequest);
        return ResponseEntity.ok(updatedAirport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>("The airport has been eliminated", HttpStatus.OK);
    }
}
