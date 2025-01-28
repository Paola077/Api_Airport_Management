package com.example.Airport.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.flight.id = :flightId AND r.reservationExpirationTime > :currentTime")
    boolean areSeatsBlockedForFlight(@Param("flightId") Long flightId, @Param("currentTime") LocalDateTime currentTime);

    boolean existsByFlightId(Long flightId);

    List<Reservation> findAllByFlightId(Long flightId);

    List<Reservation> findAllByUserId(Long userId);

    @Query("SELECT COALESCE(SUM(r.seatsReserved), 0) " +
            "FROM Reservation r " +
            "WHERE r.flight.id = :flightId " +
            "AND r.reservationStatus = com.example.Airport.reservation.ReservationStatus.CONFIRMED")
    int countConfirmedSeatsByFlightId(@Param("flightId") Long flightId); //Méthod para contar las reservas confirmadas por vuelo
}
