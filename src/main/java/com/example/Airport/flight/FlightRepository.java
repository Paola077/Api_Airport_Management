package com.example.Airport.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Flight f WHERE f.origin.id = :originId OR f.destination.id = :destinationId")
    boolean existsByOriginIdOrDestinationId(@Param("originId") Long originId, @Param("destinationId") Long destinationId);

    @Query("SELECT f FROM Flight f " +
            "WHERE (:originId IS NULL OR f.origin.id = :originId) " +
            "AND (:destinationId IS NULL OR f.destination.id = :destinationId) " +
            "AND (:departureDateTime IS NULL OR f.departureDateTime = :departureDateTime) " +
            "AND (:arrivalDateTime IS NULL OR f.arrivalDateTime = :arrivalDateTime)" +
            "AND (:status IS NULL OR f.status = :status)")
    List<Flight> findByFilters(
            @Param("originId") Long originId,
            @Param("destinationId") Long destinationId,
            @Param("departureDateTime") LocalDateTime departureDateTime,
            @Param("arrivalDateTime") LocalDateTime arrivalDateTime,
            @Param("status") FlightStatus status
    );

    List<Flight> findAllByStatus(FlightStatus flightStatus); //Méthod para buscar vuelos en estado activo
}
