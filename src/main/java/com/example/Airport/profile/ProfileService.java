package com.example.Airport.profile;

import com.example.Airport.profile.exceptions.ProfileAlreadyExistException;
import com.example.Airport.user.User;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(User user) {

        if(profileRepository.findByUserId(user.getId()).isPresent()) {
            throw new ProfileAlreadyExistException("Profile already exists for user ID: " + user.getId());
        }

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setUsername(user.getUsername());
        profile.setEmail(user.getEmail());
        profile.setImageUrl("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png");
        profileRepository.save(profile);
        return profile;
    }
}
