package com.example.Airport.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.flight.id = :flightId AND r.reservationExpirationTime > :currentTime")
    boolean areSeatsBlockedForFlight(@Param("flightId") Long flightId, @Param("currentTime") LocalDateTime currentTime);
    boolean existsByFlightId(Long flightId);
}
