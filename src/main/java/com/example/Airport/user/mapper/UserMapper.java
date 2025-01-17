package com.example.Airport.user.mapper;

import com.example.Airport.user.dto.UserRequest;
import com.example.Airport.user.dto.UserResponse;
import com.example.Airport.user.model.User;

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
