package com.example.Airport.user;

import com.example.Airport.encryption.BCrypt;
import com.example.Airport.encryption.Base64Decoder;
import com.example.Airport.reservation.ReservationResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserMapper {

    public static User toEntity(UserRequest userRequest, PasswordEncoder passwordEncoder) {
        String passwordDecoded = Base64Decoder.decode(userRequest.password());
        String passwordEncrypted = new BCrypt(passwordEncoder).encrypt(passwordDecoded);
        return new User(
                userRequest.username(),
                userRequest.email(),
                passwordEncrypted
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
                user.getRoles().iterator().next().getName(),
                reservationResponses
        );
    }
}
