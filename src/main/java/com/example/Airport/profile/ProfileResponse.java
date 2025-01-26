package com.example.Airport.profile;

public record ProfileResponse(
        Long id,
        String username,
        String email,
        String imageUrl
) {
}
