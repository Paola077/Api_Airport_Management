package com.example.Airport.profile;
import com.example.Airport.profile.exceptions.ProfileAlreadyExistException;
import com.example.Airport.profile.exceptions.ProfileNotFoundException;
import com.example.Airport.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public List<ProfileResponse> getProfiles() {
        List<Profile> profiles = profileRepository.findAll();

        if(profiles.isEmpty()) {
            throw new ProfileNotFoundException("No profiles found.");
        }

        return profiles.stream()
                .map(ProfileMapper::toResponse)
                .collect(Collectors.toList());
    }


    public ProfileResponse getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(()-> new ProfileNotFoundException("Profile not found for ID: " + id));

        return ProfileMapper.toResponse(profile);
    }

    public ProfileResponse getProfileByEmail(String email) {
        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found for email: " + email));

        return ProfileMapper.toResponse(profile);
    }

    public ProfileResponse getProfileByUserId(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found for user ID: " + userId));

        return ProfileMapper.toResponse(profile);
    }

    @Transactional
    public void deleteProfileByUserId(Long id) {
        profileRepository.deleteByUserId(id);
    }
}
