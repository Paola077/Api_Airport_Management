package com.example.Airport.airport;

import com.example.Airport.flight.FlightResponse;
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
    private final AirportRepository airportRepository;

    public AirportController(AirportService airportService,
                             AirportRepository airportRepository) {
        this.airportService = airportService;
        this.airportRepository = airportRepository;
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
    public ResponseEntity<String> deleteTrend(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>("The airport has been eliminated", HttpStatus.OK);
    }
}
