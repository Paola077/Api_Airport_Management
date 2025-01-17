package com.example.Airport.user.service;

import com.example.Airport.user.dto.UserRequest;
import com.example.Airport.user.dto.UserResponse;
import com.example.Airport.user.mapper.UserMapper;
import com.example.Airport.user.model.User;
import com.example.Airport.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = UserMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }
}
