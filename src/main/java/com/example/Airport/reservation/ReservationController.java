package com.example.Airport.reservation;

import com.example.Airport.user.UserWithReservationsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest) {
        ReservationResponse reservationResponse = reservationService.createReservation(reservationRequest);
        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<ReservationResponse> confirmReservation(@PathVariable Long id) {
        ReservationResponse reservationResponse = reservationService.confirmReservation(id);
        return ResponseEntity.ok(reservationResponse);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservation() {
        List<ReservationResponse> reservationList = reservationService.findAll();
        return new ResponseEntity<>(reservationList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long id) {
        ReservationResponse reservationResponse = reservationService.findById(id);
        return new ResponseEntity<>(reservationResponse, HttpStatus.OK);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByFlightId(@PathVariable Long flightId) {
        List<ReservationResponse> reservations = reservationService.getReservationsByFlightId(flightId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserWithReservationsResponse> getReservationByUserId(@PathVariable Long userId) {
        UserWithReservationsResponse reservations = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("The reservation has been eliminated", HttpStatus.OK);
    }
}
