package com.example.Airport.user;

import com.example.Airport.profile.Profile;
import com.example.Airport.profile.ProfileMapper;
import com.example.Airport.profile.ProfileService;
import com.example.Airport.profile.UserProfileResponse;
import com.example.Airport.user.exceptions.UserAlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ProfileService profileService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfileResponse createUser(UserRequest userRequest) {
        Optional<User> existUser = userRepository.findByEmail(userRequest.email());
        if(existUser.isPresent())
            throw new UserAlreadyExistException("User already exist with this email" + userRequest.email());

        User user = UserMapper.toEntity(userRequest, passwordEncoder);
        User savedUser = userRepository.save(user);

        Profile profile = profileService.createProfile(savedUser);

        return new UserProfileResponse(
                UserMapper.toResponse(savedUser),
                ProfileMapper.toResponse(profile)
        );
    }
}
