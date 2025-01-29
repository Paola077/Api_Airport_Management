package com.example.Airport.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByFlightId(Long flightId);
    List<Reservation> findAllByFlightId(Long flightId);
    List<Reservation> findAllByUserId(Long userId);
    List<Reservation> findByUserId(Long userId);
}
