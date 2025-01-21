package com.example.Airport.user;

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
}
