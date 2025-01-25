package com.example.Airport.reservation;
import java.time.LocalDateTime;

public record ReservationResponse(
        Long id,
        Long user,
        Long flight,
        Integer seatsReserved,
        LocalDateTime reservationExpirationTime,
        String reservationStatus
) {
}
