package com.example.Airport.user;

public record UserResponse(
        Long id,
        String username,
        String email
) {
}
