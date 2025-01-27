package com.example.Airport.user;

import com.example.Airport.reservation.ReservationResponse;

import java.util.List;

public class UserMapper {

    public static User toEntity(UserRequest userRequest) {
        return new User(
                userRequest.username(),
                userRequest.email(),
                userRequest.password()
        );
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public static UserWithReservationsResponse toUserWithReservations(User user, List<ReservationResponse> reservationResponses) {
        return new UserWithReservationsResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                reservationResponses
        );
    }
}
