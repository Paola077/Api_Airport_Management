package com.example.Airport.profile;

public record ProfileUpdateResponse(
        Long id,
        String username,
        String email,
        String imageUrl
) {
}
