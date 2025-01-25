package com.example.Airport.reservation;

import com.example.Airport.flight.Flight;
import com.example.Airport.user.User;

import java.time.LocalDateTime;

public class ReservationMapper {


    public static Reservation toEntity(ReservationRequest reservationRequest, User user, Flight flight, LocalDateTime expirationTime) {

        return new Reservation(
                user,
                flight,
                reservationRequest.seatsReserved(),
                expirationTime,
                ReservationStatus.valueOf(reservationRequest.status().toUpperCase())
        );
    }

    public static ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getFlight().getId(),
                reservation.getSeatsReserved(),
                reservation.getReservationExpirationTime(),
                reservation.getReservationStatus().name()
        );
    }


}
