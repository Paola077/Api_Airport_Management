package com.example.Airport.auth;

import com.example.Airport.auth.exceptions.InvalidCredentialsException;
import com.example.Airport.reservation.exceptions.UserNotFoundException;
import com.example.Airport.user.User;
import com.example.Airport.user.UserMapper;
import com.example.Airport.user.UserRepository;
import com.example.Airport.user.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return UserMapper.toResponse(user);
    }
}
