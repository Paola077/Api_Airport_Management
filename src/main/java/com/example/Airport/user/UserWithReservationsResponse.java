package com.example.Airport.user;

import com.example.Airport.reservation.ReservationResponse;

import java.util.List;

public record UserWithReservationsResponse(
        Long userId,
        String username,
        String email,
        String role,
        List<ReservationResponse> reservations
) {
}
