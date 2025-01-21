package com.example.Airport.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Flight f WHERE f.origin.id = :originId OR f.destination.id = :destinationId")
    boolean existsByOriginIdOrDestinationId(@Param("originId") Long originId, @Param("destinationId") Long destinationId);
}
