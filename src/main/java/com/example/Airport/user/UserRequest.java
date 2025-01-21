package com.example.Airport.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRequest(
        @NotNull(message = "the field username cannot be null")
        @NotEmpty(message = "the field username cannot be empty")
        String username,

        @NotNull(message = "the field email cannot be null")
        @NotEmpty(message = "the field email cannot be empty")
        @Email(message = "The email must be a correctly formatted address")
        String email,

        @Pattern(regexp = "^(?=.*[a-zA-Z]).{8,}$", message = "The password must be at least 8 characters long and contain at least one letter")
        String password
) {
}
