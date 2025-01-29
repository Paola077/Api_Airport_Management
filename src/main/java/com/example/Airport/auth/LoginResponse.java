package com.example.Airport.auth;

public record LoginResponse(
        String email,
        String role
) {
}
