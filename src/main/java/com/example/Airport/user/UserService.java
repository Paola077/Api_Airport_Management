package com.example.Airport.user;

import com.example.Airport.user.exceptions.AirportExistingUserException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        Optional<User> existUser = userRepository.findByEmail(userRequest.email());
        if(existUser.isPresent())
            throw new AirportExistingUserException("User already exist with this email" + userRequest.email());

        User user = UserMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }
}
