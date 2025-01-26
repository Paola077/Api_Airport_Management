package com.example.Airport.profile;

import com.example.Airport.user.UserResponse;

public record UserProfileResponse(
        UserResponse user,
        ProfileResponse profile
) {
}
