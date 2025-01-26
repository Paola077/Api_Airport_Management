package com.example.Airport.profile;

public class ProfileMapper {

    public static ProfileResponse toResponse(Profile profile) {
        return new ProfileResponse(
                profile.getId(),
                profile.getUsername(),
                profile.getEmail(),
                profile.getImageUrl()
        );
    }
}
